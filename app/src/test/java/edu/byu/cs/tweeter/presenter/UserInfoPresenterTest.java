package edu.byu.cs.tweeter.presenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.UserInfoService;
import edu.byu.cs.tweeter.model.service.request.UserInfoRequest;
import edu.byu.cs.tweeter.model.service.response.UserInfoResponse;

public class UserInfoPresenterTest {
    private UserInfoRequest request;
    private UserInfoResponse response;
    private UserInfoService mockUserInfoService;
    private UserInfoPresenter presenter;

    @BeforeEach
    public void setup() throws IOException {
        User testUser = new User("test", "User",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        request = new UserInfoRequest("@testUser", "fakeToken");
        response = new UserInfoResponse(testUser);

        mockUserInfoService = Mockito.mock(UserInfoService.class);

        presenter = Mockito.spy(new UserInfoPresenter(new UserInfoPresenter.View() {}));
        Mockito.when(presenter.getUserInfoService()).thenReturn(mockUserInfoService);
    }

    @Test
    public void testGetUserInfo_returnsServiceResult() throws IOException, TweeterRemoteException {
        Mockito.when(mockUserInfoService.getUserInfo(request)).thenReturn(response);
        // Assert that the presenter returns the same response as the service (it doesn't do
        // anything else, so there's nothing else to test).
        Assertions.assertEquals(response, presenter.getUserInfo(request));
    }

    @Test
    public void testGetUserInfo_serviceThrowsIOException_presenterThrowsIOException() throws IOException, TweeterRemoteException {
        Mockito.when(mockUserInfoService.getUserInfo(request)).thenThrow(new IOException());

        Assertions.assertThrows(IOException.class, () -> {
            presenter.getUserInfo(request);
        });
    }
}
