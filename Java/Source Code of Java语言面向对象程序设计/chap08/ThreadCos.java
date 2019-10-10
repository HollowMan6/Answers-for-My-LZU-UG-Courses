class ThreadCos extends Thread
{
   private int jd;
   public ThreadCos(){jd=0;}
   public ThreadCos(int x){jd=x;}
   public int getJd(){return jd;}
   public void setJd(int a){jd=a;}

   public void run()
   {
     try
     {
       for(int i=jd;i<=90;i++)
       {
         System.out.println("cos("+i+")="+Math.cos(i*3.14159/180.0)+" 我在上网聊天");
         Thread.sleep(1);
       }
     }catch(Exception e){System.out.println("线程休息被打断！");}     
   }

}