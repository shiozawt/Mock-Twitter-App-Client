package edu.byu.cs.tweeter.model.service.request;

public class Request {
    public String token;

    Request(String token){
        this.token = token;
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }
}
