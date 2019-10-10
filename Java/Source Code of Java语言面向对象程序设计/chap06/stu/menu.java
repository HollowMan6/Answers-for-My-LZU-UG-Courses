import java.io.*;
import java.util.*;
class menu  {
   public static void main(String args[]) throws Exception  {
      int i;
      while(true)  {
         i=display();
         switch(i)  {
            case 1:
              createbj();break;
            case 2:
              managebj();break;
            case 3:
              System.exit(0);break;
            case 4:
            default:
            {System.out.println("选择有误！");break;}
         }
      }
   }
   public static int display() {
       System.out.println("\t\t*********************************");
       System.out.println("\t\t*    1-----创建新的班级！       *");
       System.out.println("\t\t*    2-----显示某班级学生成绩！   *");
       System.out.println("\t\t*    3-----退出！               *");
       System.out.println("\t\t*********************************");
       try {
          int choice=System.in.read();
          if(choice>=49&&choice<=52)
          {  return choice-48;}
          else   {
             return 0;
          }
       }
       catch(IOException e){ System.out.println("Error!");return 0;}
   }
   public static void createbj() throws Exception {
      Scanner in=new Scanner(System.in);
      System.out.println("请输入班级名：");
      String classname="";
      while(classname.trim().equals("")){
        classname=in.nextLine();
      }

System.out.println("请输入班级人数：");
int rs=in.nextInt();
Myclass myclass=new Myclass(classname,rs);
      myclass.inputData();
      myclass.outputToFile();
      myclass.outputData();
   }
   public static void managebj() throws Exception {
      Scanner in=new Scanner(System.in);
      System.out.println("请输入班级名：");
      String classname="";
      while(classname.trim().equals("")){
        classname=in.nextLine();
      }
      Myclass myclass=new Myclass();
      myclass.inputData(classname);
      myclass.outputData();
   }
}
