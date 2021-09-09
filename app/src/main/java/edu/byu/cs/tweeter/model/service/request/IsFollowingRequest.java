package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.User;

public class IsFollowingRequest extends Request{
    private User curUser;
    private User otherUser;

    public IsFollowingRequest() {
        super(null);
    }

    public IsFollowingRequest(User curUser, User otherUser, String token) {
        super(token);
        this.curUser = curUser;
        this.otherUser = otherUser;
    }

    public User getCurUser() {
        return this.curUser;
    }

    public User getOtherUser() {
        return this.otherUser;
    }

    public void setCurUser(User curUser) {
        this.curUser = curUser;
    }

    public void setOtherUser(User otherUser) {
        this.otherUser = otherUser;
    }
}
