import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class mycanvas extends JPanel  {
  private int flag;
  private int x,y;
  mycanvas(){flag=0;}
  public int getFlag(){return flag;}
  public void setFlag(int i){flag=i;}
  public void paintComponent(Graphics g){
     super.paintComponent(g);
     g.drawLine(1,1,400,1);
     g.drawLine(1,1,1,220);
     g.drawLine(1,110,400,110);
     switch(flag) {
        case 1:
          for(int i=0;i<360;i++) {
            x=i;
            y=110-(int)(100*Math.sin(x*3.1415926/180.0));
            g.drawLine(x,y,x,y);
          }
          break;
       case 2:
          for(int i=0;i<360;i++)  {
            x=i;
            y=110-(int)(100*Math.cos(x*3.1415926/180.0));
            g.drawLine(x,y,x,y);
          }
          break;
       case 3:
          for(int i=0;i<360;i++) {
            x=i;
            y=110-(int)(4*Math.sqrt(x));
            g.drawLine(x,y,x,y);
          }
          break;
       default:break;
     }
  }
}
public class MyActionEventTest extends JFrame {
   JPanel p1;
   JButton b1,b2,b3; 
   mycanvas mc;
   public MyActionEventTest() {
      setTitle("画图演示");
      mc=new mycanvas();
      b1=new JButton("SIN");
      b2=new JButton("COS");
      b3=new JButton("SQRT");
      p1=new JPanel();
      p1.add(b1);p1.add(b2);p1.add(b3);
      myListener ml=new myListener();
      b1.addActionListener(ml);
      b2.addActionListener(ml);
      b3.addActionListener(ml);
      getContentPane().setLayout(new BorderLayout());
      getContentPane().add(p1,BorderLayout.NORTH);
      getContentPane().add(mc,BorderLayout.CENTER);
   }
   private class myListener implements ActionListener {
       public void actionPerformed(ActionEvent e)  {
          if(e.getSource()==b1)  {
              mc.setFlag(1);
              mc.repaint();
          }else if(e.getSource()==b2) {
              mc.setFlag(2);
              mc.repaint();
          }else{
              mc.setFlag(3);
              mc.repaint();
          }
       }
   }
   public static void main(String[] args){
        JFrame.setDefaultLookAndFeelDecorated(true);
        MyActionEventTest myapp=new MyActionEventTest();
        myapp.setSize(400,300);
        myapp.setVisible(true); 
        myapp.addWindowListener(new WindowDestroyer());      
   }
}