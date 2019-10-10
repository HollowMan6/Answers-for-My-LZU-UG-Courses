import java.util.Scanner;
public class trycatchdemo
{
  public static void main(String[] args)
  {
     int a=0,b=0,c=0;
     try{
       a=Integer.parseInt(args[0]);
       b=Integer.parseInt(args[1]);
       c=a/b;
       System.out.println("c="+c);
       System.out.println("******正常执行*****");
       System.out.println("实数的除法不产生数学类异常：3.0/0.0="+(3.0/0.0));
       
     }
     catch(ArrayIndexOutOfBoundsException e1)
     {
        System.out.println("此程序要输入两个参数！");
     }
     catch(NumberFormatException e2)
     {
       System.out.println("必须输入数字! ");
     }
     catch(ArithmeticException e3)
     {
       System.out.println("除数不能为0!"); 
       System.out.print("请重新输入除数：");  //演示如何补救
       Scanner in=new Scanner(System.in);
       b=in.nextInt();
       c=a/b;
       System.out.println("c="+c);
     }
  }
}