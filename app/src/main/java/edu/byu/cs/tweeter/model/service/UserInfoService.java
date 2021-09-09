package edu.byu.cs.tweeter.model.service;
import java.io.IOException;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.proxyInterface.IUserInfoService;
import edu.byu.cs.tweeter.model.service.request.UserInfoRequest;
import edu.byu.cs.tweeter.model.service.response.UserInfoResponse;
import edu.byu.cs.tweeter.util.ByteArrayUtils;

public class UserInfoService implements IUserInfoService {

    static final String URL_PATH = "/get-user-info";

    public UserInfoResponse getUserInfo(UserInfoRequest request) throws IOException, TweeterRemoteException {
        UserInfoResponse response = getServerFacade().getUserInfo(request, URL_PATH);

        if(response.isSuccess()) {
            loadImages(response);
        }

        return response;
    }

    private void loadImages(UserInfoResponse response) throws IOException {
        User user = response.getUser();
        byte [] bytes = ByteArrayUtils.bytesFromUrl(user.getImageUrl());
        user.setImageBytes(bytes);
    }

    ServerFacade getServerFacade() {
        return ServerFacade.getInstance();
    }
}
