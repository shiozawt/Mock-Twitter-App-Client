package edu.byu.cs.tweeter.presenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.FollowButtonService;
import edu.byu.cs.tweeter.model.service.FollowingService;
import edu.byu.cs.tweeter.model.service.request.FollowButtonRequest;
import edu.byu.cs.tweeter.model.service.response.FollowButtonResponse;

public class FollowButtonPresenterTest {
    private FollowButtonRequest request;
    private FollowButtonResponse response;
    private FollowButtonService mockFollowButtonService;
    private FollowButtonPresenter presenter;

    @BeforeEach
    public void setup() throws IOException {
        User curUser = new User("cur", "User",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        User otherUser = new User("other", "User",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");


        request = new FollowButtonRequest(curUser, otherUser, "fakeToken");
        response = new FollowButtonResponse(1, 3);

        mockFollowButtonService = Mockito.mock(FollowButtonService.class);

        presenter = Mockito.spy(new FollowButtonPresenter(new FollowButtonPresenter.View() {}));
        Mockito.when(presenter.getFollowButtonSerive()).thenReturn(mockFollowButtonService);
    }

    @Test
    public void testFollow_returnsServiceResult() throws IOException, TweeterRemoteException {
        Mockito.when(mockFollowButtonService.follow(request)).thenReturn(response);

        // Assert that the presenter returns the same response as the service (it doesn't do
        // anything else, so there's nothing else to test).
        Assertions.assertEquals(response, presenter.follow(request));
    }

    @Test
    public void testFollow_serviceThrowsIOException_presenterThrowsIOException() throws IOException, TweeterRemoteException {
        Mockito.when(mockFollowButtonService.follow(request)).thenThrow(new IOException());

        Assertions.assertThrows(IOException.class, () -> {
            presenter.follow(request);
        });
    }
}
