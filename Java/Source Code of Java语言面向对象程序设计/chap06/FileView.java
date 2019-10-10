import java.io.*;

public class FileView{
    public static void main(String[] args){
        int numberRead=0;
        FileReader reader=null;
        PrintWriter writer=null;
        char buffer[]=new char[512];
        if(args.length<1){
            System.out.println("Usage: java FileView filename");
            System.exit(0);
        }
        try {
            reader=new FileReader(args[0]);
            writer=new PrintWriter(System.out);
            while((numberRead=reader.read(buffer))!=-1){
                  writer.write(buffer,0,numberRead);
            }
        }catch(FileNotFoundException fe){
            System.out.println(fe.getMessage());
            System.exit(0);
        }catch(IOException ioe){
            System.out.println("Error reading/writing file");
        }finally{
            try{
                reader.close();
                writer.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
