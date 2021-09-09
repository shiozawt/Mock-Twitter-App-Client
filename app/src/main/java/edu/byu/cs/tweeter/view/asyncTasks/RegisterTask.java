package edu.byu.cs.tweeter.view.asyncTasks;
import android.util.Log;
import java.io.IOException;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.presenter.RegisterPresenter;
import edu.byu.cs.tweeter.util.ByteArrayUtils;

public class RegisterTask extends TemplateAsyncTask<RegisterRequest, LoginResponse> {
    private final RegisterPresenter presenter;
    private final Observer observer;

    public interface Observer {
        void registerSuccessful(LoginResponse loginResponse);
        void registerUnsuccessful(LoginResponse loginResponse);
        void handleException(Exception ex);
    }

    public RegisterTask(RegisterPresenter presenter, Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    LoginResponse sendRequest(RegisterRequest[] requests) throws Exception {
        LoginResponse response = presenter.register(requests[0]);

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
            observer.registerSuccessful(response);
        }
        else {
            observer.registerUnsuccessful(response);
        }
    }

    @Override
    public void handleException(Exception ex) {
        Log.d("RegisterTask: ", ex.toString());
    }
}
