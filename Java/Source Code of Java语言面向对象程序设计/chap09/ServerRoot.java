import java.io.*;
import java.net.*;
import java.util.*;
public class ServerRoot { 
   public static void main(String args[]) {  
      ServerSocket server=null;
      ServerThread thread;
      Socket you=null;
      while(true) { 
         try {  
            server=new ServerSocket(4331);
         }catch(IOException e1){  
            System.out.println("正在监听");   //ServerSocket对象不能重复创建。
         } 
         try {  
            System.out.println("正在监听");
            you=server.accept();
            System.out.println("客户的地址:"+you.getInetAddress());
         }catch (IOException e) {  
            System.out.println("正在等待客户");
         }
         if(you!=null) {  
            new ServerThread(you).start(); //为每个客户启动一个专门的线程。  
         }else{continue;}
      }
   }
}
class ServerThread extends Thread {  
   Socket socket;
   DataOutputStream out=null;
   DataInputStream  in=null;
   String s=null;
   ServerThread(Socket t){  
      socket=t;
      try { 
         in=new DataInputStream(socket.getInputStream());
         out=new DataOutputStream(socket.getOutputStream());
      }catch (IOException e){}
   }  
   public void run(){  
      while(true){  
         double a=0,b=0,c=0,root1=0,root2=0;
         try{   
            a=in.readDouble(); //堵塞状态，除非读取到信息。
            b=in.readDouble(); 
            c=in.readDouble(); 
            double disk=b*b-4*a*c;
            root1=(-b+Math.sqrt(disk))/(2*a);
            root2=(-b-Math.sqrt(disk))/(2*a);
            out.writeDouble(root1);
            out.writeDouble(root2);
         }catch (IOException e){  
            System.out.println("客户离开");
            break;
         }
      }
   } 
}

