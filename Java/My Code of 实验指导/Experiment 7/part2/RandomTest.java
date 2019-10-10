import java.io.*;
public class RandomTest{
    public static void main(String[] args) {
        File f=new File("RandomTest.java");
        try{
            RandomAccessFile random = new RandomAccessFile(f, "r");
            long l=f.length();
            char ch;
            for(long i=l-1;i>=0;i--){
                random.seek(i);
                ch=(char)random.read();
                System.out.println(ch);
            }
            random.close();
        }catch(Exception e){System.out.println("IOError!");}
    }
}