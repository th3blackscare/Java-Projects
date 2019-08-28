package com.otfayoum.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrdersController implements Initializable {


    @FXML
    private ComboBox<String> lstStatus;

    @FXML
    private ComboBox<?> lstDelivery;

    @FXML
    private Button btnStatus;

    @FXML
    private Button btnAssign;

    @FXML
    private Button btnExit;

    @FXML
    private TableView<?> tblData;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lstStatus.getItems().addAll("preparing", "Ready to Go", "Cancelled");
    }

    @FXML
    public void handleButtonAction(MouseEvent event) {
        if(event.getSource() == btnExit){
            try {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/otfayoum/fxml/Dashboard.fxml")));
                stage.setScene(scene);
                stage.show();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
