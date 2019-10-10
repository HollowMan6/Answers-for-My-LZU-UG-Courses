import java.awt.*;
import javax.swing.*;
public class SimpleFrame{
  public static void main(String[] args){
     JFrame f=new JFrame("Hello GUI"); //创建Frame
     JPanel p=new JPanel();  //创建面板对象
     p.setBackground(Color.red);   //改变面板背景颜色
     f.add(p,BorderLayout.NORTH);  //将面板添加到Frame的北边
     f.setSize(200,200);  //设置Frame的大小
     f.setVisible(true);  //使Frame成为可见的
     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}