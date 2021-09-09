package edu.byu.cs.tweeter.view.asyncTasks;
import android.util.Log;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.presenter.LogoutPresenter;

public class LogoutTask extends TemplateAsyncTask<LogoutRequest, LogoutResponse> {
    private final LogoutPresenter presenter;
    private final Observer observer;

    public interface Observer {
        void logoutSuccessful(LogoutResponse logoutResponse);
        void handleException(Exception ex);
    }

    public LogoutTask(LogoutPresenter presenter, Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    LogoutResponse sendRequest(LogoutRequest[] requests) throws Exception {
        return presenter.logout(requests[0]);
    }

    @Override
    public void handleResponse(LogoutResponse response) {
        if (response.isSuccess()) {
            observer.logoutSuccessful(response);
        }
    }

    @Override
    public void handleException(Exception ex) {
        Log.d("LogoutTask: ", ex.toString());
    }
}
