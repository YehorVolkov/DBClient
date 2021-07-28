package dbclient.configuration;

public interface PropertiesReader {
    String getUsername();
    String getPassword();
    String getDbUrl();
    String getReportFileName();
    String getReportPath();
}
