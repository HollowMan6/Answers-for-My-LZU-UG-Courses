import java.util.*;
public class SystemTest{
    public static void main(String[] args) {
        long starttime=System.currentTimeMillis();
        String path=System.getenv("Path");
        Properties myprop = System.getProperties();

        System.out.println("version: "+myprop.getProperty("java.version"));
        System.out.println("java home: "+myprop.getProperty("java.home"));
        System.out.println("path = "+path);
        long endtime=System.currentTimeMillis();
        System.out.println("spent time: "+(endtime-starttime));
    }
}