package myhealth.myhealth;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class sceneHandler {
    private Scene primaryScene;
    private Stage primaryStage;
    private Parent root;
    private User currentUsername;

    static databaseConnector db = new databaseConnector();

    User getUser(){
        return this.currentUsername;
    }

    void setUser(User currentUsername){
        this.currentUsername = currentUsername;
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

}
