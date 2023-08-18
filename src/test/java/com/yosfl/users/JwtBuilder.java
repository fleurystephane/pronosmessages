package com.yosfl.users;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtBuilder {

    private static final byte[] secret = "maClefSecrète".getBytes();
    private static final byte[] invalidSecret = "bidon".getBytes();

    public static String buildJwt(String email) {
        // Construire un JWT avec l'identifiant utilisateur comme une revendication (claim)
        String token = JWT.create()
                .withClaim("email", email)
                .sign(Algorithm.HMAC256(secret));
        return token;
    }

    public static String buildInvalidJwt(String email) {
        String token = JWT.create()
                .withClaim("email", email)
                .sign(Algorithm.HMAC256(invalidSecret));
        return token;
    }
}

