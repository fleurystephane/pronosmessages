package com.yosfl.messages;

import com.yosfl.DatabaseResource;
import com.yosfl.SqlScriptRunner;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

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
        
    }
}
