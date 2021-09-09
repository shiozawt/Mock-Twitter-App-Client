package edu.byu.cs.tweeter.model.service.request;

public class PostStatusRequest extends Request{
    private String posterAlias;
    private String date;
    private String time;
    private String content;

    public PostStatusRequest(String posterAlias, String date, String time, String content, String token) {
        super(token);
        this.posterAlias = posterAlias;
        this.date = date;
        this.time = time;
        this.content = content;
    }

    public PostStatusRequest() {super(null);}

    public String getPosterAlias() {
        return posterAlias;
    }

    public void setPosterAlias(String posterAlias) {
        this.posterAlias = posterAlias;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
