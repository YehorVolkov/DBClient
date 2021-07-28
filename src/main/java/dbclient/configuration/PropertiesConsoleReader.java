package dbclient.configuration;

public class PropertiesConsoleReader implements PropertiesReader {

    @Override
    public String getUsername() {
        return System.getProperty(Configuration.USER_PROPERTY);
    }

    @Override
    public String getPassword() {
        return System.getProperty(Configuration.PASS_PROPERTY);
    }

    @Override
    public String getDbUrl() {
        return System.getProperty(Configuration.DB_URL_PROPERTY);
    }

    @Override
    public String getReportFileName() {
        return System.getProperty(Configuration.PROPERTIES_FILE_NAME);
    }

    @Override
    public String getReportPath() {
        return System.getProperty(Configuration.REPORT_PATH);
    }
}
