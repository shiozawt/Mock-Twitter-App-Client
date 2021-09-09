package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.Status;

public class StoryRequest extends Request{
    private String alias;
    private int limit;
    private Status lastStatus;

    public StoryRequest() {
        super(null);
    }
    
    public StoryRequest(String alias, int limit, Status lastStatus, String token) {
        super(token);
        this.alias = alias;
        this.limit = limit;
        this.lastStatus = lastStatus;
    }
  
    public String getUserAlias() {
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
