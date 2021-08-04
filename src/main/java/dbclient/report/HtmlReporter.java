package dbclient.report;

import dbclient.configuration.Configuration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class HtmlReporter implements ReportCreator {

    private BufferedWriter bw;
    private final Configuration configuration;

    public HtmlReporter(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void reportResult(ResultSet resultSet) throws SQLException, IOException {
        File reportPath = new File((configuration.getReportPath() != null ? configuration.getReportPath() : "reports/"));
        if (!reportPath.exists()) {
            reportPath.mkdirs();
        }
        File htmlReport = new File(reportPath.getPath() + "/" + configuration.getReportFileName() + ".html");
        try {
            bw = new BufferedWriter(new FileWriter(htmlReport));
            bw.write("<table BORDER=1 CELLPADDING=10px CELLSPACING=0 WIDTH=100%>");
            resultSet.beforeFirst();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            bw.write("<tr>");
            for (int i = 1; i <= columnsNumber; i++) {
                bw.write("<th>" + rsmd.getColumnName(i) + "</th>");
            }
            bw.write("</tr>");
            while(resultSet.next()) {
                bw.write("<tr>");
                for (int i = 1; i <= columnsNumber; i++) {
                    bw.write("<td><left>" + resultSet.getString(i) + "</left></td>");
                }
                bw.write("</tr>");
            }
            bw.write("</table>");
        } finally {
            bw.close();
        }
    }
}
