import java.awt.*;
import javax.swing.*;
public class JFrameTest {
    public static void main(String[] args){
        JFrame mywin=new JFrame("The First Window");//创建特定标题的窗口
        mywin.setSize(300,200); //设置窗口的大小
        mywin.setLocation(50,50);//设置窗口的位置
        JLabel mylabel=new JLabel("This is a label");
        mywin.getContentPane().add(mylabel);
        mywin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mywin.setVisible(true);
    }
}
    