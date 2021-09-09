package edu.byu.cs.tweeter.model.service.proxyInterface;

import java.io.IOException;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.FollowButtonRequest;
import edu.byu.cs.tweeter.model.service.response.FollowButtonResponse;

public interface IFollowButtonService {
    FollowButtonResponse follow(FollowButtonRequest request) throws IOException, TweeterRemoteException;
}
