package edu.byu.cs.tweeter.model.service.request;

public class UserInfoRequest extends Request{
    private String alias;

    public UserInfoRequest() {super(null);}

    public UserInfoRequest(String alias, String token) {
        super(token);
        this.alias = alias;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
