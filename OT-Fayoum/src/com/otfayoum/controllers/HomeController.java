package com.otfayoum.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.otfayoum.utils.test;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HomeController implements Initializable {

    @FXML
    private Button btnName;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnLogout;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
        btnName.setText(test.getName());
    }

    public void handleButtonAction(MouseEvent event) {
        if(event.getSource() == btnLogout){
            try {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/otfayoum/fxml/login.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(event.getSource() == btnExit){
            try{
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
