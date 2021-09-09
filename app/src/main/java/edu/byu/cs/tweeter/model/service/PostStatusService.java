package edu.byu.cs.tweeter.model.service;

import android.util.Log;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.proxyInterface.IPostStatusService;
import edu.byu.cs.tweeter.model.service.request.PostStatusRequest;
import edu.byu.cs.tweeter.model.service.response.PostStatusResponse;


public class PostStatusService implements IPostStatusService {

    static final String URL_PATH = "/post-status";

    ServerFacade getServerFacade() {
        return ServerFacade.getInstance();
    }

    @Override
    public PostStatusResponse getPostStatus(PostStatusRequest request) throws IOException, TweeterRemoteException {
        ServerFacade serverFacade = getServerFacade();
        PostStatusResponse postStatusResponse = serverFacade.postStatus(request, URL_PATH);

        return postStatusResponse;
    }
}
