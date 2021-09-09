package edu.byu.cs.tweeter.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.net.TweeterRequestException;
import edu.byu.cs.tweeter.model.service.FeedService;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;

public class FeedServiceTest {
    FeedService service = new FeedService();
    FeedRequest validRequest = new FeedRequest("@AA", 5, null, IntegrationConstant.token);
    FeedRequest invalidRequest = new FeedRequest("@AA", 5, null, null);

    @Test
    public void testGetFeed_success() throws IOException, TweeterRemoteException {
        FeedResponse response = service.getFeed(validRequest);
        Assertions.assertEquals(response.isSuccess(), true);
    }

    @Test
    public void testGetFeed_failed() {
        Assertions.assertThrows(TweeterRequestException.class, () -> {
            service.getFeed(invalidRequest);
        });
    }
}
