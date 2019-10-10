import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;
public class dbserver
{
    public static void main(String args[])
    {
	ServerSocket server=null;
                   Server_thread thread;
	Socket you=null;
	while(true)
	{
	    try
	    {
		server=new ServerSocket(4004);
	    }
	    catch(IOException e1){System.out.println("正在监听");}
	    try
	    {
		you=server.accept();
	    }
	    catch(IOException e2){System.out.println("正在等待客户");}
	    if(you!=null)
                      {
		new Server_thread(you).start();
	    }else{continue;}
	}
    }
}