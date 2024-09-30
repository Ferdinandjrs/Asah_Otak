package asah.otak;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementTest {
    @Test
    void testCreateStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        Statement statement = connection.createStatement();

        statement.close();

        connection.close();
    }
}
