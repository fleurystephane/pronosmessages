package com.yosfl.exceptions;

import io.quarkus.logging.Log;
import jakarta.ws.rs.ForbiddenException;

public class PronosForbiddenException extends ForbiddenException {
    public PronosForbiddenException(String message){
        super(message);
        Log.info(message);
    }
}
