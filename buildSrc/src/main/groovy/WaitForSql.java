import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Supplier;

// Sql Connection task
public class WaitForSql extends DefaultTask {

    @TaskAction
    public void waitForSql() {
        System.out.println("Waiting for Sql...");

        loadDriver();

        waitForConnection();
    }

    private void loadDriver() {
        try {
            System.out.println("Trying to initialize driver");

            String datasourceDriverClassName = getenv("SPRING_DATASOURCE_DRIVER_CLASS_NAME", "org.postgresql.Driver");
            Class.forName(datasourceDriverClassName);

            System.out.println("Initialized succeed");
        } catch (ClassNotFoundException e) {
            System.out.println("Initialization failed");

            throw new RuntimeException(e);
        }
    }

    private String getenv(String name, String defaultValue) {
        return Optional.ofNullable(System.getenv(name)).orElse(defaultValue);
    }

    private String getenv(String name, Supplier<String> defaultValue) {
        return Optional.ofNullable(System.getenv(name)).orElseGet(defaultValue);
    }

    private void waitForConnection() {
        while (true) {
            Connection connection = null;
            try {
                System.out.println("Trying to connect...");

                String datasourceUrl = getenv("SPRING_DATASOURCE_URL", () -> String.format("dbc:postgresql://%s/proto_local", getenv("DOCKER_HOST_IP", "localhost")));
                String datasourceUsername = getenv("SPRING_DATASOURCE_USERNAME", "postgres");
                String datasorucePassword = getenv("SPRING_DATASOURCE_PASSWORD", "p0stgr@s");

                connection = DriverManager.getConnection(datasourceUrl, datasourceUsername, datasorucePassword);

                System.out.println("Connection succeed");

                break;
            } catch (SQLException e) {
                System.out.println("Connection failed");

                sleep();
            } finally {
                if (connection != null) {
                    closeConnection(connection);
                }
            }
        }
    }

    private void sleep() {
        System.out.println("sleeping for 5 seconds...");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
    }

    private void closeConnection(Connection connection) {
        try {
            System.out.println("Trying to close connection");

            connection.close();

            System.out.println("Connection closed");
        } catch (SQLException e) {
            System.out.println("Failed to close connection, see stack trace:");

            e.printStackTrace();
        }
    }
}
