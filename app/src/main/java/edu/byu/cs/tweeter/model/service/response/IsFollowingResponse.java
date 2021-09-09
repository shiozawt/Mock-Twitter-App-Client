package edu.byu.cs.tweeter.model.service.response;

import java.util.Objects;

public class IsFollowingResponse extends Response {
    private boolean isFollowing;

    public IsFollowingResponse(String message) {
        super(false, message);
    }

    public IsFollowingResponse(boolean isFollowing) {
        super(true, null);
        this.isFollowing = isFollowing;
    }

    public boolean getIsFollowing() {
        return this.isFollowing;
    }

    @Override
    public boolean equals(Object param) {
        if (this == param) {
            return true;
        }

        if (param == null || getClass() != param.getClass()) {
            return false;
        }

        IsFollowingResponse that = (IsFollowingResponse) param;

        return (Objects.equals(isFollowing, that.isFollowing) &&
                Objects.equals(this.getMessage(), that.getMessage()) &&
                this.isSuccess() == that.isSuccess());
    }
}
