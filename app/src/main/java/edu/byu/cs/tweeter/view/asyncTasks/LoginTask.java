package edu.byu.cs.tweeter.view.asyncTasks;
import android.util.Log;
import java.io.IOException;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.presenter.LoginPresenter;
import edu.byu.cs.tweeter.util.ByteArrayUtils;

public class LoginTask extends TemplateAsyncTask<LoginRequest, LoginResponse>{
    private final LoginPresenter presenter;
    private final Observer observer;

    /**
     * An observer interface to be implemented by observers who want to be notified when this task
     * completes.
     */
    public interface Observer {
        void loginSuccessful(LoginResponse loginResponse);
        void loginUnsuccessful(LoginResponse loginResponse);
        void handleException(Exception ex);
    }

    public LoginTask(LoginPresenter presenter, Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    LoginResponse sendRequest(LoginRequest[] requests) throws Exception {
        LoginResponse response = presenter.login(requests[0]);
        if (response.isSuccess()) {
            loadImage(response.getUser());
        }

        return response;
    }

    private void loadImage(User user) {
        try {
            byte [] bytes = ByteArrayUtils.bytesFromUrl(user.getImageUrl());
            user.setImageBytes(bytes);
        } catch (IOException e) {
            Log.e(this.getClass().getName(), e.toString(), e);
        }
    }

    @Override
    public void handleResponse(LoginResponse response) {
        if (response.isSuccess()) {
            observer.loginSuccessful(response);
        }
        else {
            observer.loginUnsuccessful(response);
        }
    }

    @Override
    public void handleException(Exception ex) {
        Log.d("LoginTask: ", ex.toString());
    }
}
