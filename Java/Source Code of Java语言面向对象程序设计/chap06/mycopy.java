import java.io.*;
class mycopy
{
  public static void main(String[] args)
  {
     int ch;
     FileInputStream fin;
     FileOutputStream fout;
     try
     {
        fin=new FileInputStream(args[0]);
        fout=new FileOutputStream(args[1]);
        ch=fin.read();
        while(ch!=-1)
        {
            fout.write(ch);
            ch=fin.read();
        }
        fin.close();fout.close();
     }catch(ArrayIndexOutOfBoundsException e1)
     { System.out.println("使用格式错误！正确格式为：java mycopy 源文件名 目标文件名");
       System.exit(0);
     }
     catch(FileNotFoundException e3){System.out.println("文件没有找到!");}
     catch(IOException e2){System.out.println("流错误！");}
  }
}