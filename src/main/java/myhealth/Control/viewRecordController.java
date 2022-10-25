package myhealth.Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class viewRecordController extends sceneHandler implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label bp;

    @FXML
    private Label note;

    @FXML
    private Label temperature;

    @FXML
    private Label usernameDisplay;

    @FXML
    private Label weight;

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
        note.setWrapText(true);
        setupPane();
    }

    void setupPane() {
        weight.setText(Integer.toString(super.getRecord().getWeight()));
        bp.setText(Integer.toString(super.getRecord().getBloodPressure()));
        note.setText(super.getRecord().getNote());
        temperature.setText(Integer.toString(super.getRecord().getTemperature()));
    }
}
