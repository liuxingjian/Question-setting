package com.example.ruanjian01;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
public class DrawDraw extends JFrame implements ActionListener,MouseListener,MouseMotionListener{
    public static void main(String[] args) {
        new DrawDraw();
    }
    // 属性
    JPanel p0,p1,p2;
    Color color;
    String shape;
    int x1,y1,x2,y2,newx1,newy1,newx2,newy2;
    Graphics2D g;
    BufferedImage img;
    boolean flag;

    public DrawDraw(){

        p0 = new JPanel();
        p1 = new JPanel();
        p2 = new JPanel();

        setTitle("画画面板");
        this.setSize(1400,900);
        this.setLocation(100,100);

        // 图形按钮，采用数组的方式添加按钮。好处在更改代码的时候，可以直接添加，十分方便
        String [] Shape={"直线","曲线","圆","喷枪","橡皮擦","矩形","椭圆","圆角矩形","弧线","图形"};
        for(int i=0;i<Shape.length;i++){
            JButton button=new JButton(Shape[i]);
            button.addActionListener(this);    //添加事件监听机制  类(this)应该是有实现了ActionListener这个接口的吧;
            p0.add(button);
        }

        // 颜色按钮
        Color [] color={Color.BLACK,Color.blue,Color.white,Color.gray,Color.red,Color.CYAN,Color.green,Color.darkGray,Color.pink};
        for(int i=0;i<color.length;i++){
            JButton button=new JButton();
            button.addActionListener(this);     //添加事件监听机制
            button.setPreferredSize(new Dimension(40,40));  // 设置按钮的大小
            button.setBackground(color[i]);     // 设置颜色选择按钮的颜色
            p2.add(button);
        }

        // 设置背景颜色
        p0.setBackground(Color.gray);
        p1.setBackground(Color.white);
        p2.setBackground(Color.yellow);
        // 把p0,p1,p2 上-中-下的方法分配
        this.add(p0,BorderLayout.NORTH);
        this.add(p1,BorderLayout.CENTER);
        this.add(p2,BorderLayout.SOUTH);

      //  this.setDefaultCloseOperation();
        this.setVisible(true);

        // 注意：这里鼠标移动和鼠标拖动的事件，是作用在p1的面板上面。。。类(this)应该是有实现了MouseListener,MouseMotionListener
        p1.addMouseListener(this);
        p1.addMouseMotionListener(this);

    }

    // 当类实现接口的时候，类要实现接口中所有的方法。否则，类必须声明为抽象的类。对应ActionListener接口
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("")){      //如果没有信息，那就是颜色按钮
            JButton button = (JButton) e.getSource();  // getSource()事件最初发生的对象，
            color = button.getBackground();
            System.out.println("color = " + color);
        }else{
            JButton button = (JButton) e.getSource();
            shape = button.getActionCommand();
            System.out.println("String = " + shape);
        }
    }

    // 当类实现接口的时候，类要实现接口中所有的方法。否则，类必须声明为抽象的类。
    // 在组件上按下鼠标按钮时调用。
    public void mousePressed(MouseEvent e) {
        g=(Graphics2D)p1.getGraphics(); // g = p1.getGraphics();
        g.setColor(color);
        x1=e.getX();  // 返回事件相对于源组件的水平x位置。
        y1=e.getY();
        if(shape.equals("圆")){
            g.drawOval(x1, y1, 30, 30);
        }else if(shape.equals("矩形")){
            g.drawRect(x1, y1, 30, 40);
        }else if(shape.equals("圆角矩形")){
            g.drawRoundRect(x1, y1, 30, 40, 5, 10);
        }else if(shape.equals("椭圆")){
            g.drawOval(x1, y1, 30, 20);
        }else if(shape.equals("弧线")){
            g.drawArc(x1, y1, 100, 80, 10, 180);  //（x,y,宽，高，起始角度，结束角度）
        } // 如果想使用这个图形，下面的new File("这里要添加自己电脑上的图片路径")
		 /*else if (shape.equals("图形")){
		     try{
			     img=ImageIO.read(new File("F:\\学习知识\\Java\\画画面板\\imager\\太阳1.bmp"));
		     }
		     catch(IOException e1){
			     System.out.println(e.toString());
		     }
             // drawImage绘制当前可用的指定图像的大小。 该图像在其图形上下文的坐标空间中的左上角（ x ， y ,宽，高）处绘制。
		     g.drawImage(img,x1,y1,150,150,null);
		     }*/
        System.out.println("x1 = " + x1 +"   y1 = " + y1);
    }

    // 在组件上单击（按下并释放）鼠标按钮时调用。
    public void mouseClicked(MouseEvent e){
    }

    // 当鼠标进入组件时调用。
    public void mouseEntered(MouseEvent e){
    }

    // 当鼠标退出组件时调用。
    public void mouseExited(MouseEvent e){
    }

    // 松开。搭配前面的按下，就可以画出直线
    public void mouseReleased(MouseEvent e){
        g.setColor(color);
        if(shape.equals("直线")){
            x2 = e.getX();
            y2 = e.getY();
            g.drawLine(x1, y1, x2, y2);   //通过drawLine方法在两个点之间连一条直线(gr是画笔)
        }
    }

    // 在组件上按下鼠标按钮然后拖动时调用。
    public void mouseDragged(MouseEvent e){
        x2 = e.getX();
        y2 = e.getY();
        if (shape.equals("曲线")) {
            g.drawLine(x1, y1, x2, y2);
            x1 = x2;
            y1 = y2;
        }else if(shape.equals("橡皮擦")){
            // Graphics2D中的方法。BasicStroke(float width)--->指的是宽度
            g.setStroke(new BasicStroke(80));
            // 好像是渲染，应该就是给涂掉
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.WHITE);
            g.drawLine(x1, y1, x2, y2);
            x1 = x2;
            y1 = y2;
        }else if(shape.equals("喷枪")){
            for(int k=0;k<20;k++){
                Random i=new Random();
                int a=i.nextInt(8);
                int b=i.nextInt(10);
                g.drawLine(x2+a, y2+b, x2+a, y2+b);
            }
        }
    }

    // 当鼠标光标移动到组件上但没有按钮被按下时调用。（就是光标放到上面）
    public void mouseMoved(MouseEvent e) {
    }
}

