import java.io.*;
import java.net.*;
public class FTPServer {
    ThreadGroup group;
    FTPServer(){
	group=new ThreadGroup("FTPThreads");
    }
    protected void finalize(){
	group.stop();
    }
    public static void main(String args[]){
        ServerSocket server=null;
	Socket socket;
	FTPServer ftp=new FTPServer();
	try {
	    server=new ServerSocket(1999);
	    System.out.println("Linstening on port:1999");
	}catch(IOException e){		
  	    System.out.println("ServerSocket Error:"+e);
	}
	while(true){
	    try {
		socket=server.accept();
		System.out.println("New user from"+socket.getInetAddress().toString());
		(new FTPServerThread(ftp.group,ftp,socket)).start();
	    }catch(IOException e){
		System.out.println("Accept Error:"+e);
	    }
	}
    }
}
class FTPServerThread extends Thread {
    FTPServer ftp;
    Socket socket;
    DataInputStream in;
    PrintStream out;
    String line;
    FileList fl;
    public FTPServerThread(ThreadGroup group,FTPServer ftp,Socket socket){
        super(group,"FTPThread");
        this.ftp=ftp;
        this.socket=socket;
        fl=new FileList(".");
    }
    public void run(){
        try {
            in=new DataInputStream(socket.getInputStream());
            out=new PrintStream(socket.getOutputStream());
            while(true){
                line=in.readLine();
                if(line.trim().equals("ls")||line.trim().equals("dir")) fl.Display(out);
                else if(line.trim().equals("quit")||line.trim().equals("bye")) break;
                else if(line.substring(0,2).equals("cd")) fl.ChDir(line.substring(2).trim(),out);
                else if(line.substring(0,3).equals("get")) fl.put(line.substring(3).trim(),out);
                else out.println("Invalid command!");   
            }
            in.close();
            out.close();
            System.out.println("User "+socket.getInetAddress().toString()+" Leaved!");
            socket.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
class FileList {
    File f;
    FileList(String dir) {
        f=new File(dir);
    }
    public void ChDir(String dir,PrintStream out){
        if(dir.equals("..")){
            if(f.getParent()!=null){
                f=new File(f.getParent());
                out.println("Current directory:"+f.getPath());
             }else{
                out.println("Already root directory!");
             }
        }else{
             File g=new File(f,dir);
             if(g.exists()&&g.isDirectory()){
                  f=g;
                  out.println("Current directory:"+f.getPath());
             }else{
                  out.println("No such directory!");
             }
        }
    }
    public void Display(PrintStream out) {
        String []st=f.list();
        File g;
        out.println(st.length);
        for(int i=0;i<st.length;i++){
            g=new File(f,st[i]);
            if(g.isFile()){
                 out.println(format(st[i],30,false)+format(String.valueOf(g.length()),10,true));
            }else{
                 out.println(format(st[i],30,false)+format("<DIR>     ",10,true));
            }
        }
    }
    private String format(String s,int len,boolean pre){
        StringBuffer buf=new StringBuffer(s);
        while(buf.length()<len){
            if(pre) buf.insert(0," ");
            else buf.append(" ");
        }
        return (new String(buf));
    }
    public void put(String fn,PrintStream out) {
        File g=new File(f,fn);
        if(g.exists()&&g.isFile()){
            try {
                FileInputStream fin=new FileInputStream(g);
                int len=(int)g.length();
                byte[]content=new byte[len];
                out.println(len);
                fin.read(content,0,len);
                fin.close();
                out.write(content,0,len);
                out.flush();
            }catch(IOException e) {}
        }else{
           out.print(-1);
           out.println("No such file!");
        }
    }
}