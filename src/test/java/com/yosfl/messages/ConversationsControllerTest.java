package com.yosfl.messages;

import com.yosfl.DatabaseResource;
import com.yosfl.SqlScriptRunner;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

import static com.yosfl.utils.Utils.AlicePostMessageToConversation;
import static com.yosfl.utils.Utils.retrieveConversationsForAlice;

@QuarkusTest
@QuarkusTestResource(DatabaseResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ConversationsControllerTest {

    @Inject
    DataSource dataSource;

    @BeforeEach
    public void setUp() {
        try {
            SqlScriptRunner.runScript(dataSource, "src/test/resources/import.sql");
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(1)
    @DisplayName("Vérification de la bonne récupération de la liste des conversations d'un user")
    public void should_() {
        Response response = retrieveConversationsForAlice();
        Assertions.assertEquals(200, response.getStatusCode());
        response.prettyPrint();
    }

    @Test
    @Order(2)
    @DisplayName("Vérification qu'après avoir posté un message, on le retrouve dans la conversation")
    public void should_retrieve_last_message_created(){
        String bodyPost = """
                {
                "text":"Voilà je suis là!"
                }""";
        Response responsePost = AlicePostMessageToConversation(bodyPost, 1);
        Assertions.assertEquals(200, responsePost.getStatusCode());
        Response response = retrieveConversationsForAlice();
        Assertions.assertEquals(200, response.getStatusCode());
        response.prettyPrint();
    }
}
