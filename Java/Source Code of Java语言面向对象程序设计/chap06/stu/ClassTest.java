import java.util.*;
import java.io.*;
public class ClassTest
{
   public static void main(String[] args) throws Exception
   {
      Scanner in=new Scanner(System.in);
      System.out.print("请输入班级名：");
      String classname=in.nextLine();
      Myclass myapp=new Myclass();
      myapp.setName(classname);
      myapp.ReadFromFile(classname);
      myapp.outputData();
   }
}