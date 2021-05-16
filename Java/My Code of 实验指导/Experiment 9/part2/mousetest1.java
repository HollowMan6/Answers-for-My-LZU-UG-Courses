import java.awt.*;
import java.awt.event.*;
class MPanel  extends Panel {
      int[] x,y;
      int count;
      public MPanel(){
         x=new int[50];y=new int[50];count=0;
         MyMouseAdapter mAction =new MyMouseAdapter();
         this.addMouseListener(mAction);
      }
      public void paint(Graphics g){
         for(int i=1;i<=count;i++)
            g.fillOval(x[i],y[i],5,5);
      }
      private class MyMouseAdapter extends MouseAdapter{
         public void mouseClicked(MouseEvent m){
            count++;
            x[count]=m.getX();
            y[count]=m.getY();
            repaint();
         }
      }
}
class mousetest1{
   public static void main(String[] args) {
      Frame myframe=new Frame("鼠标测试");
      MPanel mp=new MPanel();
      myframe.add(mp);
      myframe.setSize(400,400);
      myframe.setVisible(true);
      myframe.addWindowListener(new WindowCloser());
   }
}