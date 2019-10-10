import java.util.*;
public class Example5{
    public static void main(String[] args){
        long a,b=0,sum=0;
        Scanner s=new Scanner(System.in);
        System.out.print("输入数字a的值：");
        a=s.nextInt();
        System.out.print("输入相加的项数：");
        int n=s.nextInt();
        int i=0;
        while(i<n){
           b=b+a;
           sum=sum+b;
           a=a*10;
           ++i;
        }
        System.out.println(sum);
    }
}