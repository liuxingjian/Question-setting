module com.example.ruanjian01 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.ruanjian01 to javafx.fxml;
    exports com.example.ruanjian01;
}