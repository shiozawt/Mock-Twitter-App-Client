package edu.byu.cs.tweeter.presenter;

import java.io.IOException;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.PostStatusService;
import edu.byu.cs.tweeter.model.service.proxyInterface.IPostStatusService;
import edu.byu.cs.tweeter.model.service.request.PostStatusRequest;
import edu.byu.cs.tweeter.model.service.response.PostStatusResponse;

public class PostStatusPresenter {
    private final PostStatusPresenter.View view;


    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    public PostStatusPresenter(PostStatusPresenter.View view) {
        this.view = view;
    }

    public PostStatusResponse getPostStatus(PostStatusRequest request) throws IOException, TweeterRemoteException {
        IPostStatusService postStatusService = getPostStatusService();
        return postStatusService.getPostStatus(request);
    }

    PostStatusService getPostStatusService() {
        return new PostStatusService();
    }
}

