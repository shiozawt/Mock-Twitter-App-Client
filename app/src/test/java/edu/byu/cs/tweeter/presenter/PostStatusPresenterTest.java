package edu.byu.cs.tweeter.presenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.PostStatusService;
import edu.byu.cs.tweeter.model.service.request.PostStatusRequest;
import edu.byu.cs.tweeter.model.service.response.PostStatusResponse;
import edu.byu.cs.tweeter.view.main.PostStatus;

public class PostStatusPresenterTest {
    private PostStatusRequest request;
    private PostStatusResponse response;
    private PostStatusService mockPostStatusService;
    private PostStatusPresenter presenter;

    @BeforeEach
    public void setup() throws IOException, TweeterRemoteException {

        User user = new User("FirstName", "LastName", "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        String content = "testMessage";

        Date date = Calendar.getInstance().getTime();
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        DateFormat timeFormat = new SimpleDateFormat("h:mm aa");
        String timeString = timeFormat.format(date);

        DateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
        String dateString = dateFormat.format(date);

        request = new PostStatusRequest("alias", "date", "time", "content", "fakeToken");
        response = new PostStatusResponse();

        // Create a mock PostStatusService
        mockPostStatusService = Mockito.mock(PostStatusService.class);
        Mockito.when(mockPostStatusService.getPostStatus(request)).thenReturn(response);

        // Wrap a PostStatusPresenter in a spy that will use the mock service.
        presenter = Mockito.spy(new PostStatusPresenter(new PostStatusPresenter.View() {}));
        Mockito.when(presenter.getPostStatusService()).thenReturn(mockPostStatusService);
    }

    @Test
    public void testGetPostStatus_returnsServiceResult() throws IOException, TweeterRemoteException {
        Mockito.when(mockPostStatusService.getPostStatus(request)).thenReturn(response);

        // Assert that the presenter returns the same response as the service (it doesn't do
        // anything else, so there's nothing else to test).
        Assertions.assertEquals(response, presenter.getPostStatus(request));
    }

    @Test
    public void testGetPostStatus_serviceThrowsIOException_presenterThrowsIOException() throws IOException, TweeterRemoteException {
        Mockito.when(mockPostStatusService.getPostStatus(request)).thenThrow(new IOException());

        Assertions.assertThrows(IOException.class, () -> {
            presenter.getPostStatus(request);
        });
    }
}
