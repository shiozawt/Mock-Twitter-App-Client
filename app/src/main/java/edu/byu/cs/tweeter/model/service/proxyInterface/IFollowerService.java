package edu.byu.cs.tweeter.model.service.proxyInterface;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import edu.byu.cs.tweeter.model.service.response.FollowerResponse;
import java.io.IOException;

public interface IFollowerService {
    FollowerResponse getFollowers(FollowerRequest request) throws IOException, TweeterRemoteException;
}