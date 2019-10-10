import java.awt.*;
 import java.io.*;   
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
 
public class BroadCastWord extends Frame implements ActionListener
{   
   int port;                                        //组播的端口. 
   InetAddress group=null;                          //组播组的地址.
   MulticastSocket socket=null;                     //多点广播套接字. 
   Timer time=null;                                 //计时器,负责每隔多长时间播放一个内容.
   FileDialog open=null;                            //选择文件对话框.
   Button select,开始广播,停止广播;
   File file=null;                                  //要播放的文件.
   String FileDir=null,fileName=null;
   FileReader in=null;                              //负责读取文件的流. 
   BufferedReader bufferIn=null;
   int   token=0;                                   //文件的长度.  
   TextArea 显示正在播放内容,显示已播放的内容;       
  public BroadCastWord()
  {
   super("单词广播系统");
   select=new Button("选择要广播的文件");
   开始广播=new Button("开始广播");
   停止广播=new Button("停止广播");
   select.addActionListener(this); 
   开始广播.addActionListener(this); 
   停止广播.addActionListener(this); 
   time=new Timer(2000,this);                     //每隔2秒"振铃"一次,导致ActionEvent事件.
   open=new FileDialog(this,"选择要广播的文件",FileDialog.LOAD);  
   显示正在播放内容=new TextArea(10,10);
   显示正在播放内容.setForeground(Color.blue); 
   显示已播放的内容=new TextArea(10,10);
   Panel north=new Panel();
   north.add(select);
   north.add(开始广播);
   north.add(停止广播);
   add(north,BorderLayout.NORTH);
   Panel center=new Panel();
   center.setLayout(new GridLayout(1,2)); 
   center.add(显示正在播放内容);
   center.add(显示已播放的内容);
   add(center,BorderLayout.CENTER);
   validate();
   try 
      {
       port=5000;                                   //设置组播组的监听端口为5000.
       group=InetAddress.getByName("239.255.0.0");  //设置组播组的地址为239.255.0.0.
       socket=new MulticastSocket(port);          //多点广播套接字将在port端口广播.
       socket.setTimeToLive(1);                 //多点广播套接字发送数据报范围为本地网络.
       socket.joinGroup(group);                //加入组播组,加入group后,socket发送的数据报.
                                              //可以被加入到group中的成员接收到.
      } 
  catch(Exception e)
       {
         System.out.println("Error: "+ e);          
       }
  setBounds(100,50,360,380);   
  setVisible(true);
  addWindowListener(new WindowAdapter()
                     { public void windowClosing(WindowEvent e)
                       { System.exit(0);
      	               }
      	             });
 }
 public void actionPerformed(ActionEvent e)
 {
   if(e.getSource()==select)
     {
      显示已播放的内容.setText(null);
      open.setVisible(true);
      fileName=open.getFile();
      FileDir=open.getDirectory(); 
      if(fileName!=null)
       { 
         time.stop();                                      //停止振铃,打断播放.
         file=new File(FileDir,fileName);
         try
            {
               file=new File(FileDir,fileName);
               in=new FileReader(file);                      
               bufferIn=new BufferedReader(in);
            }
         catch(IOException ee)
            {
            }
       }
     }
   else if(e.getSource()==开始广播)
     {
        time.start();
     }
   else if(e.getSource()==time)
     {
       String s=null;
        try
            {  
              if(token==-1)
                 {
                   file=new File(FileDir,fileName);
                   in=new FileReader(file);                      
                   bufferIn=new BufferedReader(in);
                 }
              s=bufferIn.readLine();
              if(s!=null)
                {
                  token=0;
                  显示正在播放内容.setText("正在广播的内容:\n"+s);
                  显示已播放的内容.append(s+"\n");
                  DatagramPacket packet=null;                            //待发送的数据包.
                  byte data[]=s.getBytes(); 
                  packet=new DatagramPacket(data,data.length,group,port); 
                  socket.send(packet);                                  //发送数据包. 
                }
              else
                {
                  token=-1;
                }
            }
        catch(IOException ee)
            {
            }
     }
   else if(e.getSource()==停止广播)
     {
       time.stop();
     }
 }
                                         
 public static void main(String[] args)
   {
      BroadCastWord broad=new BroadCastWord();
   }
}
