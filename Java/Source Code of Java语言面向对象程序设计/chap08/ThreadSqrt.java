class ThreadSqrt implements Runnable
{
   private int a,b;
   public ThreadSqrt(){a=0;b=100;}
   public ThreadSqrt(int x,int y){a=x;b=y;}
   public int getA(){return a;}
   public int getB(){return b;}
   public void setA(int x) {a=x;}
   public void setB(int y){b=y;}

   public void run()
   {
      try
      {
         for(int i=a;i<b;i++)
         {
            System.out.println("sqrt("+i+")="+Math.sqrt(i)+"我在打印");
            Thread.sleep(1);
         }
      }catch(Exception e){System.out.println("ok!");}

   }
}