package edu.byu.cs.tweeter.model.net;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.request.FollowButtonRequest;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import edu.byu.cs.tweeter.model.service.request.IsFollowingRequest;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.request.PostStatusRequest;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.request.UserInfoRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.model.service.response.FollowButtonResponse;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.model.service.response.FollowerResponse;
import edu.byu.cs.tweeter.model.service.response.IsFollowingResponse;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.model.service.response.PostStatusResponse;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;
import edu.byu.cs.tweeter.model.service.response.UserInfoResponse;
import edu.byu.cs.tweeter.view.session.Session;

/**
 * Acts as a Facade to the Tweeter server. All network requests to the server should go through
 * this class.
 */
public class ServerFacade {
    private static ServerFacade session = null;
    private static final String SERVER_URL = "https://3ngu1al4t6.execute-api.us-west-2.amazonaws.com/development";
    private final ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);

    private ServerFacade() {

    }

    public static ServerFacade getInstance() {
        if (session == null)
            session = new ServerFacade();
        return session;
    }

    public LoginResponse login(LoginRequest request, String urlPath) throws IOException, TweeterRemoteException {
//        Log.d("ServerFacade", "Calling AWS login route");
        LoginResponse response = clientCommunicator.doPost(urlPath, request, null, LoginResponse.class);
        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public LoginResponse register(RegisterRequest request, String urlPath) throws IOException, TweeterRemoteException {
//        Log.d("ServerFacade", "Calling AWS register route");
        LoginResponse response = clientCommunicator.doPost(urlPath, request, null, LoginResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public LogoutResponse logout(LogoutRequest request, String urlPath) throws IOException, TweeterRemoteException {
//        Log.d("ServerFacade", "Calling AWS logout route");
        Map<String, String> headers = new HashMap<String, String>() {{
            put("Authorization", Session.getInstance().getAuthToken().getToken());
        }};
        LogoutResponse response = clientCommunicator.doGet(urlPath, headers, LogoutResponse.class);
        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }


    public FollowingResponse getFollowees(FollowingRequest request, String urlPath) throws IOException, TweeterRemoteException {
//        Log.d("ServerFacade", "Calling AWS get-following route");
        FollowingResponse response = clientCommunicator.doPost(urlPath, request, null, FollowingResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public FollowerResponse getFollowers(FollowerRequest request, String urlPath) throws IOException, TweeterRemoteException {
//        Log.d("ServerFacade", "Calling AWS get-follower route");
        FollowerResponse response = clientCommunicator.doPost(urlPath, request, null, FollowerResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public UserInfoResponse getUserInfo(UserInfoRequest request, String urlPath) throws IOException, TweeterRemoteException {
//        Log.d("ServerFacade", "Calling AWS get-user-info route");
        UserInfoResponse response = clientCommunicator.doPost(urlPath, request, null, UserInfoResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public StoryResponse getStory(StoryRequest request, String urlPath) throws IOException, TweeterRemoteException{
//        Log.d("ServerFacade", "Calling AWS get-story route");
        StoryResponse response = clientCommunicator.doPost(urlPath, request, null, StoryResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public FollowButtonResponse follow(FollowButtonRequest request, String urlPath) throws IOException, TweeterRemoteException {
//        Log.d("ServerFacade", "Calling AWS follow route");
        FollowButtonResponse response = clientCommunicator.doPost(urlPath, request, null, FollowButtonResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public FeedResponse getFeed(FeedRequest request, String urlPath) throws IOException, TweeterRemoteException{
//        Log.d("ServerFacade", "Calling AWS get-feed route");
        FeedResponse response = clientCommunicator.doPost(urlPath, request, null, FeedResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public IsFollowingResponse isFollowing(IsFollowingRequest request, String urlPath) throws IOException, TweeterRemoteException {
//        Log.d("ServerFacade", "Calling AWS is-following route");
        IsFollowingResponse response = clientCommunicator.doPost(urlPath, request, null, IsFollowingResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public PostStatusResponse postStatus(PostStatusRequest request, String urlPath) throws IOException, TweeterRemoteException {
//        Log.d("serverFacade", "Calling AWS post-status route");
        PostStatusResponse response = clientCommunicator.doPost(urlPath, request, null, PostStatusResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }
}
