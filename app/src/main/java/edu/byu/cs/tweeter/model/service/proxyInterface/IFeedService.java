package edu.byu.cs.tweeter.model.service.proxyInterface;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;

import java.io.IOException;

public interface IFeedService {
    FeedResponse getFeed(FeedRequest request) throws IOException, TweeterRemoteException;

}
