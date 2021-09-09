package edu.byu.cs.tweeter.model.service.proxyInterface;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.PostStatusRequest;
import edu.byu.cs.tweeter.model.service.response.PostStatusResponse;

import java.io.IOException;

public interface IPostStatusService {
    PostStatusResponse getPostStatus(PostStatusRequest request) throws IOException, TweeterRemoteException;

}
