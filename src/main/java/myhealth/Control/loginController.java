package myhealth.Control;

import Model.Record;
import Model.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class loginController extends sceneHandler {


    User currentUser;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="loginbutton"
    private Button loginbutton; // Value injected by FXMLLoader

    @FXML // fx:id="password"
    private PasswordField password; // Value injected by FXMLLoader

    @FXML // fx:id="signupbutton"
    private Button signupbutton; // Value injected by FXMLLoader

    @FXML // fx:id="username"
    private TextField username; // Value injected by FXMLLoader

    public void userLogIn(ActionEvent event) {
        try {
            currentUser = super.db.getUser(username.getText().toString(), password.getText().toString());
            System.out.println("This is the current user : " + currentUser);

            if (currentUser == null) {
//            username.setText("");
//            password.setText("");
                super.triggerAlertMsg("Wrong Login", "Wrong username or password.");
            } else {
                System.out.println("Success.");
                username.setText("");
                password.setText("");
                super.setUser(currentUser);
                super.sceneSwitcher(event, "records.fxml");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void signUp(ActionEvent event) throws IOException, SQLException{
        super.sceneSwitcher(event, "signup.fxml");
    }
}


//
//    private boolean checkLogIn(ActionEvent event) throws IOException{
//        return True;
//    }

