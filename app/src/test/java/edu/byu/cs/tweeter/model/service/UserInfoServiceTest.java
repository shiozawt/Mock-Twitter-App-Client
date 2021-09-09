package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.UserInfoRequest;
import edu.byu.cs.tweeter.model.service.response.UserInfoResponse;

public class UserInfoServiceTest {
    private UserInfoRequest validRequest;
    private UserInfoRequest invalidRequest;

    private UserInfoResponse successResponse;
    private UserInfoResponse failResponse;

    private UserInfoService userInfoServiceSpy;

    @BeforeEach
    public void setup() throws IOException, TweeterRemoteException {

        User testUser = new User("test", "User",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png", 1, 1);

        validRequest = new UserInfoRequest("@testUser", "fakeToken");
        invalidRequest = new UserInfoRequest(null, "fakeToken");

        successResponse = new UserInfoResponse(testUser);
        ServerFacade mockServerFacade = Mockito.mock(ServerFacade.class);
        Mockito.when(mockServerFacade.getUserInfo(validRequest, UserInfoService.URL_PATH)).thenReturn(successResponse);

        failResponse = new UserInfoResponse("throw exception");
        Mockito.when(mockServerFacade.getUserInfo(invalidRequest, UserInfoService.URL_PATH)).thenReturn(failResponse);

        userInfoServiceSpy = Mockito.spy(new UserInfoService());
        Mockito.when(userInfoServiceSpy.getServerFacade()).thenReturn(mockServerFacade);
    }

    @Test
    public void testGetUserInfo_validRequest_correctResponse() throws IOException, TweeterRemoteException {
        UserInfoResponse response = userInfoServiceSpy.getUserInfo(validRequest);
        Assertions.assertEquals(successResponse, response);
    }

    @Test
    public void testGetUserInfo_invalidRequest() throws IOException, TweeterRemoteException {
        UserInfoResponse response = userInfoServiceSpy.getUserInfo(invalidRequest);
        Assertions.assertEquals(failResponse, response);
    }
}
