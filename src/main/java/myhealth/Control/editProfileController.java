package myhealth.Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class editProfileController extends sceneHandler implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button createButton;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private Label usernameDisplay;

    public void saveChanges(ActionEvent event){
        try {
            //check passwords before saving and update user

            if(!db.updateUser(firstName.getText(), lastName.getText())) {
                super.ErrorAlertMsg("Unsuccessful", "Error in updating user.");
            }
            else {
                super.confirmationAlertMsg("Successful", "User updated in database.");
                db.getUser().setFirstName(firstName.getText());
                db.getUser().setLastName(lastName.getText());
                super.sceneSwitcher(event, "records.fxml");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void back(ActionEvent event){
        try {
            super.sceneSwitcher(event, "records.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameDisplay.setText("Welcome " + super.getUser().getFirstName() + " " + super.getUser().getLastName() + "!");
        usernameDisplay.setAlignment(Pos.CENTER);

        firstName.setText(super.getUser().getFirstName());
        lastName.setText((super.getUser().getLastName()));
    }
}
