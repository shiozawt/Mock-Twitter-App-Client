package edu.byu.cs.tweeter.model.service.proxyInterface;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;

import java.io.IOException;

public interface IStoryService {
    StoryResponse getStory(StoryRequest request) throws IOException, TweeterRemoteException;
}
