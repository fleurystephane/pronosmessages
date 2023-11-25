package com.yosfl.exceptions;

import io.quarkus.logging.Log;

public class ObjectNotFoundException extends Exception{
    public ObjectNotFoundException(String message){
        super(message);
        Log.info(message);
    }
}
