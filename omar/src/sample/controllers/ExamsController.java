package sample.controllers;

import java.io.IOException;
import java.sql.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.time.LocalDate;
import java.util.regex.Pattern;
import sample.utils.querys;

import sample.utils.ConnectionUtil;
public class ExamsController implements Initializable {
    @FXML
    private TextField txtStID,txtET,txtResult;
    @FXML
    TableView tblData;
    @FXML
    private Button btnBack,btnSave,btnDelete;
    @FXML
    Label lblStatus,lblDate;

    PreparedStatement preparedStatement;
    Connection connection;

    LocalDate now = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LLLL-yyyy");
    String formattedString = now.format(formatter);

    @FXML
    public void handleButtonAction(MouseEvent event) {
        if (event.getSource() == btnBack) {
            try {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/fxml/home.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } else if (event.getSource() == btnSave) {
            //check if not empty
            if (txtStID.getText().isEmpty() || txtResult.getText().isEmpty() || txtET.getText().isEmpty()) {
                lblStatus.setTextFill(Color.TOMATO);
                lblStatus.setText("Enter all details");
            } else {
                saveData();
            }
        } else if (event.getSource() == btnDelete) {
            Object selectedItem = tblData.getSelectionModel().getSelectedItem().toString();
            String[] parts = ((String) selectedItem).split(", ");
            String[] parts1 = parts[0].split(Pattern.quote("["));
            querys q = new querys();
            if((q.DeletRow("exams"," OpreationId="+"\""+parts1[1]+"\"")) == "Success"){
                lblStatus.setTextFill(Color.GREEN);
                lblStatus.setText("Deleted Successfully");
                fetRowList();
            }

        }
    }

    public ExamsController(){connection = ConnectionUtil.conDB();}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblDate.setText("Date: "+formattedString);
        tblData.setEditable(true);
        // TODO
        fetColumnList();
        fetRowList();
    }

    private void clearFields(){
        txtET.clear();
        txtResult.clear();
        txtStID.clear();
    }

    private String saveData(){
        try {
          String st = "INSERT INTO exams (id,ExamType,Result,Date) VALUES(?,?,?,?)";
            preparedStatement = connection.prepareStatement(st);
            preparedStatement.setString(1,txtStID.getText());
            preparedStatement.setString(2,txtET.getText());
            preparedStatement.setString(3,txtResult.getText());
            preparedStatement.setString(4, formattedString);
            preparedStatement.executeUpdate();
            lblStatus.setTextFill(Color.GREEN);
            lblStatus.setText("Added Successfully");
            fetRowList();
            clearFields();
          return "Success";
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText(ex.getMessage());
            return "Exception";
        }
    }


    private ObservableList<ObservableList> data;
    String SQL = "SELECT * from exams";

    private void fetColumnList() {

        try {
            ResultSet rs = connection.createStatement().executeQuery(SQL);

            //SQL FOR SELECTING ALL OF CUSTOMER
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                tblData.getColumns().removeAll(col);
                tblData.getColumns().addAll(col);

                //
                // System.out.println("Column [" + i + "] ");

            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());

        }
    }

    //fetches rows and data from the list
    private void fetRowList() {
        data = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            rs = connection.createStatement().executeQuery(SQL);

            while (rs.next()) {
                //Iterate Row
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                //System.out.println("Row [1] added " + row);
                data.add(row);

            }

            tblData.setItems(data);
            tblData.setEditable(true);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
