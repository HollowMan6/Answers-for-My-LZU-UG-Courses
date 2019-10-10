import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
class WindowDestroyer extends WindowAdapter
{
    public void windowClosing(WindowEvent e) 
    {
        System.exit(0);
    }
}
public class EventDemo {
   public static void main(String[] args) {
      JFrame.setDefaultLookAndFeelDecorated(true);
      JFrame myapp=new JFrame("请单击关闭按钮");
      myapp.setSize(200,200);
      myapp.setVisible(true);
      WindowDestroyer closer=new WindowDestroyer();
      myapp.addWindowListener(closer);
   }
}