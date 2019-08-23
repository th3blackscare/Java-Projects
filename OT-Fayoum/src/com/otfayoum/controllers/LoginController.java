package com.otfayoum.controllers;

import com.otfayoum.utils.ConnectionUI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

import com.otfayoum.utils.test;
public class LoginController implements Initializable {

    @FXML
    public TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnSignin;

    @FXML
    private Label lblErrors;

    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet;
    String name ;
    public void handleButtonAction(MouseEvent event) {
        if(event.getSource() == btnSignin){
            if(login().equals("Success")){
                try{
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/otfayoum/fxml/sample.fxml")));
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // TODO
        if(con == null){
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Connection Error: Check Internet Connection or Server ");
        } else{
            lblErrors.setTextFill(Color.WHITE);
            lblErrors.setText("Connection initiated");
        }
    }

    public LoginController() {
        con = ConnectionUI.ConnDB();

    }

    private String login(){

        String email = txtUsername.getText();
        String password = txtPassword.getText();

        //query
        String sql = "SELECT * FROM users Where usern = ? and password = ?";
        int id;
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                lblErrors.setTextFill(Color.TOMATO);
                lblErrors.setText("Enter Correct Email/Password");
                //System.err.println("Wrong Logins --///");
                return "Error";
            } else {
                lblErrors.setTextFill(Color.GREEN);
                lblErrors.setText("Login Successful..Redirecting..");
                name = resultSet.getString("name");
                id = resultSet.getInt("id");
                test t = new test();
                t.setName(name);
                //System.out.println("Successfull Login");
                return "Success";
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return "Exception";
        }
    }
    private void getName(){
        try {
            String email = txtUsername.getText();
            String sql = "SELECT * FROM users Where usern = ?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                System.out.println(resultSet.getString("name"));
                name = resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
