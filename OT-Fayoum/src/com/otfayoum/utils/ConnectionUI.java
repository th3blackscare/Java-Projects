package com.otfayoum.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionUI {
    Connection conn = null;
    public static Connection ConnDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/t1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "reemomar01019965508");
            return con;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("ConnectionUtil : "+e.getMessage());
            return null;
        }
    }
}
