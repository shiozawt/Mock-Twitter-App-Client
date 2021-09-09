package edu.byu.cs.tweeter.view.asyncTasks;
import android.util.Log;
import edu.byu.cs.tweeter.model.service.request.FollowButtonRequest;
import edu.byu.cs.tweeter.model.service.response.FollowButtonResponse;
import edu.byu.cs.tweeter.presenter.FollowButtonPresenter;

public class FollowButtonTask extends TemplateAsyncTask<FollowButtonRequest, FollowButtonResponse> {
    private final FollowButtonPresenter presenter;
    private final Observer observer;


    public interface Observer {
        void followButtonSuccessful(FollowButtonResponse followButtonResponse);
        void handleException(Exception ex);
    }

    public FollowButtonTask(FollowButtonPresenter presenter, Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    FollowButtonResponse sendRequest(FollowButtonRequest[] requests) throws Exception {
        return presenter.follow(requests[0]);
    }

    @Override
    public void handleResponse(FollowButtonResponse response) {
        if (response.isSuccess()) {
            observer.followButtonSuccessful(response);
        }
    }

    @Override
    public void handleException(Exception ex) {
        Log.d("FollowButtonTask: ", ex.toString());
    }
}
