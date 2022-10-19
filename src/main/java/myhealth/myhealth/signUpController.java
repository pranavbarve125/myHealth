package myhealth.myhealth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import  java.security.*;

import java.io.IOException;
import java.sql.SQLException;

public class signUpController extends sceneHandler {

    @FXML
    private TextField firstname;

    @FXML
    private PasswordField password;

    @FXML
    private TextField secondname;

    @FXML
    private Button signupbutton;

    @FXML
    private TextField username;

    @FXML
    private Label messageAlert;

    public void userSignUp(ActionEvent event) throws IOException, SQLException, NoSuchAlgorithmException, NoSuchProviderException{
        //currentUser = db.getUser(username.getText().toString(), password.getText().toString());
        if (firstname.getText().isEmpty() || secondname.getText().isEmpty() || password.getText().isEmpty() || username.getText().isEmpty()){
            messageAlert.setText("Enter information in all boxes.");
        }
        else{
            // insert in the database.

            // hashing the password

            boolean flag = super.db.insertUser(new User(username.getText().toString(), password.getText().toString(), firstname.getText().toString(), secondname.getText().toString()));
            if(flag == true){
                super.sceneSwitcher(event, "SuccessfulSignIn.fxml");
            }
            else{
                messageAlert.setText("Error. Enter again.");
            }
        }
    }

}