import java.io.*;
public class Objectser
{
   public static void main(String args[]) 
   {
       student stu=new student(981036, "Li Ming", 16, "CSD");
       try
       {
           FileOutputStream fo = new FileOutputStream("data.ser");
           ObjectOutputStream so = new ObjectOutputStream(fo);
           so.writeObject(stu);
           so.close();
           fo.close();
       }
       catch(Exception e)
       {
          System.out.println(e) ;
       }
  }
}
