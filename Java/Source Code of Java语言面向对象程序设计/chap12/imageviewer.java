import java.io.*;
import java.awt.*;
import java.awt.event.*;
class myviewer1 extends Canvas
{
   private Image img[];
   private int current=0;
   private int counts=0;
   public myviewer1()
   {
      File mpath=new File(".");
      String [] filenames=mpath.list();
      for(int i=0;i<filenames.length;i++)
      {
         if(filenames[i].endsWith(".jpg")||filenames[i].endsWith(".gif")) counts++;
         else  filenames[i]=null;
      }
      img=new Image[counts];
      Toolkit  mytool=Toolkit.getDefaultToolkit();
      
      for(int i=0,j=0;i<filenames.length;i++)
      {
        if(filenames[i]!=null) 
        {
          img[j]=mytool.getImage(filenames[i]);
          j++;
        } 
      }
   }
   public void paint(Graphics g)
   {
      g.drawImage(img[current],0,0,getSize().width,getSize().height,this);
   }
   public int getCurrent(){return current;}  
   public void setCurrent(int a)
   {
      if(a>=0 && a<counts) current=a;
      else if(a<0) current=0;
      else current=counts-1;
   }
}
public class imageviewer extends Frame
{
   Button bt1,bt2;
   myviewer1 mv;
   public imageviewer()
   {
      super("图像观查器 ver 1.1");
      bt1=new Button("next");
      bt2=new Button("prev");
      mv=new myviewer1();
      Panel p1=new Panel();
      p1.add(bt1);p1.add(bt2);
      add(p1,BorderLayout.SOUTH);
      add(mv,BorderLayout.CENTER);

      Mylistener ls=new Mylistener();
      bt1.addActionListener(ls);
      bt2.addActionListener(ls);
     
   }
   private class Mylistener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(e.getSource()==bt1)
         {
            mv.setCurrent(mv.getCurrent()+1);
            mv.repaint();
         }
         else
         {
            mv.setCurrent(mv.getCurrent()-1);
            mv.repaint();
         }
      } 
   }
   public static void main(String[] args)
   {
      imageviewer myapp=new imageviewer();
      myapp.setSize(800,600);
      myapp.setVisible(true);
      myapp.addWindowListener(new WindowDestroyer());
   }
}