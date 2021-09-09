package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.FollowButtonRequest;
import edu.byu.cs.tweeter.model.service.response.FollowButtonResponse;

public class FollowButtonServiceTest {
    private FollowButtonRequest validRequest;
    private FollowButtonRequest invalidRequest;
    private FollowButtonResponse successResponse;
    private FollowButtonResponse failureResponse;
    private FollowButtonService followButtonServiceSpy;

    @BeforeEach
    public void setup() throws IOException, TweeterRemoteException {
        User curUser = new User("cur", "User",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        User otherUser = new User("other", "User",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        validRequest = new FollowButtonRequest(curUser, otherUser, "fakeToken");
        invalidRequest = new FollowButtonRequest(null, null, "fakeToken");

        successResponse = new FollowButtonResponse(1, 1);
        ServerFacade mockServerFacade = Mockito.mock(ServerFacade.class);
        Mockito.when(mockServerFacade.follow(validRequest, FollowButtonService.URL_PATH)).thenReturn(successResponse);

        failureResponse = new FollowButtonResponse("exception thrown");
        Mockito.when(mockServerFacade.follow(invalidRequest, FollowButtonService.URL_PATH)).thenReturn(failureResponse);

        followButtonServiceSpy = Mockito.spy(new FollowButtonService());
        Mockito.when(followButtonServiceSpy.getServerFacade()).thenReturn(mockServerFacade);
    }

    @Test
    public void testFollow_validRequest_correctResponse() throws IOException, TweeterRemoteException {
        FollowButtonResponse response = followButtonServiceSpy.follow(validRequest);
        Assertions.assertEquals(successResponse, response);
    }

    @Test
    public void testFollow_invalidRequest() throws IOException, TweeterRemoteException {
        FollowButtonResponse response = followButtonServiceSpy.follow(invalidRequest);
        Assertions.assertEquals(failureResponse, response);
    }
}
