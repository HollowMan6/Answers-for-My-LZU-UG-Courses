import java.awt.*;
public class FlowLayoutDemo{
  public static void main(String[] args){
     Frame myapp=new Frame("FlowLayoutDemo");
     FlowLayout fl=new FlowLayout(FlowLayout.RIGHT);  //默认居中
     myapp.setLayout(fl);   //采用FlowLayout布局
     Button b1=new Button("Left");
     Button b2=new Button("Center");
     Button b3=new Button("Right");
     myapp.add(b1);myapp.add(b2);myapp.add(b3);
     myapp.setSize(300,100);
     myapp.setVisible(true);
  }
}