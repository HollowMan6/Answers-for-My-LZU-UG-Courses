import java.net.*;
import java.io.*;
class Ping{
    public static void main(String[] args){
        System.out.println("请输入ping主机的名称或地址:");
        try {
            String host=keyreadline();
            InetAddress ip=InetAddress.getByName(host);
            System.out.println(""+ip);
            long t1=System.currentTimeMillis();
            if(ip.isReachable(50000)){
                long t2=System.currentTimeMillis();
                System.out.println("\nReply from "+ip.getHostAddress()+
                    " time<="+(t2-t1)+"ms");
                System.out.println();
            }else{
                System.out.println("Request timed out.");
            }
            if(pingServer(host,5000))System.out.println(true);
        }catch(IOException e){
            System.out.println("error:Request timed out.");
        }
    }
    private static String keyreadline(){
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str=null;
        try {
           str=br.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        return str;
    }
    /* 
     * * 能否ping通IP地址 
     * 
     * @param server IP地址 
     * 
     * @param timeout 超时时长(毫秒) 
     * 
     * @return true能ping通 
     */ 
    public static boolean pingServer(String server, int timeout) 
    { 
        BufferedReader in = null; 
        Runtime r = Runtime.getRuntime(); 
        
        String pingCommand = "ping " + server + " -n 1 -w " + timeout; 
        try 
        { 
            Process p = r.exec(pingCommand); 
            if (p == null) 
            { 
                return false; 
            } 
            in = new BufferedReader(new InputStreamReader(p.getInputStream())); 
            String line = null; 
            while ((line = in.readLine()) != null) 
            { 
                if (line.startsWith("Reply from")) 
                { 
                    return true; 
                } 
            } 
            
        } 
        catch (Exception ex) 
        { 
            ex.printStackTrace(); 
            return false; 
        } 
        finally 
        { 
            try 
            { 
                in.close(); 
            } 
            catch (IOException e) 
            { 
                e.printStackTrace(); 
            } 
        } 
        return false; 
    } 
}
            
            