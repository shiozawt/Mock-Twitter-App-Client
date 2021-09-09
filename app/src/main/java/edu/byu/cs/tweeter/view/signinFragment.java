package edu.byu.cs.tweeter.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.presenter.LoginPresenter;
import edu.byu.cs.tweeter.view.asyncTasks.LoginTask;
import edu.byu.cs.tweeter.view.main.MainActivity;
import edu.byu.cs.tweeter.view.session.Session;

public class signinFragment extends Fragment implements LoginPresenter.View, LoginTask.Observer {

    private String user_name;
    private String password;


    private static final String LOG_TAG = "LoginFragment";
    private LoginPresenter presenter;
    private Toast loginInToast;

    public signinFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signin, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        presenter = new LoginPresenter(this);
        EditText usernameInput = view.findViewById(R.id.usernameInput);
        EditText passwordInput = view.findViewById(R.id.passwordInput);


        Button loginButton = view.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(view1 -> {
            user_name = usernameInput.getText().toString();
            password = passwordInput.getText().toString();
            loginInToast = Toast.makeText(getActivity(), "Logging In", Toast.LENGTH_LONG);
            loginInToast.show();

            LoginRequest loginRequest = new LoginRequest(user_name, password);
            LoginTask loginTask = new LoginTask(presenter, signinFragment.this);
            loginTask.execute(loginRequest);
        });
    }

    @Override
    public void loginSuccessful(LoginResponse loginResponse) {
        Intent intent = new Intent(getActivity(), MainActivity.class);

        intent.putExtra(MainActivity.CURRENT_USER_KEY, loginResponse.getUser());
        intent.putExtra(MainActivity.AUTH_TOKEN_KEY, loginResponse.getAuthToken());
        Session.getInstance().setUser(loginResponse.getUser());
        Session.getInstance().setAuthToken(loginResponse.getAuthToken());
        loginInToast.cancel();
        startActivity(intent);
    }

    @Override
    public void loginUnsuccessful(LoginResponse loginResponse) {
        Toast.makeText(getActivity(), "Failed to login. " + loginResponse.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void handleException(Exception exception) {
        Log.e(LOG_TAG, exception.getMessage(), exception);
        Toast.makeText(getActivity(), "Failed to login because of exception: " + exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}