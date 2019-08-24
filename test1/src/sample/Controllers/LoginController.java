package sample.Controllers;

import com.sun.deploy.util.FXLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.utils.ConnectionUtil;

public class LoginController implements Initializable {


    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnSignin;

    @FXML
    private Label lblErrors;

    Connection connection = null;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleButtonAction(MouseEvent event) throws IOException {
        if(event.getSource() == btnSignin){
            if(login().equals("Success")){
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("fxml/DashFX.fxml")));
            }
        }
    }
    public LoginController() throws SQLException {
        connection = ConnectionUtil.ConnDB();
    }
    private String login(){

        String email = txtUsername.getText();
        String password = txtPassword.getText();

        //query
        String sql = "SELECT * FROM users Where usern = ? and password = ?";
        int id;
        try {
            preparedStatement = connection.prepareStatement(sql);
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
                //System.out.println("Successfull Login");
                return "Success";
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return "Exception";
        }
    }

}
