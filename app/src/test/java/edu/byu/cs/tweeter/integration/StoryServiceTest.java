package edu.byu.cs.tweeter.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.net.TweeterRequestException;
import edu.byu.cs.tweeter.model.service.StoryService;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;

public class StoryServiceTest {
    StoryService service = new StoryService();
    StoryRequest validRequest = new StoryRequest("@AA", 10, null, IntegrationConstant.token);
    StoryRequest invalidRequest = new StoryRequest("@AA", 10, null, null);

    @Test
    public void testGetStory_success() throws IOException, TweeterRemoteException {
        StoryResponse response = service.getStory(validRequest);
        Assertions.assertEquals(response.isSuccess(), true);
        Assertions.assertEquals(response.getStory().size(), 10);
//        Assertions.assertEquals(response.getStory().get(0).getContent(), "@guy10 @guy100 @guy1 " +
//                "ahah @guy1");
    }

    @Test
    public void testGetStory_failed() {
        Assertions.assertThrows(TweeterRequestException.class, () -> {
            service.getStory(invalidRequest);
        });
    }
}
