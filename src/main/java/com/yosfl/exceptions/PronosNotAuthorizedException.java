package com.yosfl.exceptions;

import io.quarkus.logging.Log;
import jakarta.ws.rs.NotAuthorizedException;

public class PronosNotAuthorizedException extends NotAuthorizedException {
    public PronosNotAuthorizedException(String message){
        super(message);
        Log.info(message);
    }
}
