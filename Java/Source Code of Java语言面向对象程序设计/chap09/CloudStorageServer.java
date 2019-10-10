import java.io.*;
import java.net.*;
import java.util.logging.*;

public class CloudStorageServer {
    private static ServerSocket server;
    public static void main(String[] args) {
        Socket requestSocket=null;
        new Thread(new Monitor()).start();
        try {
            server=new ServerSocket(10000);
            System.out.println("Server started:");
            System.out.println("Hit Enter to stop server");
            try {
                while(true) {
                    requestSocket=server.accept();
                    new Thread(new RequestProcessor(requestSocket)).start();
                }
            }finally{
                requestSocket.close();
            }
        }catch(Exception ex) {
            Logger.getLogger(CloudStorageServer.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    private static void shutdownServer(){
        try {
            server.close();
        }catch(IOException ex) {
        }
        System.exit(0);
    }
    private static class Monitor implements Runnable {
        public void run(){
            try {
                while(System.in.read()!='\n'){
                }
            }catch(IOException ex) {
            }
            shutdownServer();
        }
    }
    private static class RequestProcessor implements Runnable {
        private Socket requestSocket;
        public RequestProcessor(Socket requestSocket) {
            this.requestSocket=requestSocket;
        }
        @Override
        public void run() {
            try {
                DataInputStream reader=new DataInputStream(requestSocket.getInputStream());
                DataOutputStream writer=new DataOutputStream(requestSocket.getOutputStream());
                int cmd=reader.readInt();
                String fileName=reader.readUTF();
                String message;
                if(cmd==0){
                    message="Put";
                }else {
                    message="Get";
                }
                message+=fileName+" requested";
                System.out.println(message);
                if(cmd==0){
                    uploadFile(reader,fileName);
                }else if(cmd==1){
                    downloadFile(writer,fileName);
                }
            }catch(IOException ex){
                Logger.getLogger(CloudStorageServer.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
        private void uploadFile(DataInputStream in,String fname){
            try {
                BufferedWriter writer=new BufferedWriter(new FileWriter("server-"+fname));
                String str;
                while(!(str=in.readUTF()).equals("-1")){
                    writer.write(str);
                    writer.newLine();
                }
                in.close();
                writer.close();
                System.out.println("'"+fname+"' saved under name '"+fname+"'");
            }catch(IOException ex) {
                Logger.getLogger(CloudStorageServer.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
        private void downloadFile(DataOutputStream out,String fname){
            try {
                BufferedReader reader=new BufferedReader(new FileReader("server-"+fname));
                String str=reader.readLine();
                while(str!=null){
                    out.writeUTF(str);
                    str=reader.readLine();
                }
                out.writeUTF("-1");
                reader.close();
                out.close();
            }catch(IOException ex) {
                Logger.getLogger(CloudStorageServer.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    }
}
            
            