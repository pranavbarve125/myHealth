module myhealth.myhealth {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens myhealth.myhealth to javafx.fxml;
    exports myhealth.myhealth;
}