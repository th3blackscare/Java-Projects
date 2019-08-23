package sample.controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.utils.ConnectionUtil;

import java.io.IOException;
import  java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Button btnExit,btnStudents,btnStudent,btnExams;

    @FXML
    Label lblAllStudents;

    @FXML
    Label lbl1ST;
    @FXML
    Label lbl2ND,lbl3RD,lblMales,lblFemales;
    Connection connection;
    public HomeController (){ connection = ConnectionUtil.conDB(); }
    public  int fetStudents(String wh){
        try{
            String SQL = "SELECT * FROM Students "+wh;
            ResultSet rs = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(SQL);
            rs.last();
            int count = rs.getRow();
            rs.beforeFirst();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblAllStudents.setText("All Students: "+fetStudents(""));
        lbl1ST.setText("Students in Year 1 : "+fetStudents("WHERE syear= "+"\""+"1st"+"\""));
        lbl2ND.setText("Students in Year 2 : "+fetStudents("WHERE syear= "+"\""+"2nd"+"\""));
        lbl3RD.setText("Students in Year 3 : "+fetStudents("WHERE syear= "+"\""+"3rd"+"\""));
        lblMales.setText("Students are Males : "+fetStudents("WHERE gender= "+"\""+"Male"+"\""));
        lblFemales.setText("Students are Females : "+fetStudents("WHERE gender= "+"\""+"Female"+"\""));

    }
    @FXML
    public void handleButtonAction(MouseEvent event) {
        if(event.getSource() == btnExit){
            try {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(event.getSource() == btnStudents){
            try {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxml/Students.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(event.getSource() == btnExams){
            try {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxml/Exams.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
