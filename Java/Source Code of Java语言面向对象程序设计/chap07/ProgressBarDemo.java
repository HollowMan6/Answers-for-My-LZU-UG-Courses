import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
class ProgressBarDemo extends JFrame 
{  
    JProgressBar pbar1;
    JTextField text1;
    ProgressBarDemo()
    {   
      pbar1=new JProgressBar(0,100);
      pbar1.setStringPainted(true);
      text1=new JTextField(10); 

      Container con=getContentPane();
      con.setLayout(new FlowLayout());
      con.add(pbar1);con.add(text1);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(10,10,300,100);
      setVisible(true); 
      validate();
    }
    public void run()
    {
            for(int i=1;i<=100;i++)
              {
                 text1.setText("第"+i+"项="+f(i));
                 pbar1.setValue(i);
                 try{
                       Thread.sleep(200);
                    }  
                 catch(InterruptedException e)
                    {
                    }
              }
    }
   long f(int n)
    {
        long c=0;
        if(n==1||n==2)
          c=1;
        else if(n>1)
          c=f(n-1)+f(n-2);
        return c; 
    }
    public static void main(String args[])
    {
       ProgressBarDemo win=new ProgressBarDemo();
       win.run();
    }
} 
