package myhealth.Control;

import Model.Record;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
            super.triggerAlertMsg("Empty Fields", "Enter information in all boxes.");
        }
        else{
            // hashing the password

            // insert in the database.
            User currentUser = new User(username.getText().toString(), password.getText().toString(), firstname.getText().toString(), secondname.getText().toString());
            boolean flag = super.db.insertUser(currentUser);
            if(flag == true){

                currentUser.setUserID(super.db.getUserID());
                super.setUser(currentUser);
                super.sceneSwitcher(event, "records.fxml");
            }
            else{
                //switch to login screen with a message
                super.triggerAlertMsg("Username already present.", "Username is already present. Use a different username.");
            }
        }
    }

    public void logIn(ActionEvent event) throws IOException{
        super.sceneSwitcher(event, "login.fxml");
    }

}