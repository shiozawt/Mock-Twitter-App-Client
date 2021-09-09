package edu.byu.cs.tweeter.view.asyncTasks;
import android.util.Log;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;
import edu.byu.cs.tweeter.presenter.StoryPresenter;

public class GetStoryTask extends TemplateAsyncTask<StoryRequest, StoryResponse> {
    private final StoryPresenter presenter;
    private final GetStoryTask.Observer observer;
    
    public interface Observer {
        void storyRetrieved(StoryResponse storyResponse);
        void handleException(Exception exception);
    }
    
    public GetStoryTask(StoryPresenter presenter, GetStoryTask.Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    StoryResponse sendRequest(StoryRequest[] requests) throws Exception {
        return presenter.getStory(requests[0]);
    }

    @Override
    public void handleResponse(StoryResponse response) {
        if (response.isSuccess()) {
            observer.storyRetrieved(response);
        }
    }

    @Override
    public void handleException(Exception ex) {
        Log.d("GetStoryTask: ", ex.toString());
    }
}
