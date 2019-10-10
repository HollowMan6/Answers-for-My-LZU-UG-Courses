import java.io.*;
import java.net.*;
class urltest
{
   public static void main(String[] args)
   {
      try{
         URL myurl=new URL(args[0]);
         BufferedReader in=new BufferedReader(new InputStreamReader(myurl.openStream()));
         String line=in.readLine();
         BufferedWriter out=new BufferedWriter(new FileWriter("out.html"));
         while(line!=null)
         {
            out.write(line);
            out.newLine();
            System.out.println(line);
            line=in.readLine();
 
         }
         in.close();
         out.close();
      }
      catch(ArrayIndexOutOfBoundsException e){System.out.println("使用格式：java urltest URL");}
      catch(MalformedURLException e1){System.out.println("非法URL");}
      catch(IOException e2){}

   }
}