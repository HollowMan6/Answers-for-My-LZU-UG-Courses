class ThreadSin extends Thread
{
   private int startjd,endjd;
   public ThreadSin(){startjd=0;endjd=90;}
   public ThreadSin(int x,int y){startjd=x;endjd=y;}
   public int getStartjd(){return startjd;}
   public void setStartjd(int a){startjd=a;}
   public int getEndjd(){return endjd;}
   public void setEndjd(int b){endjd=b;}


   public void run()
   {
     try
     {
      for(int i=startjd;i<endjd;i++)
      {
         System.out.print("sin("+i+")="+Math.sin(i*3.14159/180.0));
         System.out.println("  我在下载文件第"+i+"块");
         Thread.sleep(1);
      }
     }catch(Exception e){System.out.println("线程休息被打断！");}     
   }

}