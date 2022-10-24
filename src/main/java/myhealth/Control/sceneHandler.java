package myhealth.Control;

import Model.Record;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Objects;

public class sceneHandler {
    private Scene primaryScene;
    private Stage primaryStage;
    private Parent root;
    private static User currentUsername;

    protected static databaseConnector db = new databaseConnector();

    User getUser(){
        return currentUsername;
    }
    String getUsername(){ return currentUsername.getUsername(); }

    void setUser(User currentUsernameArgument){
        currentUsername = currentUsernameArgument;
        System.out.println(currentUsername);
    }

    void triggerAlertMsg(String alertTitle, String alertMsg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(alertTitle);
        alert.setContentText(alertMsg);
        alert.showAndWait();
    }

    void sceneSwitcher(ActionEvent event, String filename) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(filename));
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryScene = new Scene(root);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    void switchToSingleRecord(MouseEvent event, Record toSetup, String buttonText)  throws IOException{
        FXMLLoader fxmlLoadObject = new FXMLLoader();
        fxmlLoadObject.setLocation(getClass().getResource("editRecord.fxml"));
        fxmlLoadObject.load();
        editRecordController obj = fxmlLoadObject.getController();
        obj.setupPane(toSetup);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("editRecord.fxml")));
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryScene = new Scene(root);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

}
