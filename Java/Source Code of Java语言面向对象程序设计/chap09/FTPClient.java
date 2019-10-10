import java.io.*;
import java.net.*;
public class FTPClient{
    public static void main(String args[]){
        Socket socket;
        DataInputStream in;
        PrintStream out;
        String line;
        DataInputStream stdin=new DataInputStream(System.in);
        if(args.length!=1){
            System.out.println("Useage:java FTPClient <hostname>");
            return ;
        }
        try {
            socket=new Socket(args[0],1999);
            in=new DataInputStream(socket.getInputStream());
            out=new PrintStream(socket.getOutputStream());
            while(true) {
            System.out.print("majun:");
            line=stdin.readLine();
            if(line.trim().equals(""))     continue;
            out.println(line);
            if(line.trim().equals("quit")||line.trim().equals("bye"))       break;
            else if(line.trim().equals("ls")||line.trim().equals("dir")) {
                int num=Integer.parseInt(in.readLine());
                for(int i=0;i<num;i++) {
                    line=in.readLine();
                    System.out.println(line);
                }
                System.out.println("Total:"+num+" Items.");
            }else if(line.trim().substring(0,3).equals("get")){
                String mystr=in.readLine();//int len=Integer.parseInt(mystr);
                int len=100;
                System.out.println("majun:"+line+"\nmystr:"+mystr+"\nlen="+len);
                if(len<0) {
                    line=in.readLine();
                    System.out.println(line);
                }else{
                    FileOutputStream fout=new FileOutputStream(line.substring(3).trim());
                    byte[]content=new byte[len];
                    fout.write(content,0,len);
                    fout.close();
                    System.out.println(len+"bytes received");
                }
            }else{
                line=in.readLine();
                System.out.println(line);
            } }                                      
            in.close();
            out.close();
            socket.close();
        }catch(IOException e){
            System.out.println("Error"+e);
        }
    }
}