package zujian;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * 计算器
 */
public class Main extends Application {
    private int number1;//存开始数字 0
    private int number2;//存结束数字 0
    private String logic;//存运算符的变量 null
    private TextField view;
    private boolean flag;//记录第一次点击运算符之后的拼接操作
    private boolean eq;//记录是否按了等号
    public void start(Stage primaryStage) {
        try {
            //创建布局对象
            AnchorPane root = new AnchorPane();
//			root.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
            //创建场景对象
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            //设置窗口宽高
            primaryStage.setWidth(228);
            primaryStage.setHeight(260);
            //设置不能改变大小
            primaryStage.setResizable(false);

            //调用方法添加组件
            addComp(root);

            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 该方法是添加组件
     * @param root
     */
    private void addComp(AnchorPane root) {
        //添加显示框
        view = new TextField("0");
        view.setMinSize(200, 50);
        view.setLayoutX(10);
        view.setLayoutY(15);
        //设置只读
        view.setEditable(false);
        //内容右对齐
        view.setAlignment(Pos.CENTER_RIGHT);
        //设置字体
        view.setFont(new Font("Consolas", 20));
        root.getChildren().add(view);

        //添加一个表格布局
        GridPane gridPane = new GridPane();
        //设置布局中元素组件横向间距
        gridPane.setHgap(20);
        //设置布局中元素组件纵向间距
        gridPane.setVgap(10);
        gridPane.setLayoutY(65);
        gridPane.setPrefWidth(228);
        gridPane.setPrefHeight(185);
//		gridPane.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        gridPane.setPadding(new Insets(10));
        //列容器
        root.getChildren().add(gridPane);

        //创建数字监听器对象
        NumberEvent numberEvent = new NumberEvent();
        //添加按钮
        Button btn_7 = new MyButton("7");
        btn_7.setOnMouseClicked(numberEvent);
        gridPane.add(btn_7,0,0);

        Button btn_8 = new MyButton("8");
        btn_8.setOnMouseClicked(numberEvent);
        gridPane.add(btn_8,1,0);

        Button btn_9 = new MyButton("9");
        btn_9.setOnMouseClicked(numberEvent);
        gridPane.add(btn_9,2,0);

        Button btn_4 = new MyButton("4");
        btn_4.setOnMouseClicked(numberEvent);
        gridPane.add(btn_4,0,1);

        Button btn_5 = new MyButton("5");
        btn_5.setOnMouseClicked(numberEvent);
        gridPane.add(btn_5,1,1);

        Button btn_6 = new MyButton("6");
        btn_6.setOnMouseClicked(numberEvent);
        gridPane.add(btn_6,2,1);

        Button btn_1 = new MyButton("1");
        btn_1.setOnMouseClicked(numberEvent);
        gridPane.add(btn_1,0,2);

        Button btn_2 = new MyButton("2");
        btn_2.setOnMouseClicked(numberEvent);
        gridPane.add(btn_2,1,2);

        Button btn_3 = new MyButton("3");
        btn_3.setOnMouseClicked(numberEvent);
        gridPane.add(btn_3,2,2);

        Button btn_0 = new MyButton("0");
        btn_0.setOnMouseClicked(numberEvent);
        gridPane.add(btn_0,0,3);

        Button btn_C = new MyButton("C");
        btn_C.setOnMouseClicked(new EventHandler<Event>() {
            public void handle(Event event) {
                clean();
            }
        });
        gridPane.add(btn_C,1,3);

        Button btn_eq = new MyButton("=");
        btn_eq.setOnMouseClicked(new EventHandler<Event>() {
            public void handle(Event event) {
                //记录运算结构
                switch (logic) {
                    case "+":
                        number1 = number1+number2;
                        break;
                    case "-":
                        number1 = number1-number2;
                        break;
                    case "*":
                        number1 = number1*number2;
                        break;
                    case "/":
                        number1 = number1/number2;
                        break;

                    default:
                        break;
                }
                //把结果显示回显示框
                view.setText(number1+"");
                //按了等号
                eq = true;
            }
        });
        gridPane.add(btn_eq,2,3);
        //运算符的按钮

        //创建监听器对象
        LogicEvent logicEvent = new LogicEvent();
        Button btn_add = new MyButton("+");
        //给按钮添加事件监听器
        btn_add.setOnMouseClicked(logicEvent);
        gridPane.add(btn_add,3,0);
        Button btn_sub = new MyButton("-");
        btn_sub.setOnMouseClicked(logicEvent);
        gridPane.add(btn_sub,3,1);
        Button btn_mul = new MyButton("*");
        btn_mul.setOnMouseClicked(logicEvent);
        gridPane.add(btn_mul,3,2);
        Button btn_div = new MyButton("/");
        btn_div.setOnMouseClicked(logicEvent);
        gridPane.add(btn_div,3,3);
    }

    public static void main(String[] args) {
        launch(args);
    }
    /**
     * 数字监听器
     */
    class NumberEvent implements EventHandler<Event>{
        public void handle(Event event) {
            //如果上一次是等号，得先清除一次
            if (eq) {
                clean();
            }

            /**
             *  ①判断运算符变量是否有值
             ②如果有值，将按钮的内容保存到2中的变量
             ③如果没有值，将按钮的内容保存到1中的变量
             */
            //获取当前显示框内容
            String text = view.getText();
            //获取事件源  (获取触发事件的按钮)
            MyButton btn = (MyButton)event.getSource();
            //先获取当前按钮的内容
            String btnNumber = btn.getText();
            //将显示框原本的数字拼接当前按钮的数字
            if (flag) {
                text = "0";
                flag = false;
            }
            text+=btnNumber;
            //将字符串转换为int类型
            int num = Integer.parseInt(text);
            //没有点击运算符按钮
            if (logic==null) {
                number1 = num;
                view.setText(number1+"");
                //点击了运算符按钮
            }else {
                number2 = num;
                view.setText(number2+"");
            }
        }
    }
    /**
     * 运算符的事件监听器
     */
    class LogicEvent implements EventHandler<Event>{

        public void handle(Event event) {
            //得到按钮
            MyButton logicBtn = (MyButton)event.getSource();
            //获取按钮内容
            logic = logicBtn.getText();
            flag = true;
            eq = false;
        }

    }
    /**
     * 清除计算器参与逻辑的变量
     */
    private void clean() {
        number1 = 0;
        number2 = 0;
        logic = null;
        flag = false;
        eq = false;
        //显示0
        view.setText("0");
    }
}