import java.io.*;
import java.net.*;
public class GetMachineInfo{
    public static void main(String[] args) {
        System.out.println("IPV4 Adress="+getIPv4Adress());
        System.out.println("Local Mac Address="+getLocalMACAdress(getIPv4Adress()));
    }
    public static String getIPv4Adress(){
        try{
            InetAddress ip=InetAddress.getLocalHost();
            return ip.getHostName();
        }catch(Exception e){e.printStackTrace();}
        return "127.0.0.1";
    }
    public static String getLocalMACAdress(String ipAddress){
        String str="",strMAC="";
        boolean flag=false;
        try{
            Process pp=Runtime.getRuntime().exec("nbtstat -A "+ipAddress);
            InputStreamReader ir=new InputStreamReader(pp.getInputStream());
            BufferedReader input=new BufferedReader(ir);
            str=input.readLine();
            while(str!=null){
                if(str.indexOf(ipAddress)>1)
                flag=true;
                if(str.indexOf("MAC地址")>0&&flag){
                    int index=str.indexOf("=");
                    strMAC=str.substring(index+2);
                    break;
                }
                str=input.readLine();
            }
        }catch(IOException ex){
            return "Can't Get MAC Address! ";
        }
        return strMAC;
    }
    public static String getMACAddress(String ipAddress){
        String str="",strMAC="";
        try{
            Process p=Runtime.getRuntime().exec("arp -a "+ipAddress);
            InputStreamReader ir=new InputStreamReader(p.getInputStream());
            BufferedReader input=new BufferedReader(ir);
            str=input.readLine();
            while(str!=null){
                if(str.indexOf(ipAddress)>0){
                    strMAC=str;
                    break;
                }
                str=input.readLine();
            }
        }catch(IOException ex){
            return "Can't Find the Machine's MAC Address!";
        }
        return strMAC;
    }
}