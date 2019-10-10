public class RecursionDemo {
   public long fac(int n)
   {
      if(n==0)   //终止条件
        return 1;
      else
        return(n*fac(n-1)); //递归公式
   }
   public long fbnc(int n)
   {
      if(n==0||n==1)   //终止条件
        return 1;
      else
        return(fbnc(n-1)+fbnc(n-2));  //递归公式
   }
   public static void main(String[] args)
   {
      RecursionDemo rcs=new RecursionDemo();  //调用默认构造方法创建对象
      System.out.println("5!="+rcs.fac(5));   //调用对象的fac()方法
      System.out.println("f(20)="+rcs.fbnc(20));  //调用对象的fbnc()方法
   }
}