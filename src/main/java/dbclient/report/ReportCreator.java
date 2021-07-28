package dbclient.report;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ReportCreator {

    void reportResult(ResultSet resultSet) throws SQLException, IOException;
}
