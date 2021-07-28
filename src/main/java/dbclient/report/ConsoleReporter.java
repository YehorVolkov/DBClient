package dbclient.report;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ConsoleReporter implements ReportCreator {

    @Override
    public void reportResult(ResultSet resultSet) throws SQLException {
        resultSet.beforeFirst();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        printHeaders(columnsNumber, rsmd);
        printValues(columnsNumber, resultSet);
    }

    private void printHeaders(int columnsNumber, ResultSetMetaData rsmd) throws SQLException {
        for (int i = 1; i <= columnsNumber; i++) {
            if (i > 1) {
                System.out.print(" | ");
            }
            System.out.print(rsmd.getColumnName(i));
        }
        System.out.println();
    }

    private void printValues(int columnsNumber, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) {
                    System.out.print(" | ");
                }
                System.out.print(resultSet.getString(i));
            }
            System.out.println();
        }
    }
}
