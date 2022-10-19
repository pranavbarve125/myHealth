package myhealth.myhealth;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class myHealth extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene login = new Scene(root);
        stage.setScene(login);
        stage.show();
    }

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (java.lang.ClassNotFoundException e) {
            System.out.println("No class found.");
        }
        launch();
    }
}