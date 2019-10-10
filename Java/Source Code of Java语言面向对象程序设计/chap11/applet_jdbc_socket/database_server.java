import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;
public class database_server
{
    public static void main(String args[])
    {
	ServerSocket server=null;Server_thread thread;
	Socket you=null;
	while(true)
	{
	    try
	    {
		server=new ServerSocket(4331);
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
class Server_thread extends Thread
{
    Socket socket;Connection Con=null;Statement stmt=null;
    DataOutputStream out=null;DataInputStream in=null;int n=0;
    String s=null;
    Server_thread(Socket t)
    {
	socket=t;
	try
	{
	    in=new DataInputStream(socket.getInputStream());
	    out=new DataOutputStream(socket.getOutputStream());
	}
	catch(IOException e){}
	try{Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");}
	catch(ClassNotFoundException e){}
	try
	{
	    Con=DriverManager.getConnection("jdbc:odbc:word","","");
	    stmt=Con.createStatement();
	}
	catch(SQLException ee){}
    }
    public void run()
    {
	while(true)
	{
	    try
	    {
		s=in.readUTF();
	    }
	    catch(IOException e){}
	    try
	    {
		if(!(s.equals("客户离开")))
		{
		    n=0;
		    ResultSet rs=stmt.executeQuery("select * from word where 单词="+"'"+s+"'");
		    while(rs.next())
		    {
			String enword=rs.getString("单词");
			if(s.equals(enword))
			{
			    out.writeUTF(rs.getString("解释"));n=1;break;
			}
		    }
		    if(n==0){out.writeUTF("没有此单词");}
		}else if(s.equals("客户离开"))
		{
		    Con.close();
		    System.out.println("客户离开");
		    Thread.currentThread().yield();break;
		}
		sleep(1);
	   }
	   catch(InterruptedException e){}
	   catch(IOException e)
	   {
		try{Con.close();}catch(SQLException ee){}
		System.out.println("客户离开");
		Thread.currentThread().yield();break;
	   }
	   catch(SQLException ee){}
	}
    }
}