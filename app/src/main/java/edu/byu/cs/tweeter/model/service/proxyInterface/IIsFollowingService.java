package edu.byu.cs.tweeter.model.service.proxyInterface;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.IsFollowingRequest;
import edu.byu.cs.tweeter.model.service.response.IsFollowingResponse;

public interface IIsFollowingService {
    IsFollowingResponse isFollowing(IsFollowingRequest request) throws IOException, TweeterRemoteException;
}
