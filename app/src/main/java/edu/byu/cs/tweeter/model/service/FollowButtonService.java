package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.proxyInterface.IFollowButtonService;
import edu.byu.cs.tweeter.model.service.request.FollowButtonRequest;
import edu.byu.cs.tweeter.model.service.response.FollowButtonResponse;

public class FollowButtonService implements IFollowButtonService {

    static final String URL_PATH = "/follow";

    public FollowButtonResponse follow(FollowButtonRequest followButtonRequest) throws IOException, TweeterRemoteException {
        ServerFacade serverFacade = getServerFacade();
        FollowButtonResponse followButtonResponse = serverFacade.follow(followButtonRequest, URL_PATH);

        return followButtonResponse;
    }

    ServerFacade getServerFacade() {
        return ServerFacade.getInstance();
    }
}
