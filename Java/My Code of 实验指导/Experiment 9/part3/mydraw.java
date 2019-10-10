import java.awt.*;
import java.awt.event.*;
class mycanvas extends Canvas
{
  private int flag;
  private int x,y;
  mycanvas(){flag=0;}

  public int getFlag(){return flag;}
  public void setFlag(int i){flag=i;}

  public void paint(Graphics g)
  {
     g.drawLine(0,1,400,1);

     g.drawLine(1,1,1,220);
     g.drawLine(1,110,400,110);

     switch(flag)
     {
        case 1:
          for(int i=0;i<360;i++)
          {
            x=i;
            y=110-(int)(100*Math.sin(x*3.1415926/180.0));
            g.fillOval(x,y,3,3);
          }
          break;
       case 2:
          for(int i=0;i<360;i++)
          {
            x=i;
            y=110-(int)(100*Math.cos(x*3.1415926/180.0));
            g.fillOval(x,y,3,3);
          }
          break;
       case 3:
          for(int i=0;i<360;i++)
          {
            x=i;
            y=110-(int)(4*Math.sqrt(x));
            g.fillOval(x,y,3,3);
          }
          break;
       default:break;
     }
  }
}
public class mydraw extends Frame
{
   Panel p1;
   Button b1,b2,b3;
   mycanvas mc;
   public mydraw()
   {
      setTitle("画图演示");
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
      mydraw myapp=new mydraw();
      myapp.setSize(400,300);
      myapp.setVisible(true);
      myapp.addWindowListener(new WindowCloser());
   }
}
。



