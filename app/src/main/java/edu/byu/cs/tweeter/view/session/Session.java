package edu.byu.cs.tweeter.view.session;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class Session {
    private static Session session = null;
    private User user;
    private AuthToken authToken;

    public static Session getInstance() {
        if (session == null)
            session = new Session();
        return session;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void clear() {
        session = null;
        this.user = null;
    }
}

