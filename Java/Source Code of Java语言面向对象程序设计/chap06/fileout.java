import java.io.*;
class fileout{
  public static void main(String[] args) throws Exception
  {
     FileOutputStream file=new FileOutputStream("fileout.txt");
     for(int i='A';i<='Z';i++)
        file.write(i);
     file.close();
  }
}