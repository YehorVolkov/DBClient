package dbclient;

import dbclient.configuration.Configuration;
import dbclient.configuration.ConfigHolder;
import dbclient.configuration.PropertiesConsoleReader;
import dbclient.configuration.PropertiesFileReader;
import dbclient.report.ConsoleReporter;
import dbclient.report.HtmlReporter;
import dbclient.report.ReportCreator;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Client {

    private final Connection connection;
    private final Statement statement;
    private ResultSet resultSet;
    private final List<ReportCreator> reporters;
    private final Scanner scanner;

    public static void main(String[] args) {
        int exitCode = 0;
        Client client = null;
        try {
            Configuration config = new ConfigHolder(new PropertiesConsoleReader(), new PropertiesFileReader());
            client = new Client(config, System.in, new ConsoleReporter(), new HtmlReporter(config));
            while (true) {
                client.readAndExecuteQuery();
            }
        } catch (IOException | SQLException ioException) {
            exitCode = 1;
            ioException.printStackTrace();
        } finally {
            if (client != null) {
                exitCode |= client.closeAllResources();
            }
            System.exit(exitCode);
        }
    }

    public Client(Configuration configuration, InputStream is, ReportCreator... reporters) throws SQLException {
        connection = DriverManager.getConnection(
                configuration.getDbUrl(),
                configuration.getUsername(),
                configuration.getPassword());
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.reporters = Arrays.asList(reporters);
        scanner = new Scanner(is);
    }

    public int closeAllResources() {
        int returnCode = 0;
        returnCode |= closeResource(resultSet);
        returnCode |= closeResource(statement);
        returnCode |= closeResource(connection);
        return returnCode;
    }

    public void readAndExecuteQuery() throws SQLException, IOException {
        String userInput = scanner.nextLine();
        if (userInput.toUpperCase().startsWith("SELECT")) {
            resultSet = statement.executeQuery(userInput);
            for (ReportCreator reporter : reporters) {
                reporter.reportResult(resultSet);
            }
        } else {
            System.out.println("Num of rows affected: " + statement.executeUpdate(userInput));
        }
    }

    private int closeResource(AutoCloseable resource) {
        int returnCode = 0;
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                returnCode = 1;
                System.err.println("Resource " + resource.getClass() + " could not be properly closed. Stacktrace:");
                e.printStackTrace();
            }
        }
        return returnCode;
    }

}
