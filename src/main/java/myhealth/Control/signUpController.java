package myhealth.Control;

import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigInteger;
import  java.security.MessageDigest;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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

    public void userSignUp(ActionEvent event) throws IOException, SQLException, NoSuchAlgorithmException {
        String hashedUserPassword;
        if (firstname.getText().isEmpty() || secondname.getText().isEmpty() || password.getText().isEmpty() || username.getText().isEmpty()){
            super.ErrorAlertMsg("Empty Fields", "Enter information in all boxes.");
        }
        else{
            // hashing the password
            hashedUserPassword = super.hashPassword(password.getText());

            // insert in the database.
            User currentUser = new User(username.getText().toString(), hashedUserPassword, firstname.getText().toString(), secondname.getText().toString());
            boolean flag = super.db.insertUser(currentUser);
            if(flag == true){

                super.setUser(currentUser);
                currentUser.setUserID(super.db.getUserID());
                super.sceneSwitcher(event, "records.fxml");
            }
            else{
                //switch to login screen with a message
                super.ErrorAlertMsg("Username already present.", "Username is already present. Use a different username.");
            }
        }
    }

    public void logIn(ActionEvent event) throws IOException{
        super.sceneSwitcher(event, "login.fxml");
    }

}