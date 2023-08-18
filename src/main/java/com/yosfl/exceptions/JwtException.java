package com.yosfl.exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class JwtException extends WebApplicationException {
    public JwtException(String message, Response.Status status) {
        super(Response.status(status).entity(message).build());
    }
}
