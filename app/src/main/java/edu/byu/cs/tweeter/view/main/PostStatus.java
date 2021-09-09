package edu.byu.cs.tweeter.view.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.PostStatusRequest;
import edu.byu.cs.tweeter.model.service.response.PostStatusResponse;
import edu.byu.cs.tweeter.presenter.FollowingPresenter;
import edu.byu.cs.tweeter.presenter.PostStatusPresenter;
import edu.byu.cs.tweeter.view.asyncTasks.PostStatusTask;
import edu.byu.cs.tweeter.view.session.Session;
import edu.byu.cs.tweeter.view.util.ImageUtils;

public class PostStatus extends Activity implements PostStatusTask.Observer, PostStatusPresenter.View{

    private PostStatusPresenter postStatusPresenter;
    private static final String LOG_TAG = "PostStatusWindow";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_status);

        User user = Session.getInstance().getUser(); // current logged in user

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.6),(int)(height*.4));

        TextView postUserName = findViewById(R.id.postUserName);
        postUserName.setText(user.getName());

        TextView userAlias = findViewById(R.id.postUserAlias);
        userAlias.setText(user.getAlias());

        ImageView userImageView = findViewById(R.id.userImage);
        userImageView.setImageDrawable(ImageUtils.drawableFromByteArray(user.getImageBytes()));


        EditText messageContent = findViewById(R.id.post_message_content);

        Button postButton = findViewById(R.id.post_button);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String content = messageContent.getText().toString();

                Date date = Calendar.getInstance().getTime();
                //DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

                DateFormat timeFormat = new SimpleDateFormat("h:mm aa");
                String timeString = timeFormat.format(date);

                DateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
                String dateString = dateFormat.format(date);

                postStatusPresenter = new PostStatusPresenter(PostStatus.this);

//                Status status = new Status(user, dateString, timeString, content);

                PostStatusTask postStatusTask = new PostStatusTask(postStatusPresenter, PostStatus.this);
                String token = Session.getInstance().getAuthToken().getToken();
                PostStatusRequest request = new PostStatusRequest(user.getAlias(), dateString, timeString, content, token);
                postStatusTask.execute(request);


                Snackbar.make(view, "Status Posted", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }

    @Override
    public void postStatusesRetrieved(PostStatusResponse postStatusResponse) {
        //Toast.makeText(this.getApplicationContext(), "RETRIEVAL SUCCESS", Toast.LENGTH_LONG);
        Log.d("LOGGGG","MADE IT TO POST STATUS RETRIEVED");
        finish();
    }

    @Override
    public void handleException(Exception exception) {
        Log.e(LOG_TAG, exception.getMessage(), exception);
        Toast.makeText(this.getApplicationContext(), "Exception Thrown", Toast.LENGTH_LONG).show();
    }
}
