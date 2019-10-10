import java.applet.*;
import java.awt.*;
import java.awt.event.*;
/* public class FrameToApplet extends Frame
{
   Panel p1;
   Button b1,b2,b3;
   mycanvas mc;
   public FrameToApplet()
   {
      super("应用程序");
      mc=new mycanvas();
      b1=new Button("SIN");
      b2=new Button("COS");
      b3=new Button("SQRT");
      p1=new Panel();
      p1.add(b1);p1.add(b2);p1.add(b3);
      myListener ml=new myListener();
      b1.addActionListener(ml);
      b2.addActionListener(ml);
      b3.addActionListener(ml);
      setLayout(new BorderLayout());
      add(p1,BorderLayout.NORTH);
      add(mc,BorderLayout.CENTER);
   }
   private class myListener implements ActionListener
   {
       public void actionPerformed(ActionEvent e)
       {
          if(e.getSource()==b1)
          {
              mc.setFlag(1);
              mc.repaint();
          }else if(e.getSource()==b2)
          {
              mc.setFlag(2);
              mc.repaint();
          }else{
              mc.setFlag(3);
              mc.repaint();
          }
       }
   }
   public static void main(String[] args)
   {
      FrameToApplet myapp=new FrameToApplet();
      myapp.setSize(400,300);
      myapp.setVisible(true);
      myapp.addWindowListener(new WindowDestroyer());
   }
}
*/
public class FrameToApplet extends Applet  //父类换为Applet
{
   Panel p1;
   Button b1,b2,b3;
   mycanvas mc;
   public void init()  //构造方法换为init()
   {
      mc=new mycanvas();
      b1=new Button("SIN");
      b2=new Button("COS");
      b3=new Button("SQRT");
      p1=new Panel();
      p1.add(b1);p1.add(b2);p1.add(b3);
      myListener ml=new myListener();
      b1.addActionListener(ml);
      b2.addActionListener(ml);
      b3.addActionListener(ml);
      setLayout(new BorderLayout());
      add(p1,BorderLayout.NORTH);
      add(mc,BorderLayout.CENTER);
   }
   private class myListener implements ActionListener
   {
       public void actionPerformed(ActionEvent e)
       {
          if(e.getSource()==b1)
          {
              mc.setFlag(1);
              mc.repaint();
          }else if(e.getSource()==b2)
          {
              mc.setFlag(2);
              mc.repaint();
          }else{
              mc.setFlag(3);
              mc.repaint();
          }
       }
   }
   public static void main(String[] args)
   {
      Frame myapp=new Frame("应用程序");  //创建一个框架程序
      FrameToApplet myapplet=new FrameToApplet();
      myapp.add(myapplet);  //添加小应用程序
      myapplet.init();     //初始化
      myapplet.start();    //启动
      myapp.setSize(400,300);
      myapp.setVisible(true);
      myapp.addWindowListener(new WindowDestroyer());
   }
}