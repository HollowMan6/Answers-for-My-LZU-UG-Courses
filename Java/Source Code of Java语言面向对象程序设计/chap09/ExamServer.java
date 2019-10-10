import java.io.*;
import java.net.*;
public class ExamServer
{
  public static void main(String[] args) throws IOException
  {
     ServerSocket svr=new ServerSocket(3300); //建立服务器套节字
     System.out.println("等待连接......"); 
     Socket clt=svr.accept();  //等待客户机连接
     BufferedReader in=new BufferedReader(new InputStreamReader(clt.getInputStream()));
     PrintWriter out=new PrintWriter(clt.getOutputStream());
     while(true)
     {
        String str=in.readLine();  //从套节字输入流读入一行
        System.out.println(str);
        out.println("服务器已收到您发送的："+str); //向客户机发送响应信息
        out.flush();  //强制缓冲数据输出
        if(str.equals("bye"))
        {
            in.close();out.close();break;  //关闭输入输出流
        }
     }
     clt.close();
  }
}