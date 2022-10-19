package myhealth.myhealth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class SignInSuccessController extends sceneHandler{

    @FXML
    private Button loginbutton;

    public void backToLogin(ActionEvent event) throws IOException {
        super.sceneSwitcher(event, "login.fxml");
    }

}
