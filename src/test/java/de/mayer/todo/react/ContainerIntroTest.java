package de.mayer.todo.react;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.LogManager;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@Testcontainers
public class ContainerIntroTest extends AbstractContainerDatabaseTest {

    static {
        // Postgres JDBC driver uses JUL; disable it to avoid annoying, irrelevant, stderr logs during connection testing
        LogManager.getLogManager().getLogger("").setLevel(Level.OFF);
    }

    @Test
    void test() throws SQLException {
        try(PostgreSQLContainer<?> container = new PostgreSQLContainer<>(PostgreSQLContainer.IMAGE)){
            container.start();
            ResultSet resultSet = performQuery(container, "SELECT 1");
            int resultSetInt = resultSet.getInt(1);
            assertEquals("A basic SELECT query succeeds", 1, resultSetInt);
        }
        System.out.println("Hello containers!");
    }
}
