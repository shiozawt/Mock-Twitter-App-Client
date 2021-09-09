package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;

public class RegisterServiceTest {
    private RegisterRequest validRequest;
    private RegisterRequest invalidRequest;

    private LoginResponse successResponse;
    private LoginResponse failureResponse;

    private RegisterService registerServiceSpy;

    @BeforeEach
    public void setup() throws IOException, TweeterRemoteException {
        String firstname = "donald";
        String lastname = "duck";
        String username1 = "duck";
        String password1 = "pass1";
        String picture = "4567890uytgnbvfcdse4567ik765redfghjkop98u7ytrew345tyujmnbvcdfghjkio765rghjhbvcdf"; //Its a base64 string
        User currentUser = new User("Donald", "Duck", "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");

        // Setup request objects to use in the tests
        validRequest = new RegisterRequest(firstname, lastname, username1, password1, picture);
        invalidRequest = new RegisterRequest(null, null, null, null, null);

        // Setup a mock ServerFacade that will return known responses
        successResponse = new LoginResponse(currentUser, new AuthToken("fakeToken"));
        ServerFacade mockServerFacade = Mockito.mock(ServerFacade.class);
        Mockito.when(mockServerFacade.register(validRequest, RegisterService.URL_PATH)).thenReturn(successResponse);

        failureResponse = new LoginResponse("An exception occurred");
        Mockito.when(mockServerFacade.register(invalidRequest, RegisterService.URL_PATH)).thenReturn(failureResponse);

        // Create a FollowingService instance and wrap it with a spy that will use the mock service
        registerServiceSpy = Mockito.spy(new RegisterService());
        Mockito.when(registerServiceSpy.getServerFacade()).thenReturn(mockServerFacade);
    }


    @Test
    public void testRegister_validRequest_correctResponse() throws IOException, TweeterRemoteException {
        LoginResponse response = registerServiceSpy.register(validRequest);
        Assertions.assertEquals(successResponse, response);
    }

    @Test
    public void testRegister_validRequest_loadsProfileImages() throws IOException, TweeterRemoteException {
        LoginResponse response = registerServiceSpy.register(validRequest);
        Assertions.assertNotNull(response.getUser().getImageBytes());
    }

    @Test
    public void testRegister_invalidRequest_failure() throws IOException, TweeterRemoteException {
        LoginResponse response = registerServiceSpy.register(invalidRequest);
        Assertions.assertEquals(failureResponse, response);
    }
}
