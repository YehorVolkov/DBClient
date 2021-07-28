package dbclient.configuration;

public interface Configuration {

    String USER_PROPERTY = "user";
    String PASS_PROPERTY = "password";
    String DB_URL_PROPERTY = "db_url";
    String PROPERTIES_FILE_NAME = "application.properties";
    String REPORT_FILE_NAME = "reportFileName";
    String REPORT_PATH = "reportPath";

    String getUsername();
    String getPassword();
    String getDbUrl();
    String getReportFileName();
    String getReportPath();
}
