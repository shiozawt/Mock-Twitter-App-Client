package edu.byu.cs.tweeter.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.LoginService;
import edu.byu.cs.tweeter.model.service.LogoutService;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.view.session.Session;

public class LoginLogoutServiceTest {
    LoginService loginService = new LoginService();
    LoginRequest validLoginRequest = new LoginRequest("AA", "password");
    LogoutService logoutService = new LogoutService();
    LogoutRequest validLogoutRequest = new LogoutRequest("token");

    @Test
    public void testLoginLogout_success() throws IOException, TweeterRemoteException {
        LoginResponse loginResponse = loginService.login(validLoginRequest);
        Assertions.assertEquals(loginResponse.isSuccess(), true);
        Assertions.assertNotNull(loginResponse.getAuthToken());
        Assertions.assertEquals(loginResponse.getUser().getFirstName(), "A");
        Assertions.assertEquals(loginResponse.getUser().getLastName(), "A");

        AuthToken token = new AuthToken(loginResponse.getAuthToken().getToken());
        Session.getInstance().setAuthToken(token);
        LogoutResponse logoutResponse = logoutService.logout(validLogoutRequest);
        Assertions.assertEquals(logoutResponse.isSuccess(), true);
    }
}
