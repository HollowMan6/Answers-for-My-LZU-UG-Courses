import java.io.*;
import java.net.*;
public class CloudStore {
    public static void main(String[] args) {
        Socket requestSocket=null;
        if(args.length<2){
            System.out.println("Usage: java CloudStore get/put filename");
            System.exit(0);
        }
        int cmd=0;
        switch(args[0]){
            case "get":cmd=1;break;
            case "put":cmd=0;break;
        }
        String fileName=args[1];
        try {
            try {
                requestSocket=new Socket();
                requestSocket.connect(new InetSocketAddress("localhost",10000));
                DataOutputStream writer=new DataOutputStream(requestSocket.getOutputStream());
                writer.writeInt(cmd);
                writer.writeUTF(fileName);
                if(cmd==0) {//put
                    BufferedReader reader=new BufferedReader(new FileReader(fileName));
                    String str=null;
                    while((str=reader.readLine())!=null){
                        writer.writeUTF(str);
                    }
                    writer.writeUTF("-1");
                    System.out.println(fileName+" uploaded successfully");
                    reader.close();
                    writer.close();
                }else{ //get
                    DataInputStream reader=new DataInputStream(requestSocket.getInputStream());
                    BufferedWriter fileWriter=new BufferedWriter(new FileWriter(fileName));
                    String str=null;
                    while(!(str=reader.readUTF()).equalsIgnoreCase("-1")){
                        fileWriter.write(str);
                        fileWriter.newLine();
                        System.out.println(str);
                    }
                    reader.close();
                    fileWriter.close();
                }
            }finally{
                requestSocket.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
                    