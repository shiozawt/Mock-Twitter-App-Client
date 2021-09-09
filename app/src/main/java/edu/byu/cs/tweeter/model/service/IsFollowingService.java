package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.proxyInterface.IIsFollowingService;
import edu.byu.cs.tweeter.model.service.request.IsFollowingRequest;
import edu.byu.cs.tweeter.model.service.response.IsFollowingResponse;

public class IsFollowingService implements IIsFollowingService {

    static final String URL_PATH = "/is-following";

    public IsFollowingResponse isFollowing(IsFollowingRequest request) throws IOException, TweeterRemoteException {
        ServerFacade serverFacade = getServerFacade();
        IsFollowingResponse isFollowinResponse = serverFacade.isFollowing(request, URL_PATH);

        return isFollowinResponse;
    }

    ServerFacade getServerFacade() {
        return ServerFacade.getInstance();
    }
}
