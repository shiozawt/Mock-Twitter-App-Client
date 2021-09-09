package edu.byu.cs.tweeter.view.asyncTasks;
import android.util.Log;
import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import edu.byu.cs.tweeter.model.service.response.FollowerResponse;
import edu.byu.cs.tweeter.presenter.FollowerPresenter;

public class GetFollowerTask extends TemplateAsyncTask<FollowerRequest, FollowerResponse> {
    private final FollowerPresenter presenter;
    private final Observer observer;

    public interface Observer {
        void followersRetrieved(FollowerResponse followerResponse);
        void handleException(Exception exception);
    }

    public GetFollowerTask(FollowerPresenter presenter, Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    FollowerResponse sendRequest(FollowerRequest[] requests) throws Exception {
        return presenter.getFollower(requests[0]);
    }

    @Override
    public void handleResponse(FollowerResponse response) {
        if (response.isSuccess()) {
            observer.followersRetrieved(response);
        }
    }

    @Override
    public void handleException(Exception ex) {
        Log.d("GetFollowerTask: ", ex.toString());
    }
}
