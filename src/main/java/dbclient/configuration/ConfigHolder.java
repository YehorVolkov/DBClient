package dbclient.configuration;

public class ConfigHolder implements Configuration {
    // TODO maybe have here two classes "DBConfigHolder" and "ReportConfigHolder", provide getters to them
    //  and pass them to Client and HtmlReporter constructors respectively?

    private final String username;
    private final String password;
    private final String dbUrl;
    private final String reportFileName;
    private final String reportPath;

    public ConfigHolder(PropertiesReader consoleReader, PropertiesReader fileReader) {
        username = consoleReader.getUsername() != null ? consoleReader.getUsername() : fileReader.getUsername();
        password = consoleReader.getPassword() != null ? consoleReader.getPassword() : fileReader.getPassword();
        dbUrl = consoleReader.getDbUrl() != null ? consoleReader.getDbUrl() : fileReader.getDbUrl();
        reportFileName = consoleReader.getReportFileName() != null ? consoleReader.getReportFileName() : fileReader.getReportFileName();
        reportPath = consoleReader.getReportPath() != null ? consoleReader.getReportPath() : fileReader.getReportPath();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getDbUrl() {
        return dbUrl;
    }

    @Override
    public String getReportFileName() {
        return reportFileName;
    }

    @Override
    public String getReportPath() {
        return reportPath;
    }
}
