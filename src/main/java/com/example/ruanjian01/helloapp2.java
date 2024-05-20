package com.example.ruanjian01;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import zujian.Main;
import zujian.myJigsaw;


public class helloapp2 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("helloapp.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        new myJigsaw().start(stage);
        stage.show();

    }

    public static void start() {
        launch();
    }
}