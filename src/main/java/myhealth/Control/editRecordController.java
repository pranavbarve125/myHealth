package myhealth.Control;

import Model.Record;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class editRecordController extends sceneHandler{

    @FXML
    private TextField bp;

    @FXML
    private Button createButton;

    @FXML
    private TextArea note;

    @FXML
    private TextField temperature;

    @FXML
    private Label usernameDisplay;

    @FXML
    private TextField weight;
    Record toInsert;

    void createRecord(ActionEvent event) {
        toInsert = new Record(Integer.parseInt(weight.getText()), Integer.parseInt(temperature.getText()), Integer.parseInt(bp.getText()), note.getText());
        db.insertRecord(toInsert);
    }

    void setupPane(Record toStart){
        weight.setText(Integer.toString(toStart.getWeight()));
        bp.setText(Integer.toString(toStart.getBloodPressure()));
        note.setText(toStart.getNote());
        temperature.setText(Integer.toString(toStart.getTemperature()));
    }

}