package edu.byu.cs.tweeter.model.domain;

import java.util.ArrayList;

public class Story {
    private ArrayList<Status> statuses; // ordered by date

    public Story(ArrayList<Status> statuses){
        this.statuses = statuses;
    }

    public void addStatus(Status status){ this.statuses.add(status); }

    public void setAllStatus(ArrayList<Status> stories){ this.statuses = stories; }

    public ArrayList<Status> getAllStatus(){ return this.statuses;}
}
