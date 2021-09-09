package edu.byu.cs.tweeter.model.service.request;

public class RegisterRequest {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String b64Pic;

    public RegisterRequest () {}

    public RegisterRequest(String firstname, String lastname, String username,
                           String password, String picture) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.b64Pic = picture;
    }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public void setB64Pic(String b64Pic) { this.b64Pic = b64Pic; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getB64Pic() { return b64Pic; }
}
