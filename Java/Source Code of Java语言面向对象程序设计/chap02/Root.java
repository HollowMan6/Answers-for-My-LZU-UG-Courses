import java.util.Scanner;
class Root
{
  public static void main(String[] args)
  {
     double a,b,c,dt,x1,x2,sb,xb;
     Scanner keyin=new Scanner(System.in);
     System.out.println("求解一元二次方程的根，请输入三个系数：");
     System.out.print("a=");
     a=keyin.nextDouble();
     System.out.print("b=");
     b=keyin.nextDouble();
     System.out.print("c=");
     c=keyin.nextDouble();

     dt=b*b-4*a*c;
     if(dt>=0)
     {
        x1=(-b+Math.sqrt(dt))/(2*a);
        x2=(-b-Math.sqrt(dt))/(2*a);
        System.out.println("x1="+x1);
        System.out.println("x2="+x2);
     }
     else
     {
        sb=-b/(2*a);
        xb=Math.sqrt(-dt)/(2*a);
        System.out.println("x1="+sb+"+"+xb+"i");
        System.out.println("x2="+sb+"-"+xb+"i");
     }
 
  }
}