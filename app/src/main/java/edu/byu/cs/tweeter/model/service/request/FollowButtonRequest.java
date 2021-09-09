package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.User;

public class FollowButtonRequest extends Request{
    private User curUser;
    private User otherUser;

    public FollowButtonRequest() {
        super(null);
    }

    public FollowButtonRequest(User curUser, User otherUser, String token) {
        super(token);
        this.curUser = curUser;
        this.otherUser = otherUser;
    }

    public User getCurUser() {
        return curUser;
    }

    public User getOtherUser() {
        return otherUser;
    }

    public void setCurUser(User curUser) {
        this.curUser = curUser;
    }

    public void setOtherUser(User otherUser) {
        this.otherUser = otherUser;
    }
}
