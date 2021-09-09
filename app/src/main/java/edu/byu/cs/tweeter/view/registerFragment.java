package edu.byu.cs.tweeter.view;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.presenter.RegisterPresenter;
import edu.byu.cs.tweeter.view.asyncTasks.RegisterTask;
import edu.byu.cs.tweeter.view.main.MainActivity;
import edu.byu.cs.tweeter.view.session.Session;

import static androidx.core.content.ContextCompat.checkSelfPermission;

public class registerFragment extends Fragment implements RegisterTask.Observer, RegisterPresenter.View {

    private String user_name;
    private String password;
    private String confirmPassword;
    private String firstname;
    private String lastname;

    private static final String LOG_TAG = "RegisterFragment";
    private RegisterPresenter presenter;
    private Toast registerToast;

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int GET_FROM_GALLERY = 101;
    private Bitmap pfPicBitmap;



    public registerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        presenter = new RegisterPresenter(this);

        EditText usernameInput = view.findViewById(R.id.usernameInput);
        EditText passwordInput = view.findViewById(R.id.passwordInput);
        EditText passwordConfirmInput = view.findViewById(R.id.passwordConfirmInput);
        EditText firstnameInput = view.findViewById(R.id.firstNameInput);
        EditText lastnameInput = view.findViewById(R.id.lastNameInput);

        String[] picOptions = {"Upload from Gallery", "Take with Camera"};



        Button pictureButton = view.findViewById(R.id.uploadPhotoButton);
        pictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Upload Profile Picture");
                builder.setItems(picOptions, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 1){
                            if (checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                            {
                                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                            }
                            else
                            {
                                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                            }
                        }
                        else if(which == 0){
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(Intent.createChooser(intent, "Select Picture"),GET_FROM_GALLERY);
                            //startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
                        }
                    }
                });
                builder.show();
            }
        });

        Button registerButton = view.findViewById(R.id.registerButton);
        registerButton.setOnClickListener(view1 -> {
            user_name = usernameInput.getText().toString();
            password = passwordInput.getText().toString();
            confirmPassword = passwordConfirmInput.getText().toString();
            firstname = firstnameInput.getText().toString();
            lastname = lastnameInput.getText().toString();
            if(password.equals(confirmPassword)){
                registerToast = Toast.makeText(getActivity(), "Registering", Toast.LENGTH_LONG);
                registerToast.show();

                String encoded = "";
                if(pfPicBitmap != null) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    pfPicBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                }

                RegisterRequest registerRequest = new RegisterRequest(firstname, lastname, user_name, password, encoded);
                RegisterTask registerTask = new RegisterTask(presenter, registerFragment.this);
                registerTask.execute(registerRequest);
            }
            else {
                registerToast = Toast.makeText(getActivity(), "Passwords do not Match", Toast.LENGTH_LONG);
                registerToast.show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                registerToast = Toast.makeText(getActivity(), "camera permission granted", Toast.LENGTH_LONG);
                registerToast.show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                registerToast = Toast.makeText(getActivity(), "camera permission denied", Toast.LENGTH_LONG);
                registerToast.show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {
            pfPicBitmap = (Bitmap) data.getExtras().get("data");
            registerToast = Toast.makeText(getActivity(), "Got the Picture", Toast.LENGTH_LONG);
            registerToast.show();
        }
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            try {
                pfPicBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                registerToast = Toast.makeText(getActivity(), "got pic from gallery", Toast.LENGTH_LONG);
                registerToast.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void registerSuccessful(LoginResponse loginResponse) {
        Intent intent = new Intent(getActivity(), MainActivity.class);

        intent.putExtra(MainActivity.CURRENT_USER_KEY, loginResponse.getUser());
        intent.putExtra(MainActivity.AUTH_TOKEN_KEY, loginResponse.getAuthToken());
        Session.getInstance().setUser(loginResponse.getUser());
        Session.getInstance().setAuthToken(loginResponse.getAuthToken());
        registerToast.cancel();
        startActivity(intent);
    }

    @Override
    public void registerUnsuccessful(LoginResponse loginResponse) {
        Toast.makeText(getActivity(), "Failed to register. " + loginResponse.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void handleException(Exception exception) {
        Log.e(LOG_TAG, exception.getMessage(), exception);
        Toast.makeText(getActivity(), "Failed to register because of exception: " + exception.getMessage(), Toast.LENGTH_LONG).show();
    }

}