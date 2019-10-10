import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
public class database_client extends Applet implements Runnable,ActionListener
{
    Button query;TextField entxt,chtxt;
    Socket socket=null;
    DataInputStream in=null;
    DataOutputStream out=null;
    Thread thread;
    public void init()
    {
	query=new Button("查询");
	entxt=new TextField(10);chtxt=new TextField(20);
	add(new Label("输入要查询的英文单词："));add(entxt);
	add(new Label("汉语解释："));add(chtxt);add(query);
	query.addActionListener(this);
    }
    public void start()
    {
	try
	{
	    socket=new Socket(this.getCodeBase().getHost(),4331);
	    in=new DataInputStream(socket.getInputStream());
	    out=new DataOutputStream(socket.getOutputStream());
	}
	catch(IOException e){}
	if(thread==null)
	{
	    thread=new Thread(this);
	    thread.setPriority(Thread.MIN_PRIORITY);
	    thread.start();
	}
    }
    public void stop()
    {
	try 
	{
	    out.writeUTF("客户离开");
        }
	catch(IOException e){}
    }
    public void run()
    {
	String s=null;
	while(true)
	{
	    try{s=in.readUTF();}
	    catch(IOException e1){chtxt.setText("与服务器已断开");break;}
	    chtxt.setText(s);
	}
    }
    public void actionPerformed(ActionEvent e)
    {
	if(e.getSource()==query)
	{
	    String s=entxt.getText();
	    if(s!=null)
	    {
		try{out.writeUTF(s);}catch(IOException e1){}
	    }
	}
    }
}