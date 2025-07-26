module com.mycompany.kasir {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.mycompany.kasir to javafx.fxml;
    exports com.mycompany.kasir;
}
