package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.RegisterService;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;

public class RegisterPresenter {

    private final View view;

    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    public RegisterPresenter(View view) {
        this.view = view;
    }


    public LoginResponse register(RegisterRequest registerRequest) throws IOException, TweeterRemoteException {
        RegisterService registerService = getRegisterService();
        return registerService.register(registerRequest);
    }

    RegisterService getRegisterService() { return new RegisterService(); }
}

