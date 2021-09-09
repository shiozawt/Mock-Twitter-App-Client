package edu.byu.cs.tweeter.presenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.IsFollowingService;
import edu.byu.cs.tweeter.model.service.request.IsFollowingRequest;
import edu.byu.cs.tweeter.model.service.response.IsFollowingResponse;

public class IsFollowingPresenterTest {
    private IsFollowingRequest request;
    private IsFollowingResponse response;
    private IsFollowingService mockIsFollowingService;
    private IsFollowingPresenter presenter;

    @BeforeEach
    public void setup() throws IOException {
        User curUser = new User("cur", "User",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        User otherUser = new User("other", "User",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        request = new IsFollowingRequest(curUser, otherUser, "fakeToken");
        response = new IsFollowingResponse(true);

        mockIsFollowingService = Mockito.mock(IsFollowingService.class);

        presenter = Mockito.spy(new IsFollowingPresenter(new IsFollowingPresenter.View() {}));
        Mockito.when(presenter.getIsFollowingService()).thenReturn(mockIsFollowingService);
    }

    @Test
    public void testIsFollowing_returnsServiceResult() throws IOException, TweeterRemoteException {
        Mockito.when(mockIsFollowingService.isFollowing(request)).thenReturn(response);
        // Assert that the presenter returns the same response as the service (it doesn't do
        // anything else, so there's nothing else to test).
        Assertions.assertEquals(response, presenter.isFollowing(request));
    }

    @Test
    public void testIsFollowing_serviceThrowsIOException_presenterThrowsIOException() throws IOException, TweeterRemoteException {
        Mockito.when(mockIsFollowingService.isFollowing(request)).thenThrow(new IOException());

        Assertions.assertThrows(IOException.class, () -> {
            presenter.isFollowing(request);
        });
    }
}
