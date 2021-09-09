package edu.byu.cs.tweeter.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.net.TweeterRequestException;
import edu.byu.cs.tweeter.model.service.FollowingService;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;

public class FollowingServiceTest {
    FollowingService service = new FollowingService();
    FollowingRequest validRequest = new FollowingRequest("@AA", 10, null, IntegrationConstant.token);
    FollowingRequest invalidRequest = new FollowingRequest("@AA", 10, null, null);

    @Test
    public void testGetFollowing_success() throws IOException, TweeterRemoteException {
        FollowingResponse response = service.getFollowees(validRequest);
        Assertions.assertEquals(response.isSuccess(), true);
    }

    @Test
    public void testGetFollowing_failed() {
        Assertions.assertThrows(TweeterRequestException.class, () -> {
            service.getFollowees(invalidRequest);
        });
    }
}
