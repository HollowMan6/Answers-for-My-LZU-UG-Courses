import java.util.*;
import java.io.*;
public class MyclassTest
{
   public static void main(String[] args) throws Exception
   {
      Scanner in=new Scanner(System.in);
      System.out.print("请输入班级名：");
      String classname=in.nextLine();
      System.out.print("请输入班级人数：");
      int rs=in.nextInt();
      Myclass myapp=new Myclass(classname,rs);
      myapp.inputData();
      myapp.outputData();
      myapp.WriteToFile();
   }
}