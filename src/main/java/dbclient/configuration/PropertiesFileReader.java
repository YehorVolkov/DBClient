package dbclient.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileReader implements PropertiesReader {

    private final Properties properties;

    public PropertiesFileReader() throws IOException {
        properties = new Properties();
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(Configuration.PROPERTIES_FILE_NAME)) {
            properties.load(inputStream);
        }
    }

    @Override
    public String getUsername() {
        return properties.getProperty(Configuration.USER_PROPERTY);
    }

    @Override
    public String getPassword() {
        return properties.getProperty(Configuration.PASS_PROPERTY);
    }

    @Override
    public String getDbUrl() {
        return properties.getProperty(Configuration.DB_URL_PROPERTY);
    }

    @Override
    public String getReportFileName() {
        return properties.getProperty(Configuration.REPORT_FILE_NAME);
    }

    @Override
    public String getReportPath() {
        return properties.getProperty(Configuration.REPORT_PATH);
    }
}
