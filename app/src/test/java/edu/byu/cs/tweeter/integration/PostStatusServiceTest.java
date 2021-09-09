package edu.byu.cs.tweeter.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.PostStatusService;
import edu.byu.cs.tweeter.model.service.request.PostStatusRequest;
import edu.byu.cs.tweeter.model.service.response.PostStatusResponse;

public class PostStatusServiceTest {
    PostStatusService service = new PostStatusService();
    PostStatusRequest validRequest = new PostStatusRequest("@guy1976", "APR 15 2050", "3:00 PM",
            "testing integration for @guy1976 posting a status",
            IntegrationConstant.token);
    PostStatusRequest invalidRequest = new PostStatusRequest(null, null, null, null, null);

    @Test
    public void testPostStatus_success() throws IOException, TweeterRemoteException {
        PostStatusResponse response = service.getPostStatus(validRequest);
        Assertions.assertEquals(response.isSuccess(), true);
        Assertions.assertNull(response.getMessage());
    }
}
