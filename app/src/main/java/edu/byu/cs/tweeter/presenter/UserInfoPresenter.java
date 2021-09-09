package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.UserInfoService;
import edu.byu.cs.tweeter.model.service.proxyInterface.IUserInfoService;
import edu.byu.cs.tweeter.model.service.request.UserInfoRequest;
import edu.byu.cs.tweeter.model.service.response.UserInfoResponse;

public class UserInfoPresenter {
    private final View view;

    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    public UserInfoPresenter(View view) {
        this.view = view;
    }

    public UserInfoResponse getUserInfo(UserInfoRequest userInfoRequest) throws IOException, TweeterRemoteException {
        IUserInfoService userInfoService = getUserInfoService();
        return userInfoService.getUserInfo(userInfoRequest);
    }

    UserInfoService getUserInfoService() {
        return new UserInfoService();
    }
}
