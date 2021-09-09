package edu.byu.cs.tweeter.model.service.response;

import java.util.Objects;

public class PostStatusResponse extends Response {

    public PostStatusResponse(String message) {
        super(false, message);
    }

    public PostStatusResponse() {
        super(true, null);
    }

    @Override
    public boolean equals(Object param) {
        if (this == param) {
            return true;
        }

        if (param == null || getClass() != param.getClass()) {
            return false;
        }

        PostStatusResponse that = (PostStatusResponse) param;

        return (Objects.equals(this.getMessage(), that.getMessage()) &&
                this.isSuccess() == that.isSuccess());
    }
}
