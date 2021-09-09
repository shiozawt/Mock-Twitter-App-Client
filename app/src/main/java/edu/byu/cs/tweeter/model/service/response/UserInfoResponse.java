package edu.byu.cs.tweeter.model.service.response;

import java.util.Objects;

import edu.byu.cs.tweeter.model.domain.User;

public class UserInfoResponse extends Response {
    private User user;

    public UserInfoResponse(String message) {
        super(false, message);
    }

    public UserInfoResponse(User user) {
        super(true, null);
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    @Override
    public boolean equals(Object param) {
        if (this == param) {
            return true;
        }

        if (param == null || getClass() != param.getClass()) {
            return false;
        }

        UserInfoResponse that = (UserInfoResponse) param;

        return (Objects.equals(user, that.user) &&
                Objects.equals(this.getMessage(), that.getMessage()) &&
                this.isSuccess() == that.isSuccess());
    }
}
