package myhealth.Control;


import Model.Record;
import javafx.event.ActionEvent;
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
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class recordsController extends sceneHandler implements Initializable{

    @FXML
    private Button deleteB;

    @FXML
    private Button editB, saveButton;

    @FXML
    private ScrollPane scrollBox;

    @FXML
    private VBox recordsPane;

    @FXML
    private Label usernameDisplay;

    private ArrayList<Record> records;
    private String recordText;
//    private FileChooser fileSaver;
//    private File fileToSave;
//    private FileWriter wr;

    private void updateScene(){
        singleRecordsPaneController singleRecordObject;
        recordsPane.getChildren().clear();
        try{
            records = db.getRecords();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        try {
            FXMLLoader fxmlLoadObject = new FXMLLoader();
            if (records.isEmpty()) {
                fxmlLoadObject.setLocation(getClass().getResource("emptyHBox.fxml"));
                recordsPane.getChildren().add(fxmlLoadObject.load());
            } else {
                for (int n = 0; n < records.size(); n++) {
                    fxmlLoadObject.setLocation(getClass().getResource("singleRecordHbox.fxml"));
                    HBox singleRecord = null;
                    singleRecord = fxmlLoadObject.load();
                    singleRecordObject = fxmlLoadObject.getController();
                    recordText = String.format("Weight : %d\nTemperature : %d\nBlood Pressure : %d\nNote : %s", records.get(n).getWeight(), records.get(n).getTemperature(), records.get(n).getBloodPressure(), records.get(n).getNote());
                    singleRecordObject.setup(records.get(n).getRecordID(), recordText, onDelete, onEdit, onView);
                    recordsPane.getChildren().add(singleRecord);
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initializing file variable to null
//        fileToSave = null;

        usernameDisplay.setText("Welcome " + super.getUser().getFirstName() + " " + super.getUser().getLastName() + "!");
        usernameDisplay.setAlignment(Pos.CENTER);
        updateScene();
    }

    public void createRecord(ActionEvent event){
        try {
            super.setCreateFlag(true);
            super.sceneSwitcher(event, "editRecord.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logOut(ActionEvent event){
        try {
            super.sceneSwitcher(event, "login.fxml");
            super.setUser(null); // logging out
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editProfile(ActionEvent event){
        try {
            super.sceneSwitcher(event, "editProfile.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRecordString(Record toSave) {
        return "Record ID : " + toSave.getRecordID() +
                "\nWeight : " + toSave.getWeight() +
                "\nTemperature : " + toSave.getTemperature() +
                "\nBlood Pressure : " + toSave.getBloodPressure() +
                "\nNote : " + toSave.getNote() + "\n\n\n";
    }

//    public void saveRecordsToFile(ActionEvent event) throws IOException {
//        fileSaver = new FileChooser();
//        fileSaver.setTitle("Save Records.");
//        fileSaver.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
//        fileToSave = fileSaver.showSaveDialog(((Node) event.getTarget()).getScene().getWindow()); //passing the current window
//
//        if (fileToSave != null) {
//            wr = new FileWriter(fileToSave);
//            records.forEach((r) -> {
//                try {
//                    wr.write(getRecordString(r));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//            wr.close();
//        }
//    }

    EventHandler onDelete = new EventHandler() {
        @Override
        public void handle(Event event){
            int recordID = Integer.parseInt(((Node)event.getSource()).getId());
            boolean flag = db.deleteRecord(recordID);
            if (flag){
                recordsController.super.confirmationAlertMsg("Successful.", "Record is deleted.");
                updateScene();
            }
            else{
                recordsController.super.confirmationAlertMsg("Unsuccessful.", "Record could not be deleted.");
            }
        }
    };

    EventHandler onEdit = new EventHandler() {
        @Override
        public void handle(Event event){
            int recordID = Integer.parseInt(((Node)event.getSource()).getId());
            try {
                recordsController.super.setRecord(db.getSingleRecord(recordID));
                recordsController.super.setCreateFlag(false);
                recordsController.super.switchToSingleRecord((MouseEvent) event, "editRecord.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    EventHandler onView = new EventHandler() {
        @Override
        public void handle(Event event){
            int recordID = Integer.parseInt(((Node)event.getSource()).getId());
            try {
                recordsController.super.setRecord(db.getSingleRecord(recordID));
                recordsController.super.switchToSingleRecord((MouseEvent)event, "viewRecord.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
