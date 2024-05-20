package com.example.ruanjian01;

import com.example.ruanjian01.ExerciseTest.score;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class Main extends Application {
    int ii=0;
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("成绩分析");
        stage.setWidth(500);
        stage.setHeight(500);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("right", Integer.parseInt(score.a)),
                        new PieChart.Data("wrong", Integer.parseInt(score.b)));
                     //   new PieChart.Data("Plums", 10),
                    //    new PieChart.Data("Pears", 22),
                     //   new PieChart.Data("Apples", 30));
        double a=Integer.parseInt(score.a);
        double b=Integer.parseInt(score.b);
        double[] sc=new double[2];
        sc[0]=b;
        sc[1]=a;
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("score");
        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : chart.getData()) {
            ii=0;
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                        @Override public void handle(MouseEvent e) {
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY());
                            caption.setText(String.valueOf(sc[ii++]/(a+b)*100)
                                    + "%");
                        }
                    });

        }

        ((Group) scene.getRoot()).getChildren().addAll(chart, caption);
        stage.setScene(scene);
        //scene.getStylesheets().add("piechartsample/Chart.css");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}