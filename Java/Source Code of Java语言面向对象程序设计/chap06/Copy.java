import java.io.*;
public class Copy {
    public static void main(String[] args) {
        int numberRead=0;
        InputStream in=null;
        OutputStream out=null;
        byte buf[]=new byte[512];
        if(args.length!=2){
            System.out.println("Usage: java Copy sourcefile destfile");
            System.exit(0);   //终止程序，退出JVM
        }
        try {
            in=new FileInputStream(args[0]);
            out=new FileOutputStream(args[1]);
            while((numberRead=in.read(buf))!=-1){
                out.write(buf,0,numberRead);
            }
        }catch(FileNotFoundException fe){
            System.out.println(args[0]+" not found.");
            System.exit(0);
        }catch(IOException ioe){
            System.out.println("Error reading/writing file.");
        }finally{
            try {
                in.close();
                out.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("1 file copied.");
    }
}