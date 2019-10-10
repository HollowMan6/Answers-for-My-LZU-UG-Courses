import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class ClientRoot extends JFrame implements Runnable,ActionListener {  
   JButton connection,computer;
   JTextField inputA,inputB,inputC;
   JTextArea showResult;
   Socket socket=null;
   DataInputStream in=null; 
   DataOutputStream out=null;
   Thread thread; 
   public  ClientRoot() {  
      socket=new Socket();               //待连接的套接字。
      connection=new JButton("连接服务器");
      computer=new JButton("求方程的根");
      computer.setEnabled(false);        //没有和服务器连接之前,该按钮不可用。
      inputA=new JTextField("0",12);
      inputB=new JTextField("0",12);
      inputC=new JTextField("0",12);
      Box  boxV1=Box.createVerticalBox();
      boxV1.add(new JLabel("输入2次项系数"));
      boxV1.add(new JLabel("输入1次项系数"));
      boxV1.add(new JLabel("输入常数项"));
      Box boxV2=Box.createVerticalBox();
      boxV2.add(inputA);
      boxV2.add(inputB);
      boxV2.add(inputC);
      Box baseBox=Box.createHorizontalBox();
      baseBox.add(boxV1);
      baseBox.add(boxV2);
      Container con=getContentPane();
      con.setLayout(new FlowLayout());
      showResult=new JTextArea(8,18);
      con.add(connection);
      con.add(baseBox);
      con.add(computer);
      con.add(new JScrollPane(showResult));
      computer.addActionListener(this);
      connection.addActionListener(this);
      thread = new Thread(this);
      setBounds(100,100,360,310);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
   }
   public void run(){ 
      while(true){   
         try{  
             double root1=in.readDouble();  //堵塞状态，除非读取到信息。
             double root2=in.readDouble(); 
             showResult.append("\n两个根:\n"+root1+"\n"+root2);
             showResult.setCaretPosition((showResult.getText()).length());
         }catch(IOException e){  
             showResult.setText("与服务器已断开");
             computer.setEnabled(false);
             break;
         } 
     }
  }
  public void actionPerformed(ActionEvent e){ 
    if(e.getSource()==connection){
        try {   //请求和服务器建立套接字连接：
            if(socket.isConnected()){
            }else{
                InetAddress  address=InetAddress.getByName("127.0.0.1");
                InetSocketAddress socketAddress=new InetSocketAddress(address,4331);
                socket.connect(socketAddress); 
                in =new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                computer.setEnabled(true);
                thread.start();
            }
        }catch (IOException ee){}
    }
    if(e.getSource()==computer){
        try {
           double a=Double.parseDouble(inputA.getText()),
                  b=Double.parseDouble(inputB.getText()),
                  c=Double.parseDouble(inputC.getText());
           double disk=b*b-4*a*c;
           if(disk>=0){
              out.writeDouble(a);
              out.writeDouble(b);
              out.writeDouble(c);
           }else{ 
               inputA.setText("此2次方程无实根");
           }
        }catch(Exception ee){
           inputA.setText("请输入数字字符");
        }
     }
  }
  public static void main(String args[]){ 
      ClientRoot win=new  ClientRoot();
  }
}
