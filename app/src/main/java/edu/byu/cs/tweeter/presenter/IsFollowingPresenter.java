package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.IsFollowingService;
import edu.byu.cs.tweeter.model.service.proxyInterface.IIsFollowingService;
import edu.byu.cs.tweeter.model.service.request.IsFollowingRequest;
import edu.byu.cs.tweeter.model.service.response.IsFollowingResponse;

public class IsFollowingPresenter {
    private final View view;

    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    public IsFollowingPresenter(View view) {
        this.view = view;
    }

    public IsFollowingResponse isFollowing(IsFollowingRequest isFollowingRequest) throws IOException, TweeterRemoteException {
        IIsFollowingService isFollowingService = getIsFollowingService();
        return isFollowingService.isFollowing(isFollowingRequest);
    }

    public IsFollowingService getIsFollowingService() {
        return new IsFollowingService();
    }
}
