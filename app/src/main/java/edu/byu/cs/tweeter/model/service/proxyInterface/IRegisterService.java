package edu.byu.cs.tweeter.model.service.proxyInterface;

import java.io.IOException;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;

public interface IRegisterService {
    LoginResponse register(RegisterRequest request) throws IOException, TweeterRemoteException;
}
