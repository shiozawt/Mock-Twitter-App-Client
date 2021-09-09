package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.Status;

public class FeedRequest extends Request{
    private String alias;
    private int limit;
    private Status lastStatus;

    public FeedRequest() {
        super(null);
    }

    public FeedRequest(String alias, int limit, Status lastStatus, String token) {
        super(token);
        this.alias = alias;
        this.limit = limit;
        this.lastStatus = lastStatus;
    }

    public String getAlias() {
        return alias;
    }

    public int getLimit() {
        return limit;
    }

    public Status getLastStatus() {
        return lastStatus;
    }

    public void setAlias(String alias) { this.alias = alias; }

    public void setLimit(int limit) { this.limit = limit; }

    public void setLastStatus(Status lastStatus) { this.lastStatus = lastStatus; }
}
