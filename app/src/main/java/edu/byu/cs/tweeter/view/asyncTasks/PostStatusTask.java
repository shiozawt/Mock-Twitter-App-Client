package edu.byu.cs.tweeter.view.asyncTasks;

import android.util.Log;

import edu.byu.cs.tweeter.model.service.request.PostStatusRequest;
import edu.byu.cs.tweeter.model.service.response.PostStatusResponse;
import edu.byu.cs.tweeter.presenter.PostStatusPresenter;

public class PostStatusTask extends TemplateAsyncTask<PostStatusRequest, PostStatusResponse> {
    private final PostStatusPresenter presenter;
    private final Observer observer;

    public interface Observer {
        void postStatusesRetrieved(PostStatusResponse postStatusResponse);
        void handleException(Exception exception);
    }

    public PostStatusTask(PostStatusPresenter presenter, Observer observer) {
        Log.d("observer","REACHED poststatustask");
        if(observer == null) {
            throw new NullPointerException();
        }
        if(presenter == null){
            Log.d("observerSEND","PRESENTER IS NULL");
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    PostStatusResponse sendRequest(PostStatusRequest[] requests) throws Exception {
        if(requests[0] == null){
            Log.d("observerSEND","NULLLLLLL OBJECT");
        }
        return presenter.getPostStatus(requests[0]);
    }

    @Override
    public void handleResponse(PostStatusResponse response) {
        if (response.isSuccess()) {
            Log.d("observer","REACHED handleresponse");
            observer.postStatusesRetrieved(response);
        }
    }

    @Override
    public void handleException(Exception ex) {
        Log.d("GetPostStatusTask: ", ex.toString());
    }
}