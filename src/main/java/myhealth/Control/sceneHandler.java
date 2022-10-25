package myhealth.Control;

import Model.Record;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class sceneHandler {
    private Scene primaryScene;
    private Stage primaryStage;
    private Parent root;
    private static User currentUsername;
    private static Record currentRecord;
    private static boolean createFlag = true;

    protected static databaseConnector db = new databaseConnector();

    boolean getCreateFlag(){
        return createFlag;
    }

    void setCreateFlag(boolean argumentValue){
        createFlag = argumentValue;
    }

    User getUser() {
        return currentUsername;
    }

    String getUsername() {
        return currentUsername.getUsername();
    }

    Record getRecord(){
        return currentRecord;
    }

    void setUser(User currentUsernameArgument) {
        currentUsername = currentUsernameArgument;
        System.out.println(currentUsername);
    }

    void setRecord(Record currentRecordArgument) {
        currentRecord = currentRecordArgument;
    }

    void ErrorAlertMsg(String alertTitle, String alertMsg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(alertTitle);
        alert.setContentText(alertMsg);
        alert.showAndWait();
    }

    void warningAlertMsg(String alertTitle, String alertMsg){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(alertTitle);
        alert.setContentText(alertMsg);
        alert.showAndWait();
    }

    void confirmationAlertMsg(String alertTitle, String alertMsg){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(alertTitle);
        alert.setContentText(alertMsg);
        alert.showAndWait();
    }

    void sceneSwitcher(ActionEvent event, String filename) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(filename)));
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryScene = new Scene(root);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    void switchToSingleRecord(MouseEvent event, String filename) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(filename)));
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryScene = new Scene(root);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    String hashPassword(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger bigInt = new BigInteger(1, messageDigest);
        return bigInt.toString(16);
    }
}