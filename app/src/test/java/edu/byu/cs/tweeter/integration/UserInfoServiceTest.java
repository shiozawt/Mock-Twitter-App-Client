package edu.byu.cs.tweeter.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.net.TweeterRequestException;
import edu.byu.cs.tweeter.model.service.UserInfoService;
import edu.byu.cs.tweeter.model.service.request.UserInfoRequest;
import edu.byu.cs.tweeter.model.service.response.UserInfoResponse;

public class UserInfoServiceTest {
    UserInfoService service = new UserInfoService();
    UserInfoRequest validRequest = new UserInfoRequest("@AA", IntegrationConstant.token);
    UserInfoRequest invalidRequest = new UserInfoRequest("@AA", null);

    @Test
    public void testGetUserInfo_success() throws IOException, TweeterRemoteException {
        UserInfoResponse response = service.getUserInfo(validRequest);
        Assertions.assertEquals(response.isSuccess(), true);
        Assertions.assertEquals(response.getUser().getFirstName(), "A");
        Assertions.assertEquals(response.getUser().getLastName(), "A");
    }

    @Test
    public void testGetUserInfo_failed() {
        Assertions.assertThrows(TweeterRequestException.class, () -> {
            service.getUserInfo(invalidRequest);
        });
    }
}
