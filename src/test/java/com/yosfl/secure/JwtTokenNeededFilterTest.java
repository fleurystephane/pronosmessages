package com.yosfl.secure;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.yosfl.exceptions.JwtException;
import com.yosfl.users.JwtBuilder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.*;

import java.io.InputStream;
import java.net.URI;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtTokenNeededFilterTest {

    @Mock
    private ContainerRequestContext containerRequestContext;

    @Mock
    private UriInfo uriInfo;

    @Test
    public void validToken(){
        // Ajouter le jeton JWT dans l'en-tête de la demande
        String bearer = "Bearer " + JwtBuilder.buildJwt("email-client-de-test@gmail.com");
        when(containerRequestContext.getHeaderString("Authorization")).thenReturn(bearer);
        when(containerRequestContext.getHeaderString("email")).thenReturn("email-client-de-test@gmail.com");

        // Appeler la méthode doFilter de JwtTokenNeededFilter
        JwtTokenNeededFilter filter = new JwtTokenNeededFilter();
        filter.filter(containerRequestContext);
        Assertions.assertTrue(true);
    }

    @Test
    public void invalidToken(){
        String bearer = "Bearer " + JwtBuilder.buildInvalidJwt("email-client-de-test@gmail.com");
        when(containerRequestContext.getHeaderString("Authorization")).thenReturn(bearer);


        // Appeler la méthode doFilter de JwtTokenNeededFilter
        JwtTokenNeededFilter filter = new JwtTokenNeededFilter();
        try{
            filter.filter(containerRequestContext);
        }catch(SignatureVerificationException sve){
            return;
        }
        Assertions.fail("Signature Exception aurait du etre levee.");
    }

    @Test
    public void noToken(){
        when(containerRequestContext.getHeaderString("Authorization")).thenReturn(null);

        // Appeler la méthode doFilter de JwtTokenNeededFilter
        JwtTokenNeededFilter filter = new JwtTokenNeededFilter();

        assertThrows(JwtException.class, () -> filter.filter(containerRequestContext));
    }
}