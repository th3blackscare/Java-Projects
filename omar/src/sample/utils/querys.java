package sample.utils;
import javafx.scene.paint.Color;
import sample.utils.ConnectionUtil;

import javax.swing.text.TableView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class querys {
    Connection connection;
    String table;
    PreparedStatement preparedStatement;
    public querys() { connection = ConnectionUtil.conDB(); }
    public String DeletRow(String table, String where){
        try {
            String st = "DELETE FROM "+ table +" WHERE "+where;
            preparedStatement = connection.prepareStatement(st);
            preparedStatement.executeUpdate();
            return "Success";
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //lblStatus.setTextFill(Color.TOMATO);
            //lblStatus.setText(ex.getMessage());
            return "Exception";
        }
    }
}
