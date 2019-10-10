import java.io.*;
import java.util.*;
public class mydir
{
   public static void main(String[] args) throws IOException
   {
      File currentdir;
      String filename;
      int fcount=0;
      if(args.length==0)
      {
          filename=".";
      }
      else
      {
          filename=args[0];
      }
      currentdir=new File(filename);
      if(currentdir.isDirectory())
      {
          String[] mydirs=currentdir.list();
          for(int i=0;i<mydirs.length;i++)
          {
             File mfile=new File(filename+"/"+mydirs[i]);
             Date mtime=new Date(mfile.lastModified());
             System.out.print(""+mtime);
             if(mfile.isFile())
             {   System.out.print("\t\t");
                 System.out.println(mfile.length()+"\t"+mydirs[i]);
                 fcount++;
             }
             else
             {   System.out.print("\t<DIR>\t");
                 System.out.println("\t"+mydirs[i]);
             }
          }

      }
      else
      {
           fcount++;
           Date mtime=new Date(currentdir.lastModified());
           System.out.print(""+mtime);
           System.out.print("\t\t");
           System.out.println(currentdir.length()+"\t"+currentdir.getName());
      }
      System.out.println("文件数："+fcount);
   }
}