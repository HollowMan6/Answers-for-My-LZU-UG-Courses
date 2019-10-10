interface Computable
{
   int M=10;
   int f(int x);
   public abstract int g(int x,int y);
}
class A implements Computable
{
  public int f(int x)
  { 
    return M+2*x;
  }
  public int g(int x,int y){return M*(x+y);}
}
class B implements Computable
{
  public int f(int x){return x*x*x;}
  public int g(int x,int y){return x*y*M;}
}
public class interfacedemo {
  public static void main(String[] args){
    A a=new A();
    B b=new B();
    System.out.println(a.M);
    System.out.println(""+a.f(20)+", "+a.g(12,2));
    System.out.println(b.M);
    System.out.println(""+b.f(20)+", "+b.g(12,2));
  }
}