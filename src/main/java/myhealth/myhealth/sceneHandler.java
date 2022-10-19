package myhealth.myhealth;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class sceneHandler {
    private Scene primaryScene;
    private Stage primaryStage;
    private Parent root;

    databaseConnector db = new databaseConnector();

    void sceneSwitcher(ActionEvent event, String filename) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(filename));
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryScene = new Scene(root);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

}
