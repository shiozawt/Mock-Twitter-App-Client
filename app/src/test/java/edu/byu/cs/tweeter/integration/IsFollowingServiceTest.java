package edu.byu.cs.tweeter.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.net.TweeterRequestException;
import edu.byu.cs.tweeter.model.service.IsFollowingService;
import edu.byu.cs.tweeter.model.service.request.IsFollowingRequest;
import edu.byu.cs.tweeter.model.service.response.IsFollowingResponse;

public class IsFollowingServiceTest {
    private static final String MALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png";
    User curUser = new User("A", "A", MALE_IMAGE_URL);
    User otherUser = new User("firstname", "lastname", "@guy1976", MALE_IMAGE_URL);
    IsFollowingService service = new IsFollowingService();
    IsFollowingRequest validRequest = new IsFollowingRequest(curUser, otherUser, IntegrationConstant.token);
    IsFollowingRequest invalidRequest = new IsFollowingRequest(curUser, otherUser, null);

    @Test
    public void testIsFollowing_success() throws IOException, TweeterRemoteException {
        IsFollowingResponse response = service.isFollowing(validRequest);
        Assertions.assertEquals(response.isSuccess(), true);
        Assertions.assertEquals(response.getIsFollowing(), false);
    }

    @Test
    public void testIsFollowing_failed() {
        Assertions.assertThrows(TweeterRequestException.class, () -> {
            service.isFollowing(invalidRequest);
        });
    }
}
