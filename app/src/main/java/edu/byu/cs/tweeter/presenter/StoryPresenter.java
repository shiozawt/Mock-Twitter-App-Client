package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.FollowingService;
import edu.byu.cs.tweeter.model.service.StoryService;
import edu.byu.cs.tweeter.model.service.proxyInterface.IStoryService;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;

public class StoryPresenter {
    private final StoryPresenter.View view;

    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    public StoryPresenter(StoryPresenter.View view) {
        this.view = view;
    }

    public StoryResponse getStory(StoryRequest request) throws IOException, TweeterRemoteException {
        IStoryService storyService = getStoryService();
        return storyService.getStory(request);
    }

    StoryService getStoryService() {
        return new StoryService();
    }
}
