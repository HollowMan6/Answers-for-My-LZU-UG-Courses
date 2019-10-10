import java.io.*;
public class Type{
  public static void main(String[] args){
    FileInputStream filein;
    int ch;
    try{
      filein=new FileInputStream(args[0]);
      ch=filein.read();
      while(ch!=-1)
      {
         System.out.print((char)ch);
         ch=filein.read();
      }
      filein.close();
    }
    catch(ArrayIndexOutOfBoundsException e1)
    {
       System.out.println("使用格式：java Type 文本文件名");
    }
    catch(FileNotFoundException e2)
    {
       System.out.println("你输入的文件不存在!");
    }
    catch(IOException e3)
    {
      System.out.println("流输入输出错误!");
    }
  }
}