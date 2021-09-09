package edu.byu.cs.tweeter.view.asyncTasks;
import android.util.Log;
import edu.byu.cs.tweeter.model.service.request.UserInfoRequest;
import edu.byu.cs.tweeter.model.service.response.UserInfoResponse;
import edu.byu.cs.tweeter.presenter.UserInfoPresenter;

public class GetUserInfoTask extends TemplateAsyncTask<UserInfoRequest, UserInfoResponse> {
    private final UserInfoPresenter presenter;
    private final GetUserInfoTask.Observer observer;

    public interface Observer {
        void getUserInfoSuccessful(UserInfoResponse userInfoResponse);
        void handleException(Exception ex);
    }

    public GetUserInfoTask(UserInfoPresenter presenter, Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    UserInfoResponse sendRequest(UserInfoRequest[] requests) throws Exception {
        return presenter.getUserInfo(requests[0]);
    }

    @Override
    public void handleResponse(UserInfoResponse response) {
        if (response.isSuccess()) {
            observer.getUserInfoSuccessful(response);
        }
    }

    @Override
    public void handleException(Exception ex) {
        Log.d("GetUserInfoTask: ", ex.toString());
    }
}
