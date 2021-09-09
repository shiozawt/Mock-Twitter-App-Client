package edu.byu.cs.tweeter.view.main;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.content.ContextCompat;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.UserInfoRequest;
import edu.byu.cs.tweeter.model.service.response.UserInfoResponse;
import edu.byu.cs.tweeter.presenter.UserInfoPresenter;
import edu.byu.cs.tweeter.view.asyncTasks.GetUserInfoTask;
import edu.byu.cs.tweeter.view.main.following.FollowingFragment;
import edu.byu.cs.tweeter.view.session.Session;


public class OtherUserNavigator implements INavigator, GetUserInfoTask.Observer {
    private String otherUserAlias;
    private Context context;
    private AuthToken authToken;
    private UserInfoPresenter userInfoPresenter;


    public OtherUserNavigator(String otherUserAlias, Context context, AuthToken authToken, UserInfoPresenter userInfoPresenter) {
        this.otherUserAlias = otherUserAlias;
        this.context = context;
        this.authToken = authToken;
        this.userInfoPresenter = userInfoPresenter;
    }

    public void navigateToOtherUser() {
        UserInfoRequest userInfoRequest = new UserInfoRequest(otherUserAlias, Session.getInstance().getAuthToken().getToken());
        GetUserInfoTask userInfoTask = new GetUserInfoTask(userInfoPresenter, OtherUserNavigator.this);
        userInfoTask.execute(userInfoRequest);
    }

    @Override
    public void getUserInfoSuccessful(UserInfoResponse userInfoResponse) {
        Log.d("GetUserInfoSuccess: ", userInfoResponse.getUser().getAlias());
        Intent intent = new Intent(context, OtherUserActivity.class);
        intent.putExtra(OtherUserActivity.OTHER_USER_KEY, userInfoResponse.getUser());
        intent.putExtra(OtherUserActivity.AUTH_TOKEN_KEY, authToken);
        context.startActivity(intent);
    }

    @Override
    public void handleException(Exception ex) {
        Log.d("GetUserInfoFail: ", ex.getMessage());
    }
}
