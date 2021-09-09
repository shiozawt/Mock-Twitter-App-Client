package edu.byu.cs.tweeter.model.service.proxyInterface;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;

public interface IFollowingService {
    FollowingResponse getFollowees(FollowingRequest request) throws IOException, TweeterRemoteException;
}
