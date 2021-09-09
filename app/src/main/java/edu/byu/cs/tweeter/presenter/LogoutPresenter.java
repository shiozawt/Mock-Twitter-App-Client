package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.LoginService;
import edu.byu.cs.tweeter.model.service.LogoutService;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;


public class LogoutPresenter {

    private final View view;


    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    public LogoutPresenter(View view) {
        this.view = view;
    }


    public LogoutResponse logout(LogoutRequest logoutRequest) throws IOException, TweeterRemoteException {
        LogoutService logoutService = getLogoutService();
        return logoutService.logout(logoutRequest);
    }

    LogoutService getLogoutService() {
        return new LogoutService();
    }
}
