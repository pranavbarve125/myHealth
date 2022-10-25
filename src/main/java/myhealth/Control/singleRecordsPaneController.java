package myhealth.Control;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class singleRecordsPaneController {

    @FXML
    private Button deletebutton;

    @FXML
    private Button editbutton;

    @FXML
    private Label recordlabel;

    @FXML
    private HBox singleRecord;

    public void setup(int id, String text, EventHandler onDelete, EventHandler onEdit, EventHandler onView){
        deletebutton.setId(Integer.toString(id));
        editbutton.setId(Integer.toString(id));
        recordlabel.setId(Integer.toString(id));
        recordlabel.setText(text);
        deletebutton.setOnMouseClicked(onDelete);
        editbutton.setOnMouseClicked(onEdit);
        recordlabel.setOnMouseClicked(onView);
    }
}
