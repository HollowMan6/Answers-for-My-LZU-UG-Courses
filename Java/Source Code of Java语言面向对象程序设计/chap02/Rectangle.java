import java.util.Scanner;
class Rectangle
{
   public static void main(String[] args)
   {
      double w,l,S,s;
      Scanner keyin=new Scanner(System.in);
      System.out.print("请输入长方形的长:");
      l=keyin.nextDouble();
      System.out.print("请输入长方形的宽:");
      w=keyin.nextDouble();
      S=w*l;    //计算面积
      s=2*(w+l);  //计算周长
      System.out.println("此长方形的面积:"+S+"\n此长方形的周长:"+s);
   }
}