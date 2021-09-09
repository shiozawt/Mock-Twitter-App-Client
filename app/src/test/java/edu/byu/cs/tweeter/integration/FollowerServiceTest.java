package edu.byu.cs.tweeter.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.net.TweeterRequestException;
import edu.byu.cs.tweeter.model.service.FollowerService;
import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import edu.byu.cs.tweeter.model.service.response.FollowerResponse;

public class FollowerServiceTest {
    FollowerService service = new FollowerService();
    FollowerRequest validRequest = new FollowerRequest("@AA", 10, null, IntegrationConstant.token);
    FollowerRequest invalidRequest = new FollowerRequest("@AA", 10, null, null);

    @Test
    public void testGetFollower_success() throws IOException, TweeterRemoteException {
        FollowerResponse response = service.getFollowers(validRequest);
        Assertions.assertEquals(response.isSuccess(), true);
        Assertions.assertEquals(response.getFollowers().size(), 10);
    }

    @Test
    public void testGetFollower_failed() {
        Assertions.assertThrows(TweeterRequestException.class, () -> {
            service.getFollowers(invalidRequest);
        });
    }
}

