package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.PostStatusRequest;
import edu.byu.cs.tweeter.model.service.response.PostStatusResponse;

public class PostStatusServiceTest {
    private PostStatusRequest validRequest;
    private PostStatusRequest invalidRequest;

    private PostStatusResponse successResponse;
    private PostStatusResponse failureResponse;

    private PostStatusService postStatusServiceSpy;

    /**
     * Create a PostStatusService spy that uses a mock ServerFacade to return known responses to
     * requests.
     */
    @BeforeEach
    public void setup() throws IOException, TweeterRemoteException {
        User resultUser1 = new User("FirstName1", "LastName1",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        // Setup request objects to use in the tests
        validRequest = new PostStatusRequest("alias", "date", "time", "content", "fakeToken");
        invalidRequest = new PostStatusRequest(null, null, null, null, "fakeToken");

        // Setup a mock ServerFacade that will return known responses
        successResponse = new PostStatusResponse();
        ServerFacade mockServerFacade = Mockito.mock(ServerFacade.class);
        Mockito.when(mockServerFacade.postStatus(validRequest, PostStatusService.URL_PATH)).thenReturn(successResponse);

        failureResponse = new PostStatusResponse("An exception occurred");
        Mockito.when(mockServerFacade.postStatus(invalidRequest, PostStatusService.URL_PATH)).thenReturn(failureResponse);

        // Create a PostStatusService instance and wrap it with a spy that will use the mock service
        postStatusServiceSpy = Mockito.spy(new PostStatusService());
        Mockito.when(postStatusServiceSpy.getServerFacade()).thenReturn(mockServerFacade);
    }

    @Test
    public void testGetPostStatus_validRequest_correctResponse() throws IOException, TweeterRemoteException {
        PostStatusResponse response = postStatusServiceSpy.getPostStatus(validRequest);
        Assertions.assertEquals(successResponse, response);
    }

    @Test
    public void testGetPostStatus_validRequest_checksSuccess() throws IOException, TweeterRemoteException {
        PostStatusResponse response = postStatusServiceSpy.getPostStatus(validRequest);

        Boolean success = response.isSuccess();
        Assertions.assertTrue(success);
    }

    @Test
    public void testGetPostStatus_invalidRequest_returnsNoPostStatus() throws IOException, TweeterRemoteException {
        PostStatusResponse response = postStatusServiceSpy.getPostStatus(invalidRequest);
        Assertions.assertEquals(failureResponse, response);
    }
}

