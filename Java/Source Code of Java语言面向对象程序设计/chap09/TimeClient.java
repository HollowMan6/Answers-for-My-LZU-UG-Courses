import java.io.*;
import java.net.*;
public class TimeClient{
    final static int PORT=4400;
    public static void main(String args[]) throws IOException {
        if(args.length==0) {
            System.err.println("Not specify server name!");
            System.exit(-1);
        }
        String host=args[0];
        byte msg[]=new byte[256];
        InetAddress address=InetAddress.getByName(host);
        System.out.println("Sending service request to "+address);
        DatagramPacket p=new DatagramPacket(msg,msg.length,address,PORT);
        DatagramSocket skt=new DatagramSocket();
        skt.send(p);
        p=new DatagramPacket(msg,msg.length);
        skt.receive(p);
        String time=new String(p.getData());
        System.out.println("The time at "+host+" is:"+time);
        skt.close();
    }
}