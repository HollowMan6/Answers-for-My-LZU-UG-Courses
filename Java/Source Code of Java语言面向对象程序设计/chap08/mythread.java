class MyThread extends Thread
{
   public void run()
   {
      for(int i=0;i<=5;i++)
      {
        try
        {
          System.out.println("exp("+i+")="+Math.exp(i));
          Thread.sleep(1000);
        }catch(InterruptedException e){}
      }
   }
}
class MyThreadTest
{
   public static void main(String[] args)
   {
      MyThread thread1=new MyThread();
      MyThread thread2=new MyThread();
      thread1.start();
      thread2.start();
      try
      {
         for(int k=0;k<=6;k++)
         {
             System.out.println("在主线程中k="+k);
             Thread.sleep(600);
         }
      }catch(InterruptedException e1){}
   }
}