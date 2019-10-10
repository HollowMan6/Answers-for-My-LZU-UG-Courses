import java.io.*;
public class FileCopy {
    public static void main(String[] args){
        try (InputStream fis=new FileInputStream(new File("src.txt"));
             OutputStream fos=new FileOutputStream(new File("dest.txt"))) {
             byte[] buf=new byte[8192];
             int i;
             while((i=fis.read(buf))!=-1){
                 fos.write(buf,0,i);
             }
        }catch(Exception e){
             e.printStackTrace();
        }
    }
}