import java.io.*;
public class LineCounter{
    public static void main(String[] args){
        LineNumberReader reader=null;
        if(args.length<1){
            System.out.println("Usage: java LineCounter <filename>");
            System.exit(0);
        }
        try {
            reader=new LineNumberReader(new FileReader(args[0]));
            while(reader.readLine()!=null){
            }
            System.out.println("Line number of the last line in the file is: "+(reader.getLineNumber()+1));
        }catch(FileNotFoundException fe){
            System.out.println(fe.getMessage());
        }catch(IOException e){
            System.out.println("Error reading file");
        }finally{
            try {
                reader.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}