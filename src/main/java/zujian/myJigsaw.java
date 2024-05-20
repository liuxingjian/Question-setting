package zujian;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;

public class myJigsaw extends Application {
    public int m;          //m是不在随机数组的那个数字
    ImageView[] imageViews = new ImageView[9];
    public static void main(String[] args) {
        Application.launch(args);
    }


    @Override
    public void start(Stage arg0) throws Exception {
        init(arg0);
    }
    public void init(Stage stage) {
        int[] n = random();              //自定义的函数，产生逆序数为偶数的不重复数组

        Image image = new Image("D:\\workplace\\myLanguage\\myJava\\ruanjian01\\src\\main\\java\\com\\example\\ruanjian01\\3.png");


        GridPane gridPane = new GridPane();

        for(int i = 0, k = 0; i <= 2; ++i) {
            for(int j = 0; j <= 2; ++j, ++k) {
                imageViews[k] = new ImageView(image);        //初始化数组
                imageViews[k].setOnMouseClicked(new myevent());      //设置点击事件
                imageViews[k].setViewport(new Rectangle2D(100 * j, 100 * i, 100, 100));             //切割图片
            }
        }

        gridPane.add(imageViews[n[0]], 0, 0);                         //按照产生的随机数将imageView数组加入面板
        gridPane.add(imageViews[n[1]], 1, 0);
        gridPane.add(imageViews[n[2]], 2, 0);
        gridPane.add(imageViews[n[3]], 0, 1);
        gridPane.add(imageViews[n[4]], 1, 1);
        gridPane.add(imageViews[n[5]], 2, 1);
        gridPane.add(imageViews[n[6]], 0, 2);
        gridPane.add(imageViews[n[7]], 1, 2);
        m = findnum(n);                                                 //找出那个不在随机数组里面的数字
        ImageView incomp = new ImageView(imageViews[m].getImage());              //用于显示空格子的图片
        ImageView comp = new ImageView(image);                                     //用于显示完整的大图
        incomp.setViewport(imageViews[m].getViewport());
        Image image2 = new Image("D:\\workplace\\myLanguage\\myJava\\ruanjian01\\src\\main\\java\\com\\example\\ruanjian01\\2.png");                                 //2.png为一个透明图，放在空格子中
        imageViews[m].setImage(image2);
        gridPane.add(imageViews[m], 2, 2);
        gridPane.setGridLinesVisible(true);
        BorderPane borderPane = new BorderPane(gridPane);
        VBox right = new VBox(20, incomp, comp);
        borderPane.setRight(right);
        Scene scene = new Scene(borderPane, 820, 420);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public int[] random() {             //生成8个不重复的逆序数为偶数的数字
        int[] ran = new int[8];
        while(iso(ran) == false) {
            ran = random_num();
        }
        return ran;

    }

    public int[] random_num() {      //生成8个不重复数
        int r[] = new int[8];
        Random random = new Random();
        for(int i = 0; i < 8; ++i) {
            r[i] = random.nextInt(9);
            for(int j = 0;j < i; ++j) {
                while(r[i] == r[j]) {
                    i--;
                    break;
                }
            }
        }
        return r;
    }

    public boolean iso(int[] num) {          //判断逆序数是否为偶数
        int sum = 0;
        for(int i = 0; i <= 6; ++i) {
            for(int j = i; j <= 7; j++) {
                if(num[i] > num[j]) {
                    sum++;
                }
            }
        }
        if((sum % 2) == 0 && sum != 0) {
            return true;
        }

        return false;

    }

    class myevent implements EventHandler<MouseEvent> {               //点击事件的实现


        @Override
        public void handle(MouseEvent arg0) {
            // TODO Auto-generated method stub
            ImageView img = (ImageView) arg0.getSource();
            double sx = img.getLayoutX();
            double sy = img.getLayoutY();
            double dispx = sx - imageViews[m].getLayoutX();
            double dispy = sy - imageViews[m].getLayoutY();
            if((dispx == -100) && (dispy == 0 )) {               //点击的空格左边的格子
                swapimg(img, imageViews[m]);             //交换imageView
                if(issucc(imageViews)) {                          //判断是否拼成功
                    Alert alert = new Alert(AlertType.WARNING, "成功！");
                    alert.show();
                }
            }

            else if ((dispx == 0) && (dispy == -100)) {        //上面的格子
                swapimg(img, imageViews[m]);
                if(issucc(imageViews)) {
                    Alert alert = new Alert(AlertType.WARNING, "成功！");
                    alert.show();
                }
            }
            else if((dispx == 100) && (dispy == 0)) {               //右边的格子
                swapimg(img, imageViews[m]);
                if(issucc(imageViews)) {
                    Alert alert = new Alert(AlertType.WARNING, "成功！");
                    alert.show();
                }
            }
            else if((dispx == 0) && (dispy == 100)) {                //下面的格子
                swapimg(img, imageViews[m]);
                if(issucc(imageViews)) {
                    Alert alert = new Alert(AlertType.WARNING, "成功！");
                    alert.show();
                }
            }
        }
        public void swapimg(ImageView i1, ImageView i2) {              //交换两个imageView的实现
            int row1 = GridPane.getRowIndex(i1);
            int colu1 = GridPane.getColumnIndex(i1);
            int row2 = GridPane.getRowIndex(i2);
            int colu2 = GridPane.getColumnIndex(i2);

            GridPane.setRowIndex(i1, row2);
            GridPane.setColumnIndex(i1, colu2);
            GridPane.setRowIndex(i2, row1);
            GridPane.setColumnIndex(i2, colu1);
        }
    }
    public boolean issucc(ImageView[] imageViews) {                                //判断是否拼成功
        for(int i = 0; i <= 8; ++i) {
            if(i != 3 * GridPane.getRowIndex(imageViews[i]) + GridPane.getColumnIndex(imageViews[i])) {
                return false;
            }
        }
        return true;
    }

    public int findnum(int[] n) {                                             //找出m
        for(int j = 0; j <= 8; ++j) {
            if((j == n[0]) || (j == n[1]) || (j == n[2]) || (j == n[3]) || (j == n[4]) || (j == n[5]) || (j == n[6]) || (j == n[7])) {
            }
            else {
                return j;
            }
        }
        return -1;
    }

}