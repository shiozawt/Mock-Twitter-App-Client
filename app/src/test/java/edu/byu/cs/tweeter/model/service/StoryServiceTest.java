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
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;

public class StoryServiceTest {
    private StoryRequest validRequest;
    private StoryRequest invalidRequest;

    private StoryResponse successResponse;
    private StoryResponse failureResponse;

    private StoryService storyServiceSpy;

    /**
     * Create a StoryService spy that uses a mock ServerFacade to return known responses to
     * requests.
     */
    @BeforeEach
    public void setup() throws IOException, TweeterRemoteException {
        User currentUser = new User("FirstName", "LastName", null);

        User resultUser1 = new User("FirstName1", "LastName1",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        User resultUser2 = new User("FirstName2", "LastName2",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");
        User resultUser3 = new User("FirstName3", "LastName3",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");

        Status status1 = new Status(resultUser1, "01/01/2021", "10:00 PM", "hi @HenryHenderson");
        Status status2 = new Status(resultUser2, "01/01/2021", "10:00 PM", "simple content");
        Status status3 = new Status(resultUser3, "01/01/2021", "10:00 PM", "content8");

        // Setup request objects to use in the tests
        validRequest = new StoryRequest(currentUser.getAlias(), 3, null, "fakeToken");
        invalidRequest = new StoryRequest(null, 0, null, "fakeToken");

        // Setup a mock ServerFacade that will return known responses
        successResponse = new StoryResponse(Arrays.asList(status1,status2,status3), false);
        ServerFacade mockServerFacade = Mockito.mock(ServerFacade.class);
        Mockito.when(mockServerFacade.getStory(validRequest, StoryService.URL_PATH)).thenReturn(successResponse);

        failureResponse = new StoryResponse("An exception occurred");
        Mockito.when(mockServerFacade.getStory(invalidRequest, StoryService.URL_PATH)).thenReturn(failureResponse);

        // Create a StoryService instance and wrap it with a spy that will use the mock service
        storyServiceSpy = Mockito.spy(new StoryService());
        Mockito.when(storyServiceSpy.getServerFacade()).thenReturn(mockServerFacade);
    }

    @Test
    public void testGetStory_validRequest_correctResponse() throws IOException, TweeterRemoteException {
        StoryResponse response = storyServiceSpy.getStory(validRequest);
        Assertions.assertEquals(successResponse, response);
    }

    @Test
    public void testGetStory_validRequest_loadsProfileImages() throws IOException, TweeterRemoteException {
        StoryResponse response = storyServiceSpy.getStory(validRequest);

        for(Status status : response.getStory()) {
            User user = status.getUser();
            Assertions.assertNotNull(user.getImageBytes());
        }
    }

    @Test
    public void testGetStory_invalidRequest_returnsNoStory() throws IOException, TweeterRemoteException {
        StoryResponse response = storyServiceSpy.getStory(invalidRequest);
        Assertions.assertEquals(failureResponse, response);
    }
}

