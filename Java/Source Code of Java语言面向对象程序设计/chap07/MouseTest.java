import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class MousePanel  extends JPanel {
      int x,y;
      int mx,my;
      String msg=null;
      boolean isRight=false;
      public MousePanel()   {
            MyMouseAdapter mAction = new MyMouseAdapter();
            MyMouseAdapter1 mac=new MyMouseAdapter1();
            this.addMouseListener(mAction);
            this.addMouseMotionListener(mac);
            msg="I love you!";
      }
      public void paintComponent(Graphics g)  {
           super.paintComponent(g);
           if(!isRight)
              g.fillOval(x,y,10,10);
           else 
              g.drawRect(x,y,10,10);
              g.drawString(msg,mx,my);           
      }
     private class MyMouseAdapter extends MouseAdapter   {
            public void mouseClicked(MouseEvent m) {
                x=m.getX();
                y=m.getY();
                if(m.getClickCount()==2) { 
                  if(msg.equals("I love you!"))
                     msg="I hate you!!!";
                  else
                     msg="I love you!";
                }
                if(m.getButton()==m.BUTTON3)
                   isRight=true;
                else
                   isRight=false;
                repaint();
            }
      }
     private class MyMouseAdapter1 extends MouseMotionAdapter  {
            public void mouseMoved(MouseEvent m)  {
                mx=m.getX();
                my=m.getY();
                repaint();
            }
      }
}
public class MouseTest {
       public static void main(String args[])  {
               JFrame myframe=new JFrame("鼠标测试");
               MousePanel mp=new MousePanel();
               myframe.add(mp);
               myframe.setSize(400,400);
               myframe.setVisible(true);
               myframe.addWindowListener(new WindowDestroyer());
       }
}