package edu.byu.cs.tweeter.model.service.response;

import java.util.Objects;

public class FollowButtonResponse extends  Response {
    private int followingCount;
    private int followerCount;

    public FollowButtonResponse(String message) {
        super(false, message);
    }

    public FollowButtonResponse(int followingCount, int followerCount) {
        super(true, null);
        this.followingCount = followingCount;
        this.followerCount = followerCount;
    }

    public int getFollowingCount() {
        return this.followingCount;
    }

    public int getFollowerCount() {
        return this.followerCount;
    }

    @Override
    public boolean equals(Object param) {
        if (this == param) {
            return true;
        }

        if (param == null || getClass() != param.getClass()) {
            return false;
        }

        FollowButtonResponse that = (FollowButtonResponse) param;

        return (Objects.equals(followingCount, that.followingCount) &&
                Objects.equals(followerCount, that.followerCount) &&
                Objects.equals(this.getMessage(), that.getMessage()) &&
                this.isSuccess() == that.isSuccess());
    }
}
