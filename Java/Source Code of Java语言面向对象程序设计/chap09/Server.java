import java.io.*;
import java.net.*;
public class Server
{
    public static void main(String args[])
    {
	ServerSocket server=null;
	Socket you=null;String s=null;
	DataOutputStream out=null;
        DataInputStream in=null;
	try
	{
	    server=new ServerSocket(4331);
	}
	catch(IOException e1){System.out.println("ERRO:"+e1);}
	try
	{
            System.out.println("正在监听....");
	    you=server.accept();
	    System.out.println("port="+you.getPort());
	    in=new DataInputStream(you.getInputStream());
	    out=new DataOutputStream(you.getOutputStream());
            out.writeUTF("login:");
            s=in.readUTF();
            System.out.println("用户："+s);
            out.writeUTF("user: "+s+" login successful");
            out.writeUTF("please input a double:");

            double d=in.readDouble();
            out.writeDouble(Math.sqrt(d));
            System.out.println(d);

	    you.close();
	}
	catch(IOException e)
	{
	    System.out.println("ERRO:"+e);
	}
    }
}