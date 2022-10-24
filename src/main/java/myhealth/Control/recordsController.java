package myhealth.Control;


import Model.Record;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class recordsController extends sceneHandler implements Initializable{

    @FXML
    private Button deleteB;

    @FXML
    private Button editB;

    @FXML
    private ScrollPane scrollBox;

    @FXML
    private VBox recordsPane;

    @FXML
    private Label usernameDisplay;

    private ArrayList<Record> records;
    private singleRecordsPaneController singleRecordObject;
    String recordText;

    private void updateScene(){
        try{
            records = db.getRecords();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        try {
            recordsPane.getChildren().clear();
            for(int n=0; n<records.size(); n++){
                FXMLLoader fxmlLoadObject = new FXMLLoader();
                fxmlLoadObject.setLocation(getClass().getResource("singleRecordHbox.fxml"));
                HBox singleRecord = fxmlLoadObject.load();
                singleRecordObject = fxmlLoadObject.getController();
                recordText = String.format("Weight : %d\nTemperature : %d\nBlood Pressure : %d\nNote : %s", records.get(n).getWeight(), records.get(n).getTemperature(), records.get(n).getBloodPressure(), records.get(n).getNote());
                singleRecordObject.setup(records.get(n).getRecordID(), recordText, onDelete, onEdit);
                recordsPane.getChildren().add(singleRecord);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameDisplay.setText("Welcome " + super.getUser().getFirstName() + "!");
        usernameDisplay.setAlignment(Pos.CENTER);
        updateScene();
    }

    EventHandler onDelete = new EventHandler() {
        @Override
        public void handle(Event event){
            int recordID = Integer.parseInt(((Node)event.getSource()).getId());
            boolean flag = db.deleteRecord(recordID);
            if (flag){
                recordsController.super.triggerAlertMsg("Successful.", "Record is deleted.");
                updateScene();
            }
            else{
                recordsController.super.triggerAlertMsg("Unsuccessful.", "Record could not be deleted.");
            }
        }
    };

    EventHandler onEdit = new EventHandler() {
        @Override
        public void handle(Event event){
            int recordID = Integer.parseInt(((Node)event.getSource()).getId());
            try {
                Record toSetup = db.getSingleRecord(recordID);
                System.out.println("Current Record is : " + toSetup.getNote());
                recordsController.super.switchToSingleRecord((MouseEvent) event, toSetup, "Edit Record");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
