package asah.otak;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverTest {
    @Test
    void testRegister() {

        try{
            Driver mySqlDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mySqlDriver);
        }catch (SQLException exception) {
            Assertions.fail(exception);
        }


    }
}
