package myhealth.Control;

import Model.Record;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class editRecordController extends sceneHandler implements Initializable {

    @FXML
    private TextField bp;

    @FXML
    private Button createButton, backButton;

    @FXML
    private BorderPane borderPane;

    @FXML
    private TextArea note;

    @FXML
    private TextField temperature;

    @FXML
    private Label usernameDisplay;

    @FXML
    private TextField weight;
    Record toInsert, toUpdate;
    int recordToEditID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameDisplay.setText("Welcome " + super.getUser().getFirstName() + "!");
        usernameDisplay.setAlignment(Pos.CENTER);
        if (!super.getCreateFlag()){
            setupPane();
        }
    }
    public void back(ActionEvent event){
        try {
            super.sceneSwitcher(event, "records.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createOrEditRecord(ActionEvent event) throws SQLException, IOException {

        //checking for empty boxes
        if (weight.getText().isEmpty() && temperature.getText().isEmpty() && bp.getText().isEmpty() && note.getText().isEmpty()){
            super.ErrorAlertMsg("Empty Fields", "Enter information in atleast on  box.");
        }

        //Checking the 50 word limit
        String[] numberOfWords = note.getText().split("\\s+");

        if(numberOfWords.length > 50){
            super.ErrorAlertMsg("Note limit exceeded.", "Note cannot be more than 50 characters");
        }
        try {
            if (super.getCreateFlag()) {
                if (db.insertRecord(new Record(Integer.parseInt(weight.getText()), Integer.parseInt(temperature.getText()), Integer.parseInt(bp.getText()), note.getText()))) {
                    super.confirmationAlertMsg("Successful", "Record has been successfully added.");
                    super.sceneSwitcher(event, "records.fxml");
                } else {
                    super.warningAlertMsg("Unsuccessful", "Record was not added properly.");
                }
            } else {
                if (db.updateRecord(recordToEditID, new Record(Integer.parseInt(weight.getText()), Integer.parseInt(temperature.getText()), Integer.parseInt(bp.getText()), note.getText()))) {
                    super.confirmationAlertMsg("Successful", "Record has been successfully updated.");
                    super.sceneSwitcher(event, "records.fxml");
                } else {
                    super.warningAlertMsg("Unsuccessful", "Record was not updated properly.");
                }
            }
        }
        catch(NumberFormatException e){
            super.ErrorAlertMsg("Unsuccessful", "Please enter appropriate numeric values for weight, temperature and blood pressure.");
        }
    }



    void setupPane(){
        weight.setText(Integer.toString(super.getRecord().getWeight()));
        bp.setText(Integer.toString(super.getRecord().getBloodPressure()));
        note.setText(super.getRecord().getNote());
        temperature.setText(Integer.toString(super.getRecord().getTemperature()));
        if(super.getCreateFlag()){
            createButton.setText("Create Record");
        }
        else{
            createButton.setText("Edit Record");
            recordToEditID = super.getRecord().getRecordID(); // storing the Id for running the update query
        }

    }
}