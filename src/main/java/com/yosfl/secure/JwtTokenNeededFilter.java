package com.yosfl.secure;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yosfl.exceptions.JwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.jboss.resteasy.core.interception.jaxrs.PostMatchContainerRequestContext;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import jakarta.xml.bind.DatatypeConverter;
import java.io.IOException;

@Provider
@JwtTokenNeeded
public class JwtTokenNeededFilter implements ContainerRequestFilter {

    public static final String MA_CLEF_SECRETE = "maClefSecrète";

    @Override
    public void filter(ContainerRequestContext requestContext) {
        // Récupère le token JWT depuis le header "Authorization"
        String authorizationHeader = requestContext.getHeaderString("Authorization");
        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7); // supprime la chaîne "Bearer "
        }

        if (token != null) {
            JwtParserBuilder builder = Jwts.parserBuilder()
                    .setSigningKey(MA_CLEF_SECRETE.getBytes());
            builder.require(SignatureAlgorithm.class.getName(), SignatureAlgorithm.HS256);

            JWTVerifier verifier = JWT.require(Algorithm.HMAC256("maClefSecrète")).build();
            DecodedJWT jwt = verifier.verify(token);
            String email = jwt.getClaim("email").asString();
            if(requestContext.getHeaderString("email") == null ||
                    !requestContext.getHeaderString("email").equalsIgnoreCase(email)
            ){
                if(!((PostMatchContainerRequestContext) requestContext).getHttpRequest().getUri().getPath().startsWith("/v1/users"))
                        //&& ((PostMatchContainerRequestContext) requestContext).getHttpRequest().getHttpMethod().equalsIgnoreCase("post")))
                    throw new JwtException("Email transmis incorrect", Response.Status.UNAUTHORIZED);
            }
        }
        else {
            throw new JwtException("Absence du token", Response.Status.UNAUTHORIZED);
        }

    }
}
