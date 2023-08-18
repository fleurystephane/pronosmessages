package com.yosfl.exceptions;

import io.quarkus.logging.Log;
import jakarta.ws.rs.NotFoundException;

public class PronosNotFoundException extends NotFoundException {
    public PronosNotFoundException(String message){
        super(message);
        Log.info(message);
    }
}
