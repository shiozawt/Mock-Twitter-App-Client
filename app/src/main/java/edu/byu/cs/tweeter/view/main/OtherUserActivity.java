package edu.byu.cs.tweeter.view.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowButtonRequest;
import edu.byu.cs.tweeter.model.service.request.IsFollowingRequest;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.response.FollowButtonResponse;
import edu.byu.cs.tweeter.model.service.response.IsFollowingResponse;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.presenter.FollowButtonPresenter;
import edu.byu.cs.tweeter.presenter.IsFollowingPresenter;
import edu.byu.cs.tweeter.presenter.LogoutPresenter;
import edu.byu.cs.tweeter.view.LoginActivity;
import edu.byu.cs.tweeter.view.asyncTasks.FollowButtonTask;
import edu.byu.cs.tweeter.view.asyncTasks.IsFollowingTask;
import edu.byu.cs.tweeter.view.asyncTasks.LogoutTask;
import edu.byu.cs.tweeter.view.session.Session;
import edu.byu.cs.tweeter.view.util.ImageUtils;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class OtherUserActivity extends AppCompatActivity implements IsFollowingTask.Observer,
        IsFollowingPresenter.View, FollowButtonTask.Observer, FollowButtonPresenter.View,
        LogoutTask.Observer, LogoutPresenter.View {
    public static final String OTHER_USER_KEY = "OtherUser";
    public static final String AUTH_TOKEN_KEY = "AuthTokenKey";
    private IsFollowingPresenter isFollowingPresenter;
    private User curUser = Session.getInstance().getUser();
    private String LOG_TAG = "OtherUserActivity";

    private Button followButton;
    private TextView followeeCount;
    private TextView followerCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user);

        User otherUser = (User) getIntent().getSerializableExtra(OTHER_USER_KEY);
        if(otherUser == null) {
            throw new RuntimeException("Other User not passed to activity");
        }

        AuthToken authToken = (AuthToken) getIntent().getSerializableExtra(AUTH_TOKEN_KEY);

        OtherUserSectionsPagerAdapter sectionsPagerAdapter = new OtherUserSectionsPagerAdapter(this, getSupportFragmentManager(), otherUser, authToken);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.hide();

        TextView userName = findViewById(R.id.userName);
        userName.setText(otherUser.getName());

        TextView userAlias = findViewById(R.id.userAlias);
        userAlias.setText(otherUser.getAlias());

        ImageView userImageView = findViewById(R.id.userImage);
        userImageView.setImageDrawable(ImageUtils.drawableFromByteArray(otherUser.getImageBytes()));

        followeeCount = findViewById(R.id.followeeCount);
        followeeCount.setText(getString(R.string.followeeCount, otherUser.getFollowingCount()));

        followerCount = findViewById(R.id.followerCount);
        followerCount.setText(getString(R.string.followerCount, otherUser.getFollowerCount()));

        followButton = findViewById(R.id.FollowButton);

        followButton.setText("loading");

        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (curUser.getAlias().equals(otherUser.getAlias())) {
                    cannotFollowMsg();
                    return;
                }
                canFollowMsg();

                FollowButtonPresenter followButtonPresenter = new FollowButtonPresenter(OtherUserActivity.this);
                Log.d("curUser: ", curUser.getAlias());
                FollowButtonRequest followButtonRequest = new FollowButtonRequest(curUser, otherUser, Session.getInstance().getAuthToken().getToken());
                FollowButtonTask followButtonTask = new FollowButtonTask(followButtonPresenter, OtherUserActivity.this);
                followButtonTask.execute(followButtonRequest);
            }
        });

        isFollowingPresenter = new IsFollowingPresenter(this);
        IsFollowingRequest isFollowingRequest = new IsFollowingRequest(curUser, otherUser, Session.getInstance().getAuthToken().getToken());
        Log.d("isFollowingRequest: ",  "other user: " + otherUser.getAlias());
        Log.d("curUser: ", curUser.getAlias());
        IsFollowingTask isFollowingTask = new IsFollowingTask(isFollowingPresenter, OtherUserActivity.this);
        isFollowingTask.execute(isFollowingRequest);
    }

    private void canFollowMsg() {
        Toast.makeText(this, "follow button clicked", Toast.LENGTH_SHORT).show();
    }



    private void cannotFollowMsg() {
        Toast.makeText(this, "You cannot follow yourself", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.logoutMenu){

            LogoutPresenter presenter = new LogoutPresenter(this);
            LogoutRequest logoutRequest = new LogoutRequest(AUTH_TOKEN_KEY);
            LogoutTask logoutTask = new LogoutTask(presenter, this);
            logoutTask.execute(logoutRequest);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void logoutSuccessful(LogoutResponse logoutResponse) {
        Toast.makeText(this, "logout reached", Toast.LENGTH_LONG).show();

        Session.getInstance().clear();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void handleException(Exception exception) {
        Log.e(LOG_TAG, exception.getMessage(), exception);
        Toast.makeText(this, "Failed to logout because of exception: " + exception.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void isFollowingRetrieved(IsFollowingResponse isFollowingResponse) {
        if (isFollowingResponse.getIsFollowing()) {
            followButton.setText("UNFOLLOW");
        }
        else {
            followButton.setText("FOLLOW");
        }
    }

    @Override
    public void followButtonSuccessful(FollowButtonResponse followButtonResponse) {
        Toast.makeText(this, "followButtonSuccessful " + "following count: " + followButtonResponse.getFollowingCount() + "follower count: " + followButtonResponse.getFollowerCount(), Toast.LENGTH_SHORT).show();
        // use the numbers inthe res to set the text
        // Should I just use the previous text and add 1 or use the server response?
        followeeCount.setText(getString(R.string.followeeCount, followButtonResponse.getFollowingCount()));
        followerCount.setText(getString(R.string.followerCount, followButtonResponse.getFollowerCount()));
        if (followButton.getText() == "UNFOLLOW") {
            followButton.setText("FOLLOW");
            Session.getInstance().getUser().setFollowingCount(Session.getInstance().getUser().getFollowingCount() - 1);
        }
        else {
            followButton.setText("UNFOLLOW");
            Session.getInstance().getUser().setFollowingCount(Session.getInstance().getUser().getFollowingCount() + 1);
        }
    }
}