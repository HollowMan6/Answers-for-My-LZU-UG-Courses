import java.util.Scanner;
class iftest3
{
  public static void main(String[] args)
  {
     double y,x;
     Scanner keyin=new Scanner(System.in);
     System.out.print("请输入x的值：");
     x=keyin.nextDouble();
     if(x>0) y=2*x;
     else 
       if(x==0) y=2+Math.cos(x);
       else y=x*x+1;
     System.out.println("y="+y);
  }
}