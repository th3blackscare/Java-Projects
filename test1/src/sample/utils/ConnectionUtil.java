package sample.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionUtil {
    Connection conn = null;
    public static Connection ConnDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://167.86.67.9/arabitga_pubg?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "arabitga_root", "c93e4f962ad683533b4b78a4cb085002");
            return con;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("ConnectionUtil : "+e.getMessage());
            return null;
        }
    }
}
