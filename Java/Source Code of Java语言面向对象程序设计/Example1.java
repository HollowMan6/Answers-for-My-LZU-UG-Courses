//这是一个斐波那契数列问题
public class Example1{
    public static void main(String[] args){
       System.out.println("第1个月的兔子对数：1");
       System.out.println("第2个月的兔子对数：1");
       int f1=1,f2=1,f,M=24;
       for(int i=3;i<=M;i++){
          f=f1+f2;
          System.out.println("第"+i+"个月的兔子对数："+f);
          f1=f2;
          f2=f;
          f=f2;
       }
    }
}