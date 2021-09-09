package edu.byu.cs.tweeter.view.asyncTasks;
import android.util.Log;
import edu.byu.cs.tweeter.model.service.request.IsFollowingRequest;
import edu.byu.cs.tweeter.model.service.response.IsFollowingResponse;
import edu.byu.cs.tweeter.presenter.IsFollowingPresenter;

public class IsFollowingTask extends TemplateAsyncTask<IsFollowingRequest, IsFollowingResponse> {
    private final IsFollowingPresenter presenter;
    private final IsFollowingTask.Observer observer;

    public interface Observer {
        void isFollowingRetrieved(IsFollowingResponse isFollowingResponse);
        void handleException(Exception ex);
    }

    public IsFollowingTask(IsFollowingPresenter presenter, Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    IsFollowingResponse sendRequest(IsFollowingRequest[] requests) throws Exception {
        return presenter.isFollowing(requests[0]);
    }

    @Override
    public void handleResponse(IsFollowingResponse response) {
        if (response.isSuccess()) {
            observer.isFollowingRetrieved(response);
        }
    }

    @Override
    public void handleException(Exception ex) {
        Log.d("IsFollowingTask: ", ex.toString());
    }
}
