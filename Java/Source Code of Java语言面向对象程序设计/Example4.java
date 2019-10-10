import java.util.*;
public class Example4{
    public static void main(String[] args){
        int a,b,m;
        Scanner s=new Scanner(System.in);
        System.out.print("键入一个整数：");
        a=s.nextInt();
        System.out.print("输入另一个整数：");
        b=s.nextInt();
        deff cd=new deff();
        m=cd.deff(a,b);
        int n=a*b/m;
        System.out.println("最大公约数："+m);
        System.out.println("最小公倍数："+n);
    }
}
class deff{
    public int deff(int x,int y){
       int t;
       if(x<y){
           t=x;
           x=y;
           y=t;
       }
       while(y!=0){
           if(x==y) return x;
           else{
               int k=x%y;
               x=y;
               y=k;
           }
       }
       return x;
    }
}