import java.net.*;
import java.io.*;
public class Client{
    public static void main(String[] args) throws Exception{
        Socket soc =new Socket("localhost",4002);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入消息至服务器");
        String message="";
        String temp;
        PrintStream ps=new PrintStream(soc.getOutputStream());
        while(!((temp=br.readLine()).equals("quit"))){
            ps.println(temp);
        }
        ps.close();
        soc.close();
    }
}