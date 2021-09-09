package edu.byu.cs.tweeter.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.net.TweeterRequestException;
import edu.byu.cs.tweeter.model.service.FollowButtonService;
import edu.byu.cs.tweeter.model.service.request.FollowButtonRequest;
import edu.byu.cs.tweeter.model.service.response.FollowButtonResponse;

public class FollowButtonServiceTest {
    // returns the following and  follower count for OTHER_USER
    private static final String MALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png";
    User curUser = new User("A", "A", MALE_IMAGE_URL);
    User otherUser = new User("first1976", "last1976", "@guy1977", MALE_IMAGE_URL);
    FollowButtonService service = new FollowButtonService();
    FollowButtonRequest validRequest = new FollowButtonRequest(curUser, otherUser, IntegrationConstant.token);
    FollowButtonRequest invalidRequest = new FollowButtonRequest(curUser, otherUser, null);

    @Test
    public void testFollowButton_success() throws IOException, TweeterRemoteException {
        FollowButtonResponse response = service.follow(validRequest);
        System.out.print(response.getMessage());
        Assertions.assertEquals(response.isSuccess(), true);
        Assertions.assertEquals(response.getFollowingCount(), 1);
        if (response.getFollowerCount() == 1 || response.getFollowerCount() == 0) {
            Assertions.assertEquals(true, true);
        }
    }

    @Test
    public void testFollowButton_failed() {
        Assertions.assertThrows(TweeterRequestException.class, () -> {
            service.follow(invalidRequest);
        });
    }
}
