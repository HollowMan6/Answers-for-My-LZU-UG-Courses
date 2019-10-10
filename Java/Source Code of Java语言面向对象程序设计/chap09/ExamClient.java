import java.net.*;
import java.io.*;
public class ExamClient
{
   static Socket svr;
   public static void main(String[] args) throws Exception
   {
      System.out.println("正在连接服务器，请稍候...");
      svr=new Socket(InetAddress.getLocalHost(),3300);
      if(svr!=null)
         System.out.println("与"+svr.getInetAddress()+" 连接成功！请输出要传送的信息...");
      BufferedReader in=new BufferedReader(new InputStreamReader(svr.getInputStream())); //创建套节字输入流
      PrintWriter out=new PrintWriter(svr.getOutputStream());  //创建套节字输出流
      BufferedReader wt=new BufferedReader(new InputStreamReader(System.in)); //创建键盘输入流
      while(true)
      {
         String str=wt.readLine();
         out.println(str);out.flush();
         if(str.equals("bye"))
         {
            in.close();out.close();break;//关闭输入输出流
         }
         System.out.println(in.readLine());
      }
      svr.close();
   }
}