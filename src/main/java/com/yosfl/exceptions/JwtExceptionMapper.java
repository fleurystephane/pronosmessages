package com.yosfl.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class JwtExceptionMapper implements ExceptionMapper<JwtException> {

    @Override
    public Response toResponse(JwtException e) {
        return e.getResponse();
    }
}
