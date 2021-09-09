package edu.byu.cs.tweeter.model.service.proxyInterface;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.UserInfoRequest;
import edu.byu.cs.tweeter.model.service.response.UserInfoResponse;

public interface IUserInfoService {
    UserInfoResponse getUserInfo(UserInfoRequest request) throws IOException, TweeterRemoteException;
}
