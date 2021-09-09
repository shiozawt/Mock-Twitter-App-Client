package edu.byu.cs.tweeter.view.asyncTasks;
import android.util.Log;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.presenter.FollowingPresenter;

public class GetFollowingTask extends TemplateAsyncTask<FollowingRequest, FollowingResponse> {
    private final FollowingPresenter presenter;
    private final Observer observer;

    public interface Observer {
        void followeesRetrieved(FollowingResponse followingResponse);
        void handleException(Exception exception);
    }

    public GetFollowingTask(FollowingPresenter presenter, Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    FollowingResponse sendRequest(FollowingRequest[] requests) throws Exception {
        return presenter.getFollowing(requests[0]);
    }

    @Override
    public void handleResponse(FollowingResponse response) {
        if (response.isSuccess()) {
            observer.followeesRetrieved(response);
        }
    }

    @Override
    public void handleException(Exception ex) {
        Log.d("GetFollowingTask: ", ex.toString());
    }
}
