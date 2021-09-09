package edu.byu.cs.tweeter.model.service.request;

public class FollowerRequest extends Request{
    private String followeeAlias;
    private int limit;
    private String lastFollowerAlias;

    public FollowerRequest() {
        super(null);
    }

    public FollowerRequest(String followeeAlias, int limit, String lastFollowerAlias, String token) {
        super(token);
        this.followeeAlias = followeeAlias;
        this.limit = limit;
        this.lastFollowerAlias = lastFollowerAlias;
    }

    public String getFolloweeAlias() {
        return followeeAlias;
    }

    public int getLimit() {
        return limit;
    }

    public String getLastFollowerAlias() {
        return lastFollowerAlias;
    }

    public void setFolloweeAlias(String followeeAlias) {
        this.followeeAlias = followeeAlias;
    }

    public void setLastFollowerAlias(String lastFollowerAlias) {
        this.lastFollowerAlias = lastFollowerAlias;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
