import java.io.*;
class Mytype{
  public static void main(String[] args) throws Exception
  {
     FileInputStream fin=new FileInputStream(args[0]);
     InputStreamReader isr=new InputStreamReader(fin);
     BufferedReader br=new BufferedReader(isr);
     String str=br.readLine();
     while(str!=null)
     {
        System.out.println(str);
        str=br.readLine();
     }
  }
}