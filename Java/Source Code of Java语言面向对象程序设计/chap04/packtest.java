package mypg;
public class packtest
{
   public void display()
   {
      System.out.println("Math.sqrt(2.0)="+Math.sqrt(2.0));
   }
}
class B
{
   public static void main(String [] args)
   {
      packtest a=new packtest();
      a.display();
   }
}