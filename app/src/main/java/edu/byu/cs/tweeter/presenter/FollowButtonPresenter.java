package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.FollowButtonService;
import edu.byu.cs.tweeter.model.service.proxyInterface.IFollowButtonService;
import edu.byu.cs.tweeter.model.service.request.FollowButtonRequest;
import edu.byu.cs.tweeter.model.service.response.FollowButtonResponse;

public class FollowButtonPresenter {
    private final View view;

    public interface View {

    }

    public FollowButtonPresenter(View view) {
        this.view = view;
    }

    public FollowButtonResponse follow(FollowButtonRequest followButtonRequest) throws IOException, TweeterRemoteException {
        IFollowButtonService followButtonService = getFollowButtonSerive();
        return followButtonService.follow(followButtonRequest);
    }

    public FollowButtonService getFollowButtonSerive() {
        return new FollowButtonService();
    }
}
