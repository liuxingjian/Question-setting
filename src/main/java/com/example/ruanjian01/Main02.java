package com.example.ruanjian01;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.util.Duration;

public class Main02 extends Application {
    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        Pane pane = new Pane();  // 创建一个面板

        // 画一个圆，并将圆的圆心和半径和面板大小进行绑定，确保圆永远显示在中间，设置边缘为黑色，内部为白色
        Circle circle = new Circle();
        circle.centerXProperty().bind(pane.widthProperty().divide(2));
        circle.centerYProperty().bind(pane.heightProperty().divide(2));
        circle.radiusProperty().bind(pane.widthProperty().divide(3));
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);

        // 画第一片扇形，同样的方式固定圆心，根据圆的半径来改变扇形半径，使其与圆的边沿存在间隙
        Arc arc1 = new Arc();
        arc1.centerXProperty().bind(circle.centerXProperty());
        arc1.centerYProperty().bind(circle.centerYProperty());
        arc1.radiusXProperty().bind(circle.radiusProperty().divide(1.02));
        arc1.radiusYProperty().bind(circle.radiusProperty().divide(1.02));
        arc1.setStartAngle(30);  // 定义弧的起始角度
        arc1.setLength(60);  // 弧的角度范围
        arc1.setFill(Color.RED);
        arc1.setType(ArcType.ROUND);  // 弧的闭合类型['ArcType.OPEN', 'ArcType.CHORD']

        Arc arc2 = new Arc();
        arc2.centerXProperty().bind(circle.centerXProperty());
        arc2.centerYProperty().bind(circle.centerYProperty());
        arc2.radiusXProperty().bind(circle.radiusProperty().divide(1.02));
        arc2.radiusYProperty().bind(circle.radiusProperty().divide(1.02));
        arc2.setStartAngle(30+90);
        arc2.setLength(60);
        arc2.setFill(Color.RED);
        arc2.setType(ArcType.ROUND);

        Arc arc3 = new Arc();
        arc3.centerXProperty().bind(circle.centerXProperty());
        arc3.centerYProperty().bind(circle.centerYProperty());
        arc3.radiusXProperty().bind(circle.radiusProperty().divide(1.02));
        arc3.radiusYProperty().bind(circle.radiusProperty().divide(1.02));
        arc3.setStartAngle(30+180);
        arc3.setLength(60);
        arc3.setFill(Color.RED);
        arc3.setType(ArcType.ROUND);

        Arc arc4 = new Arc();
        arc4.centerXProperty().bind(circle.centerXProperty());
        arc4.centerYProperty().bind(circle.centerYProperty());
        arc4.radiusXProperty().bind(circle.radiusProperty().divide(1.02));
        arc4.radiusYProperty().bind(circle.radiusProperty().divide(1.02));
        arc4.setStartAngle(30+270);
        arc4.setLength(60);
        arc4.setFill(Color.RED);
        arc4.setType(ArcType.ROUND);

        // 将以上所有的结点加入面板当中
        pane.getChildren().addAll(circle, arc1, arc2, arc3, arc4);

        // 创建一个旋转的过渡动画，构建方法中，第一个参数指定完成一次旋转所需要的时间，第二个参数是旋转的对象
        RotateTransition rt = new RotateTransition(Duration.seconds(1), pane);
        rt.setByAngle(360);  // 设置旋转的角度
        rt.setCycleCount(Animation.INDEFINITE);  // 设置旋转次数，我们需要旋转无数次
        rt.setInterpolator(Interpolator.LINEAR);  // 控制每个过渡周期的加速和减速时间，设置为匀速
        rt.play();  // 开始播放动画

        // 将面板加入屏幕，并设置屏幕尺寸和标题
        Scene screen = new Scene(pane, 1000, 1000);
        primaryStage.setTitle("byack");
        primaryStage.setScene(screen);
        primaryStage.show();
    }
}

