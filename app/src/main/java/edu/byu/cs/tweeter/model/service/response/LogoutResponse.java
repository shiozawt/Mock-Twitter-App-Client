package edu.byu.cs.tweeter.model.service.response;


public class LogoutResponse extends Response {

    public LogoutResponse(Boolean deactivated, String message) {
        super(deactivated, message);
    }

}
