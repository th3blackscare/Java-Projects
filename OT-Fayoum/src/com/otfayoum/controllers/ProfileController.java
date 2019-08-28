package com.otfayoum.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.otfayoum.utils.user;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ProfileController implements Initializable {

    @FXML
    private TextField txtQe;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnLogout;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtRole;

    @FXML
    private Label txtExit;

    @FXML
    private Label txtUpdate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtID.setText(String.valueOf(user.getLID()));
        txtName.setText(user.getLName());
        txtEmail.setText(user.getLEmail());
        txtPhone.setText(user.getLPhone_number());
        txtUser.setText(user.getLUserName());
        txtAddress.setText(user.getLAddress());
        txtRole.setText(user.getLRole());
        txtExit.setText("Cancel");
        txtUpdate.setText("Update");
    }

    public void onEnter(ActionEvent event) {
        user u = new user();
        if(event.getSource() == txtQe){
            clear();
            if(isNumeric(txtQe.getText())){
                u.getUser(Integer.valueOf(txtQe.getText()));
                txtID.setText(String.valueOf(u.getID()));
                txtName.setText(u.getName());
                txtEmail.setText(u.getEmail());
                txtPhone.setText(u.getPhone_number());
                txtUser.setText(u.getUserName(Integer.valueOf(txtQe.getText())));
                txtAddress.setText(u.getAddress());
                txtRole.setText(u.getRole());
            }
            else{
                u.getUser(txtQe.getText());
                txtID.setText(String.valueOf(u.getID()));
                txtName.setText(u.getName());
                txtEmail.setText(u.getEmail());
                txtPhone.setText(u.getPhone_number());
                txtUser.setText(u.getUserName(u.getID()));
                txtAddress.setText(u.getAddress());
                txtRole.setText(u.getRole());
            }
        }
    }

    public void handleButtonAction(MouseEvent event) throws IOException {
        if(event.getSource() == btnExit){
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/otfayoum/fxml/Dashboard.fxml")));
            stage.setScene(scene);
            stage.show();
        }
        else if(event.getSource() == btnLogout){
            user u = new user();
            if((u.updateData((Integer.valueOf(txtID.getText())),txtName.getText(),txtAddress.getText(),txtPhone.getText(),txtRole.getText(),txtEmail.getText()))== true){
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setContentText("Information Updated Successfully");
            }else{
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Error!");
            }
        }
    }
    public void clear(){
        txtID.clear();
        txtName.clear();
        txtEmail.clear();
        txtPhone.clear();
        txtUser.clear();
        txtAddress.clear();
    }
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
