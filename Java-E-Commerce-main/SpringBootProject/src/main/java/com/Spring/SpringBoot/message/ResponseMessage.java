//controller will use both class of message package for sending
//message via HTTP responses
package com.Spring.SpringBoot.message;

public class ResponseMessage {
    private final String message;

    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
}
