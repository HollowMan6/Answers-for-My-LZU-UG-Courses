import java.io.*;
class mergefile{
  public static void main(String[] args)
  {
     FileReader fr;
     FileWriter fw;
     int ch;
     try{
        fr=new FileReader(args[0]);
        fw=new FileWriter(args[2]);
        ch=fr.read();
        while(ch!=-1)
        { 
           fw.write(ch);
           ch=fr.read();
        }
        fr.close();
        fr=new FileReader(args[1]);
        ch=fr.read();
        while(ch!=-1)
        { 
           fw.write(ch);
           ch=fr.read();
        }
        fr.close();
        fw.close();
     }
     catch(ArrayIndexOutOfBoundsException e1)
     {
        System.out.println("使用格式：java mergefile 源文件1 源文件2  目标文件");
     }
     catch(FileNotFoundException e2)
     { 
        System.out.println("你输入的文件不存在");
     }
     catch(IOException e3)
     {
        System.out.println("流错误!");
     }
  }
}