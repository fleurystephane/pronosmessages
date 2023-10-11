package com.yosfl.utils;

import com.yosfl.users.JwtBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Utils {
    public static final String ALICELEE_EMAIL = "alicelee@example.com";
    public static final String ALICELEE_TOKEN = JwtBuilder.buildJwt(ALICELEE_EMAIL);
    public static final int ALICELEE_ID = 4;
    public static final String JOHNDOE_EMAIL = "johndoe@example.com";
    public static final String JOHNDOE_TOKEN = JwtBuilder.buildJwt(JOHNDOE_EMAIL);
    public static final int JOHNDOE_ID = 1;
    public static final String JANESMITH_EMAIL = "janesmith@example.com";
    public static final String JANESMITH_TOKEN = JwtBuilder.buildJwt(JANESMITH_EMAIL);
    public static final int JANESMITH_ID = 2;
    public static final String BOBJOHNSON_EMAIL = "bobjohnson@example.com";
    public static final String BOBJOHNSON_TOKEN = JwtBuilder.buildJwt(BOBJOHNSON_EMAIL);
    public static final int BOBJOHNSON_ID = 3;
    public static final String MIKEBROWN_EMAIL = "mikebrown@example.com";
    public static final String MIKEBROWN_TOKEN = JwtBuilder.buildJwt(MIKEBROWN_EMAIL);
    public static final int MIKEBROWN_ID = 5;
    public static final String PABLO_EMAIL = "tata@example.com";
    public static final String PABLO_TOKEN  = JwtBuilder.buildJwt(PABLO_EMAIL);
    public static final int PABLO_ID = 6;

    public static Response retrieveConversationsForAlice(){
        return retrieveConversationsForUser(ALICELEE_TOKEN, ALICELEE_EMAIL);
    }

    public static Response retrieveConversationsForUser(String token, String email) {
        return getHeaderOctetStream(token, email)
                .when()
                .get("/messagesapi/v1/conversations")
                .then().extract().response();
    }

    public static Response AlicePostMessageToConversation(String body, long idConversation){
        return postMessageToConversation(ALICELEE_TOKEN, ALICELEE_EMAIL, body, idConversation);
    }

    private static Response postMessageToConversation(String token, String email, String body, long idConversation) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .header("email", email)
                .and()
                .body(body)
                .post("/messagesapi/v1/conversations/"+idConversation+"/messages")
                .then()
                .extract().response();
    }


    //----------------------- TO DELETE ------------------------
    public static Response johndoePostPublication(String content){
        return postRestPublication(JOHNDOE_TOKEN, JOHNDOE_EMAIL, content);
    }
    public static Response johndoeRecupereSesPropresPublications(){
        return getPublicationsUser(JOHNDOE_TOKEN, JOHNDOE_EMAIL);
    }
    public static Response johndoeRecupereLaPublication(int id){
        return getPublication(JOHNDOE_TOKEN, JOHNDOE_EMAIL, id);
    }
    public  static Response johndoeDeletePublication(long idPub){
        return deleteRestPublicationResponse(JOHNDOE_TOKEN, JOHNDOE_EMAIL, idPub);
    }
    public static Response johndoeAjouteImageAUnePublication(long idPublication, String imagePath){
        return creatorAjouteImageAUnePublication(JOHNDOE_TOKEN, JOHNDOE_EMAIL, idPublication,
                getImageBytes(imagePath), imagePath.substring(imagePath.lastIndexOf('.')+1));
    }
    public  static Response pabloPostPublication(String content){
        return postRestPublication(PABLO_TOKEN, PABLO_EMAIL, content);
    }

    public static Response aliceSAbonneAJohn() {
        return userA_sabonne_a_B(ALICELEE_TOKEN,ALICELEE_EMAIL,JOHNDOE_ID);

    }
    public static Response aliceSAbonneAPablo() {
        return userA_sabonne_a_B(ALICELEE_TOKEN,ALICELEE_EMAIL,PABLO_ID);
    }
    public static Response aliceCommenteUnePublication(String body, int idPublication) {
        return userCommentUnePublication(ALICELEE_TOKEN, ALICELEE_EMAIL, body, idPublication);
    }
    public static Response aliceLikePublication(long idPublication) {
        return userLikeUnePublication(ALICELEE_TOKEN, ALICELEE_EMAIL, idPublication);
    }
    public static Response aliceSavePublication(long idPublication){
        return userSavePublication(ALICELEE_TOKEN, ALICELEE_EMAIL, idPublication);
    }
    public static Response aliceGetPublicationsSaved(){
        return userGetPublicationsSaved(ALICELEE_TOKEN, ALICELEE_EMAIL);
    }
    public static Response aliceSupprimeUnePublicationSaved(long idPublication){
        return userDeletePublicationSaved(ALICELEE_TOKEN, ALICELEE_EMAIL, idPublication);
    }
    public static Response aliceRecupereLesCommentaires(long idPublication){
        return userRecupereLesCommentaires(ALICELEE_TOKEN,ALICELEE_EMAIL,idPublication);
    }



    public static Response mikeSAbonneAJohn() {
        return userA_sabonne_a_B(MIKEBROWN_TOKEN,MIKEBROWN_EMAIL,JOHNDOE_ID);
    }
    public static Response mikebrownCommenteUnePublication(String body, int idPublication) {
        return userCommentUnePublication(MIKEBROWN_TOKEN, MIKEBROWN_EMAIL, body, idPublication);
    }
    public static Response mikebrownLikePublication(long idPublication) {
        return userLikeUnePublication(MIKEBROWN_TOKEN, MIKEBROWN_EMAIL, idPublication);
    }
    public static Response mikebrownAjouteImageAUnePublication(long idPublication, String imagePath){
        return creatorAjouteImageAUnePublication(MIKEBROWN_TOKEN, MIKEBROWN_EMAIL, idPublication,
                getImageBytes(imagePath), imagePath.substring(imagePath.lastIndexOf('.')+1));
    }
    public static Response mikebrownGetImageDUnePublication(long idPublication, int order){
        return userGetImageDUnePublication(MIKEBROWN_TOKEN, MIKEBROWN_EMAIL,idPublication, order);
    }
    public static Response mikebrownSavePublication(long idPublication){
        return userSavePublication(MIKEBROWN_TOKEN, MIKEBROWN_EMAIL, idPublication);
    }
    public static Response mikebrownGetPublicationsSaved(){
        return userGetPublicationsSaved(MIKEBROWN_TOKEN, MIKEBROWN_EMAIL);
    }
    public static Response mikebrownAjouteImageASonAccount(String imagePath){
        return userAjouteImageASonCompte(MIKEBROWN_TOKEN, MIKEBROWN_EMAIL,
                getImageBytes(imagePath), imagePath.substring(imagePath.lastIndexOf('.')+1));
    }

    public static Response mikeBrownLikesComment(long idPublication, long idComment){
        return userLikesComment(MIKEBROWN_TOKEN, MIKEBROWN_EMAIL, idPublication, idComment);
    }
    public static Response mikebrownDislikesComment(String idPublication, String idComment){
        return userDislikesComment(MIKEBROWN_TOKEN, MIKEBROWN_EMAIL, idPublication, idComment);
    }
    public static Response mikebrownRecupereLesCommentaires(long idPublication){
        return userRecupereLesCommentaires(MIKEBROWN_TOKEN,MIKEBROWN_EMAIL,idPublication);
    }


    public static Response bobSAbonneAPablo(){
        return userA_sabonne_a_B(BOBJOHNSON_TOKEN,BOBJOHNSON_EMAIL,PABLO_ID);
    }


    public static Response janesmithLikePublication(long idPublication) {
        return userLikeUnePublication(JANESMITH_TOKEN, JANESMITH_EMAIL, idPublication);
    }



    private static Response userLikesComment(String token, String email, long idPublication, long idComment) {
        return getWhen(token, email)
                .post("/shareskillsapi/v1/publications/"+idPublication+"/comments/"+idComment+"/likes")
                .then().extract().response();
    }

    private static Response userDislikesComment(String token, String email,
                                                String idPublication, String idComment){
        return getWhen(token, email)
                .delete("/shareskillsapi/v1/publications/"+idPublication+"/comments/"+idComment+"/likes")
                .then().extract().response();
    }


    public static Response getPublicationsUser(String token, String email){
        return getHeaderOctetStream(token, email)
                .when()
                .get("/shareskillsapi/v1/publications/users/"+JOHNDOE_ID)
                .then().extract().response();
    }
    public static Response getPublication(String token, String email, int idPublication){
        return getHeaderOctetStream(token, email)
                .when()
                .get("/shareskillsapi/v1/publications/"+idPublication)
                .then().extract().response();
    }

    private static Response userAjouteImageASonCompte(String token, String email, byte[] image, String extension) {
        return getHeaderOctetStream(token, email)
                .body(image)
                .when()
                .put("/shareskillsapi/v1/users/accountpicture/"+extension)
                .then().extract().response();
    }

    private static Response userSavePublication(String token, String email, long idPublication) {
        return getWhen(token, email)
                .post("/shareskillsapi/v1/publications/"+idPublication+"/saved").then().extract().response();
    }

    public static Response getRestUserResponse(String token, String email, String idUser) {
        return getWhen(token, email).get("/shareskillsapi/v1/publications/users/"+idUser)
                .then()
                .extract().response();
    }

    public static Response getRestPublicationsByUserResponse(String token, String email, String idUser) {
        return getWhen(token, email)
                .get("/shareskillsapi/v1/publications/users/"+idUser)
                .then().extract().response();
    }

    public static Response getRestSurveyResponse(String token, String email, String idSondage){
        return getWhen(token, email)
                .get("/shareskillsapi/v1/publications/"+idSondage).then().extract().response();
    }

    public static Response postRestPublication(String token, String email, String body){
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .header("email", email)
                .and()
                .body(body)
                .when()
                .post("/shareskillsapi/v1/publications")
                .then()
                .extract().response();
    }

    public static Response postRestSurveyResponse(String token, String email, String body){
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .header("email", email)
                .and()
                .body(body)
                .when()
                .post("/shareskillsapi/v1/publications/survey").then().extract().response();
    }


    public static Response deleteRestPublicationResponse(String token, String email, long idPub){
        return getWhen(token, email)
                .delete("/shareskillsapi/v1/publications/"+idPub)
                .then().extract().response();
    }


    private static Response userLikeUnePublication(String token, String email, long idPublication) {
        return getWhen(token, email)
                .post("/shareskillsapi/v1/publications/"+idPublication+"/likes").then().extract().response();

    }


    private static Response userA_sabonne_a_B(String userAToken, String userAEmail,
                                              int idUser) {
        return given()
                .header("Authorization", "Bearer " + userAToken)
                .header("email", userAEmail)
                .header("Content-type", "application/json")
                .body("""
                            {
                            "info": "Abonnement ....",
                            "creatorId": """+idUser+"""
                            }""")
                .when()
                .post("/shareskillsapi/v1/connections")
                .then()
                .extract().response();
    }


    private static Response userCommentUnePublication(String token, String email, String body, int idPublication) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .header("email", email)
                .body(body)
                .when()
                .post("/shareskillsapi/v1/publications/"+idPublication+"/comments");
    }

    private static Response creatorAjouteImageAUnePublication(
            String token, String email, long idPublication, byte[] image, String extension){
        return getHeaderOctetStream(token, email)
                .body(image)
                .when()
                .put("/shareskillsapi/v1/publications/"+idPublication+"/mainpicture/"+extension)
                .then()
                .extract().response();
    }

    public static Response userGetImageDUnePublication(
            String token, String email, long idPublication, int order){
        return getHeaderOctetStream(token, email)
                .when()
                .get("/shareskillsapi/v1/publications/"+idPublication+"/pictures?order="+order)
                .then().extract().response();
    }

    public static Response userDeletePublicationSaved(
            String token, String email, long idPublication) {
        return getWhen(token, email)
                .delete("/shareskillsapi/v1/publications/" + idPublication + "/saved")
                .then().extract().response();
    }

    public static Response userGetPublicationsSaved(String token, String email){
        return getWhen(token, email)
                .get("/shareskillsapi/v1/publications/saved")
                .then()
                .extract().response();
    }

    private static Response userRecupereLesCommentaires(String token, String email, long idPublication) {
        return getWhen(token, email)
                .get("/shareskillsapi/v1/publications/"+idPublication+"/comments");
    }

    private static RequestSpecification getWhen(String token, String email){
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .header("email", email)
                .when();
    }

    private static RequestSpecification getHeaderOctetStream(String token, String email){
        return given()
                .header("Content-type", "application/octet-stream")
                .header("Authorization", "Bearer " + token)
                .header("email", email);
    }

    private static byte[] getImageBytes(String imagePath) {
        File imageFile = new File(Utils.class.getResource(imagePath).getFile());
        byte[] imageBytes = new byte[(int) imageFile.length()];
        try (FileInputStream fis = new FileInputStream(imageFile)) {
            fis.read(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageBytes;
    }
}
