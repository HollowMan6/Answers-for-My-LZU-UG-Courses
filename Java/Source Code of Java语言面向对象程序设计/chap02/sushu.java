import java.util.Scanner;
class sushu
{
  public static void main(String[] args)
  {
    int m;
    boolean flag=true;
    Scanner key=new Scanner(System.in);
    System.out.print("请输入要测试的数：");
    m=key.nextInt();
    for(int i=2;i<=Math.sqrt(m);i++)
    {
       if(m%i==0) 
       {
         flag=false;
         break;
       }
    }
    if(flag) System.out.println(""+m+"是素数");
    else System.out.println(""+m+"是合数");
  }
}
