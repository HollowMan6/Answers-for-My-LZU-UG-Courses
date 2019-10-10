import java.util.Scanner;     //要导入Scanner类
class Scannertest {
  public static void main(String[] args)
  {
     double d;
     float f;
     int i;
     long l;
     boolean b;
     Scanner keyin=new Scanner(System.in);
     System.out.println("请输入一个双精度数：");
     d=keyin.nextDouble();
     System.out.println("请输入一个单精度数：");
     f=keyin.nextFloat();
     System.out.println("请输入一个整数：");
     i=keyin.nextInt();
     System.out.println("请输入一个长整数：");
     l=keyin.nextLong();
     System.out.println("请输入一个逻辑值：");
     b=keyin.nextBoolean();
     System.out.println("你输入的数分别是：");
     System.out.print("d="+d+"\nf="+f+"\ni="+i+"\nl="+l+"\nb="+b);
  }
}