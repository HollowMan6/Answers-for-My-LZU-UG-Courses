import java.awt.*;
public class ManualLayout{
  public static void main(String args[]){
    Frame f=new Frame("MenualLayout");
    f.setLayout(null);   //取消布局管理器
    f.setSize(300,100);   //设置框架大小
    Button b=new Button("测试按钮");
    b.setSize(100,30); //设置按钮大小
    b.setLocation(40,60);  //设置放置坐标(40,60)
    f.add(b);
    f.setVisible(true);
  }
}