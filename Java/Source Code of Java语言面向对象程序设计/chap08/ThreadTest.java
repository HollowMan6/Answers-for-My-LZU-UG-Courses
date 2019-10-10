public class ThreadTest
{
   public static void main(String[] args)
   {
      Thread t1=new ThreadSin(0,30);
      Thread t2=new ThreadCos(60);
      Thread t3=new Thread(new ThreadSqrt(20,50));
      System.out.println("子线程开始执行！");

      t3.setPriority(10);
      t1.setPriority(1);

      t1.start();
      t2.start();
      t3.start();
   }
}