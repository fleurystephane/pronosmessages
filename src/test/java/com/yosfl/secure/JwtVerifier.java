package com.yosfl.secure;

import com.yosfl.users.JwtBuilder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.quarkus.logging.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class JwtVerifier {

    public static boolean verify(String jwt, String publicKeyStr) throws Exception {
        String[] parts = jwt.split("\\.");
        String header = parts[0];
        String payload = parts[1];
        String signature = parts[2];

        // Décoder la clé publique en utilisant Base64
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyStr);

        // Convertir la clé publique en objet PublicKey
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        // Calculer la signature du JWT en utilisant la clé publique
        Signature verifier = Signature.getInstance("RS256");
        verifier.initVerify(publicKey);
        verifier.update((header + "." + payload).getBytes(StandardCharsets.UTF_8));

        // Vérifier si la signature est valide
        byte[] signatureBytes = Base64.getDecoder().decode(signature);
        return verifier.verify(signatureBytes);
    }

    public static boolean myVerify(String jwt, String publicKeyStr){
        JwtParser jwtParser = Jwts.parser().setSigningKey(publicKeyStr);
        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(publicKeyStr).build().parseClaimsJws(jwt);

        //Jws<Claims> claims = jwtParser.parseClaimsJws(jwt);
        Claims jwtClaims = claims.getBody();

        String subject = jwtClaims.getSubject();
        String name = jwtClaims.get("name", String.class);

        return true;
    }

    public static void main(String[] args) throws Exception {

        //Le jeton pour johndoe@example.com : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImpvaG5kb2VAZXhhbXBsZS5jb20ifQ.68VonIVkD9b0SuPxpq69HvW_gJGmEpO80umeJSMvHiA
        //Le jeton pour diego@example.com : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImRpZWdvQGV4YW1wbGUuY29tIn0.NCbEm0xvvzrOcobcelOBiSM6V6iAIt_weU9l4O2UHBI
        //Le jeton pour toto@example.com : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InRvdG9AZXhhbXBsZS5jb20ifQ.PHD_3zs8Gk4d9lout7E-oqpUXRl94x6J4ohsAPzK1gQ
        //Le jeton pour alicelee@example.com : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFsaWNlbGVlQGV4YW1wbGUuY29tIn0.QJ0bFj2US1q2pWALSQUpXr7dvvKJkXIOWFeNMsmOU2s
        //Le jeton pour mikebrown@example.com : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1pa2Vicm93bkBleGFtcGxlLmNvbSJ9.ASWsuJ30tK0imXqGDWd-lf7-Tp0cqSGDrj5dD2tuyMs
        //Le jeton pour tata@example.com : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InRhdGFAZXhhbXBsZS5jb20ifQ.ZxHZBjy_2yRTE9tDVizgoiBTehz7D9DxYIDNYNl8NUw
        //Le jeton pour janesmith@example.com : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImphbmVzbWl0aEBleGFtcGxlLmNvbSJ9.cGgDyzzc-Yj6hPvht6PmCHGHkAT3UAD61TtoGY5iAAw
        //Le jeton pour lambdaS3@example.com : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImxhbWJkYVMzQGV4YW1wbGUuY29tIn0.Wt1fxVemxwxzbE_PNvWGhqdFdTAtt1fKYbIgFjT8GJ4
        System.out.println("Le jeton pour  : " + JwtBuilder.buildJwt("lambdaS3@example.com"));
        String res = new JwtVerifier().extractBucketKey("https://publicationspronosbucket1transformed.s3.eu-west-3.amazonaws.com/0e409f9f-14f1-41df-acac-3631b6fa3134-189.jpeg");
        System.out.println(res);

    }


    public String extractBucketName(String str){
        try {
            URL urlObject = new URL(str);
            return urlObject.getHost().substring(0, urlObject.getHost().indexOf('.'));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }
    public String extractBucketKey(String str){
        try {
            URL urlObject = new URL(str);
            return urlObject.getHost().substring(0, urlObject.getHost().indexOf('.'));
        } catch (MalformedURLException e) {
            Log.error("Erreur recuperation du nom du bucket de l'image à supprimer... " + e.getMessage());
            return  null;
        }

    }
}
