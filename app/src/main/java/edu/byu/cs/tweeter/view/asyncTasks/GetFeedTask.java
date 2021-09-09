package edu.byu.cs.tweeter.view.asyncTasks;
import android.util.Log;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.presenter.FeedPresenter;

public class GetFeedTask extends TemplateAsyncTask<FeedRequest, FeedResponse> {
    private final FeedPresenter presenter;
    private final GetFeedTask.Observer observer;

    public interface Observer {
        void feedRetrieved(FeedResponse feedResponse);
        void handleException(Exception exception);
    }

    public GetFeedTask(FeedPresenter presenter, GetFeedTask.Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    FeedResponse sendRequest(FeedRequest[] requests) throws Exception {
        return presenter.getFeed(requests[0]);
    }

    @Override
    public void handleResponse(FeedResponse response) {
        if (response.isSuccess()) {
            observer.feedRetrieved(response);
        }
    }

    @Override
    public void handleException(Exception ex) {
        Log.d("GetFeedTask: ", ex.toString());
    }
}
