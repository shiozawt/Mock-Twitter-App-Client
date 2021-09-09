package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.IsFollowingRequest;
import edu.byu.cs.tweeter.model.service.response.IsFollowingResponse;

public class IsFollowingServiceTest {
    private IsFollowingRequest validRequest;
    private IsFollowingRequest invalidRequest;
    private IsFollowingResponse successResponse;
    private IsFollowingResponse failureResponse;
    private IsFollowingService isFollowingServiceSpy;

    @BeforeEach
    public void setup() throws IOException, TweeterRemoteException {

        User curUser = new User("cur", "User",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        User otherUser = new User("other", "User",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        validRequest = new IsFollowingRequest(curUser, otherUser, "fakeToken");
        invalidRequest = new IsFollowingRequest(null, null, "fakeToken");

        successResponse = new IsFollowingResponse(true);
        ServerFacade mockServerFacade = Mockito.mock(ServerFacade.class);
        Mockito.when(mockServerFacade.isFollowing(validRequest, IsFollowingService.URL_PATH)).thenReturn(successResponse);

        failureResponse = new IsFollowingResponse("An exception occurred");
        Mockito.when(mockServerFacade.isFollowing(invalidRequest, IsFollowingService.URL_PATH)).thenReturn(failureResponse);

        isFollowingServiceSpy = Mockito.spy(new IsFollowingService());
        Mockito.when(isFollowingServiceSpy.getServerFacade()).thenReturn(mockServerFacade);
    }

    @Test
    public void testIsFollowing_validRequest_correctResponse() throws IOException, TweeterRemoteException {
        IsFollowingResponse response = isFollowingServiceSpy.isFollowing(validRequest);
        Assertions.assertEquals(successResponse, response);
    }

    @Test
    public void testIsFollowing_invalidRequest() throws IOException, TweeterRemoteException {
        IsFollowingResponse response = isFollowingServiceSpy.isFollowing(invalidRequest);
        Assertions.assertEquals(failureResponse, response);
    }
}
