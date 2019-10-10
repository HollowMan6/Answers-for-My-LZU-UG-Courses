import java.io.*;
import java.net.*;
public class Client
{    public static void main(String args[])
    {
	String s=null;Socket mysocket;
	DataInputStream in=null;
	DataOutputStream out=null;
        String username=null;
	try
	{
	    mysocket=new Socket("localhost",4331);
	    in=new DataInputStream(mysocket.getInputStream());
	    out=new DataOutputStream(mysocket.getOutputStream());
            BufferedReader keyin=new BufferedReader(new InputStreamReader(System.in));
	    s=in.readUTF();
            System.out.print(s);
            username=keyin.readLine();
            out.writeUTF(username);
            s=in.readUTF();
            System.out.println(s);
            s=in.readUTF();
            System.out.println(s);
            s=keyin.readLine();
            double d=Double.parseDouble(s);

            out.writeDouble(d);
            d=in.readDouble();
            System.out.println(d);
	   	
	    mysocket.close();
	}
	catch(IOException e){System.out.println("无法连接");}
	
    }}
