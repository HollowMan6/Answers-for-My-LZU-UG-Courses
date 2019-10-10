import java.net.*;
import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
class A extends JFrame implements Runnable,ActionListener
{  
   JTextField outMessage=new JTextField(12);
   JTextArea inMessage=new JTextArea(12,20); 
   JButton b=new JButton("发送数据");
   A()
   {  
      super("I AM A");
      setSize(320,200);
      setVisible(true);
      JPanel p=new JPanel();
      b.addActionListener(this);
      p.add(outMessage);
      p.add(b);
      Container con=getContentPane();
      con.add(new JScrollPane(inMessage),BorderLayout.CENTER);
      con.add(p,BorderLayout.NORTH);
      Thread thread=new Thread(this);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      thread.start();                                   //线程负责接收数据。
   }
  public void actionPerformed(ActionEvent event)        //点击按扭发送数据。
   { 
      byte b[]=outMessage.getText().trim().getBytes();
      try{ 
            InetAddress address=InetAddress.getByName("127.0.0.1");
            DatagramPacket data=new DatagramPacket(b,b.length,address,1234);
            DatagramSocket mail=new DatagramSocket();
            mail.send(data);
          }
      catch(Exception e){}     
   } 
  public void run()                                //接收数据。
   {  
      DatagramPacket pack=null;
      DatagramSocket mail=null;
      byte b[]=new byte[8192];
      try{ 
            pack=new DatagramPacket(b,b.length);
            mail=new DatagramSocket(5678);
         }
      catch(Exception e){} 
      while(true)   
         {  
           try{  mail.receive(pack); 
                 String message=new String(pack.getData(),0,pack.getLength());
                 inMessage.append("收到数据来自："+pack.getAddress());
                 inMessage.append("\n收到数据是："+message+"\n");
                 inMessage.setCaretPosition(inMessage.getText().length());
              }
          catch(Exception e){}
         } 
   }
 public static void main(String args[])
   {
       new A();
   }
}  
