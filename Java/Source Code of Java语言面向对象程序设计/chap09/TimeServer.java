import java.io.*;
import java.net.*;
import java.util.*;
public class TimeServer {
    final static int PORT=4400;
    public static void main(String[] args) throws IOException {
        DatagramSocket skt=new DatagramSocket(PORT);
        while(true){
            byte buffer[]=new byte[256];
            DatagramPacket p=new DatagramPacket(buffer,buffer.length);
            skt.receive(p);
            String date=new Date().toString();
            buffer=date.getBytes();
            InetAddress address=p.getAddress();
            int port=p.getPort();
            p=new DatagramPacket(buffer,buffer.length,address,port);
            skt.send(p);
        }
    }
}

            