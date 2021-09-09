package edu.byu.cs.tweeter.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.RegisterService;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;

public class RegisterServiceTest {
    RegisterService service = new RegisterService();
    private static final String MALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png";
    RegisterRequest validRequest = new RegisterRequest("integration", "test",
            "testUser4",
            "IntegrationTestPassword", MALE_IMAGE_URL);

    @Test
    public void testRegister_success() throws IOException, TweeterRemoteException {
        LoginResponse response = service.register(validRequest);
        Assertions.assertEquals(response.isSuccess(), true);
        Assertions.assertNotNull(response.getAuthToken());
        Assertions.assertEquals(response.getUser().getFirstName(), "integration");
        Assertions.assertEquals(response.getUser().getLastName(), "test");
    }
}
