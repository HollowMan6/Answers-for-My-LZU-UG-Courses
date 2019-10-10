import java.util.Scanner;
public class fushu
{
     private double realpart;
     private double imaginarypart;
     fushu(){realpart=0;imaginarypart=0;}
     fushu(double s,double x){realpart=s;imaginarypart=x;}
     public double getReal(){return realpart;}
     public double getImag(){return imaginarypart;}
     public boolean equals(fushu another)
     {
        return (this.realpart==another.realpart&&this.imaginarypart==another.imaginarypart);
     }
     public String toString()
     {
           String str=""+realpart;
           if(imaginarypart<0.0) str=str+imaginarypart+"i";
           else str=str+"+"+imaginarypart+"i";
           return str;
     }
     public void input()
     {
       Scanner keyin=new Scanner(System.in);
       System.out.print("real:");
       realpart=keyin.nextDouble();
       System.out.print("imaginary:");
       imaginarypart=keyin.nextDouble();
     }
     public void display()
     {
        System.out.println(toString());
     }
     public static void main(String[] args)
     {
        fushu m1=new fushu(3.4,8.0);
        fushu m2=new fushu(3.4,8.0);
        System.out.println("m1==m2="+(m1==m2));
        System.out.println("m1.equals(m2)="+m1.equals(m2));
        fushu m3=new fushu(4.4,-8.9);
        System.out.println("m1="+m1.display());
        System.out.println("m3="+m3);
        m2.input();
        m2.display();
     }
}
