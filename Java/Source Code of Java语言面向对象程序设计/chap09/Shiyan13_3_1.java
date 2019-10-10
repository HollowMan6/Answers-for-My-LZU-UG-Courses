import java.net.*;
import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
class A extends JFrame implements Runnable,ActionListener{  
   JTextField outMessage=new JTextField(12);
   JTextArea inMessage=new JTextArea(12,20); 
   JButton b=new JButton("发送数据");
   BufferedWriter bwin=null;
   BufferedWriter bwout=null;
   A() {  
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
      addWindowListener(new WindowDestroyer());
      try { 
          bwin=new BufferedWriter(new FileWriter("A_in_log.txt",true));
          bwout=new BufferedWriter(new FileWriter("A_out_log.txt",true));
      }catch(IOException e){e.printStackTrace();}
      thread.start();                                   //线程负责接收数据。
   }
  public void actionPerformed(ActionEvent event){        //点击按扭发送数据。
      String outmsg=outMessage.getText();
      byte b[]=outmsg.trim().getBytes();
      try{ 
            InetAddress address=InetAddress.getByName("127.0.0.1");
            DatagramPacket data=new DatagramPacket(b,b.length,address,1234);
            DatagramSocket mail=new DatagramSocket();
            bwout.write("发送:"+address.getHostAddress()+"\t时间:"+(new java.util.Date()));
            bwout.write("\t内容:"+outmsg);
            bwout.newLine();
            bwout.flush();
            mail.send(data);           
      }catch(Exception e){}     
   } 
  public void run() {                               //接收数据。
      DatagramPacket pack=null;
      DatagramSocket mail=null;
      byte b[]=new byte[8192];
      try { 
            pack=new DatagramPacket(b,b.length);
            mail=new DatagramSocket(5678);
      }catch(Exception e){} 
      while(true){
           try{  mail.receive(pack); 
                 String message=new String(pack.getData(),0,pack.getLength());
                 inMessage.append("收到数据来自："+pack.getAddress());
                 inMessage.append("\n收到数据是："+message+"\n");
                 inMessage.setCaretPosition(inMessage.getText().length());
                 bwin.write("收到:"+pack.getAddress()+"\t");
                 bwin.write("时间:"+(new java.util.Date())+"\t");
                 bwin.write("内容:"+message);
                 bwin.newLine();
                 bwin.flush();
           }catch(Exception e){}
      } 
   }
   public static void main(String args[]){
       new A();
   }
   class WindowDestroyer extends WindowAdapter {
       public void windowClosing(WindowEvent e) {
           try {
               bwin.close();
               bwout.close();
           }catch(IOException e1){}
           System.exit(0);
       }
    }
}  

class B extends JFrame implements Runnable,ActionListener{  
   JTextField outMessage=new JTextField(12);
   JTextArea inMessage=new JTextArea(12,20); 
   JButton b=new JButton("发送数据");
   BufferedWriter bwin=null;
   BufferedWriter bwout=null;
   B(){
      super("I AM B");
      setBounds(350,100,320,200);
      setVisible(true);
      JPanel p=new JPanel();
      b.addActionListener(this);
      p.add(outMessage);
      p.add(b);
      Container con=getContentPane();
      con.add(new JScrollPane(inMessage),BorderLayout.CENTER);
      con.add(p,BorderLayout.NORTH);
      Thread thread=new Thread(this);
      addWindowListener(new WindowDestroyer());
      try { 
          bwin=new BufferedWriter(new FileWriter("B_in_log.txt",true));
          bwout=new BufferedWriter(new FileWriter("B_out_log.txt",true));
      }catch(IOException e){e.printStackTrace();}
      thread.start();                                   //线程负责接收数据。
   }
   public void actionPerformed(ActionEvent event){        //点击按扭发送数据。
      String outmsg=outMessage.getText();
      byte b[]=outmsg.trim().getBytes();
      try { 
            InetAddress address=InetAddress.getByName("127.0.0.1");
            DatagramPacket data=new DatagramPacket(b,b.length,address,5678);
            DatagramSocket mail=new DatagramSocket();
            bwout.write("发送:"+address.getHostAddress()+"\t时间:"+(new java.util.Date()));
            bwout.write("\t内容:"+outmsg);
            bwout.newLine();
            bwout.flush();
            mail.send(data);
      }catch(Exception e){}     
   } 
   public void run() {                               //接收数据。
      DatagramPacket pack=null;
      DatagramSocket mail=null;
      byte b[]=new byte[8192];
      try { 
            pack=new DatagramPacket(b,b.length);
            mail=new DatagramSocket(1234);
      }catch(Exception e){} 
      while(true){   
           try{  mail.receive(pack); 
                 String message=new String(pack.getData(),0,pack.getLength());
                 inMessage.append("收到数据来自："+pack.getAddress());
                 inMessage.append("\n收到数据是："+message+"\n");
                 inMessage.setCaretPosition(inMessage.getText().length());
                 bwin.write("收到:"+pack.getAddress()+"\t");
                 bwin.write("时间:"+(new java.util.Date())+"\t");
                 bwin.write("内容:"+message);
                 bwin.newLine();
                 bwin.flush();
           }catch(Exception e){}
       } 
   }
   public static void main(String args[]){
       new B();
   }
   class WindowDestroyer extends WindowAdapter {
       public void windowClosing(WindowEvent e) {
           try {
               bwin.close();
               bwout.close();
           }catch(IOException e1){}
           System.exit(0);
       }
    }
} 