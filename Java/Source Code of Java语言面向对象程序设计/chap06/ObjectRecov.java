import java.io.*;
public class ObjectRecov
{
   public static void main(String args[]) 
   {
      student stu=null;
      try
      {
           FileInputStream fi = new FileInputStream("data.ser");
           ObjectInputStream si = new ObjectInputStream(fi);
           stu = (student)si.readObject();
           si.close();
      }
      catch(Exception e)
      {
         System.out.println(e);
      }
      System.out.println("ID: "+stu.id+"\tname:"+
                stu.name+"\tage:"+stu.age+"\tdept.:"+stu.department);
   }
}
