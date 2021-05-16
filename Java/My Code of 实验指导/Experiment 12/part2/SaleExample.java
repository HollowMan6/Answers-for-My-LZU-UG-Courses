class BreadSeller         //负责卖面包的类，一块面包5元钱。
{
   int fiveNum=1,tenNum=0,twentyNum=0; // 面包师傅现有钱各面额的数量
   public synchronized void  sellBread(int receiveMoney,int buyNumber)
   { 
       if(receiveMoney==5)
       {
          fiveNum=fiveNum+1; 
          System.out.printf("\n%s给我5元钱，这是您的1块面包",Thread.currentThread().getName()); 
       }
       else if(receiveMoney==10&&buyNumber==2) 
       {
           tenNum=tenNum+1; 
           System.out.printf("\n%s给我10元钱，这是您的2块面包",
                             Thread.currentThread().getName()); 
       }
       else if(receiveMoney==10&&buyNumber==1)           
        {  
          while(fiveNum<1)
            { 
                try { 
                       System.out.printf("\n%30s靠边等",Thread.currentThread().getName());
                       wait();    //如果线程占有CPU期间执行了wait,就进入等待状态。
                       System.out.printf("\n%30s结束等待\n",Thread.currentThread().getName());
                    }
               catch(InterruptedException e)
                    {
                    }
            }
           fiveNum=fiveNum-1;
           tenNum=tenNum+1;
           System.out.printf("\n%s给我10元钱，找您5元，这是您的1块面包",Thread.currentThread().getName());
        }
       else if(receiveMoney==20&&buyNumber==1)           
        {  
          while((fiveNum<1||tenNum<1)&& !(fiveNum>3))
            { 
                try { 
                       System.out.printf("\n%30s靠边等",Thread.currentThread().getName());
                       wait();    //如果线程占有CPU期间执行了wait,就进入中断状态。
                       System.out.printf("\n%30s结束等待",Thread.currentThread().getName());
                    }
               catch(InterruptedException e)
                    {
                    }
            }
           if(fiveNum>3)
           {
           fiveNum=fiveNum-3;
           twentyNum=twentyNum+1;
           System.out.printf("\n%s给20元钱，找您三张5元，这是您的1块面包",
                             Thread.currentThread().getName());
           }
           else
           { 
           fiveNum=fiveNum-1;
           tenNum=tenNum-1;
           twentyNum=twentyNum+1;
           System.out.printf("\n%s给20元钱，找您一张5元和一张10元，这是您的1块面包",
                             Thread.currentThread().getName());
        }}
       else if(receiveMoney==20&&buyNumber==2) 
        {
            while(tenNum<1&&fiveNum<2)
            {
                try { 
                       System.out.printf("\n%30s靠边等\n",Thread.currentThread().getName());
                       wait();    //如果线程占有CPU期间执行了wait,就进入中断状态。
                       System.out.printf("\n%30s结束等待",Thread.currentThread().getName());
                    }
               catch(InterruptedException e)
                    {
                    }
            }
            if(fiveNum<2)
            {
            tenNum=tenNum-1;
            twentyNum=twentyNum+1;
            System.out.printf("\n%s给20元钱，找您一张10元，这是您的2块面包",
                             Thread.currentThread().getName());
        } 
          }
          else
          {
            fiveNum=fiveNum-2;
            twentyNum=twentyNum+1;
            System.out.printf("\n%s给20元钱，找您两张5元，这是您的2块面包",
                             Thread.currentThread().getName());
          }
       notifyAll();
   }
}
class Breadshop implements Runnable            //实现Runnable接口的类（电影院）。
{  
   Thread zhao,qian,sun,li,zhou;             //电影院中买票的线程。
   BreadSeller seller;                      //电影院的售票员。
  Breadshop()
   {
      zhao=new Thread(this);
      qian=new Thread(this);
      sun=new Thread(this);
      li=new Thread(this);
      zhou=new Thread(this);
      zhao.setName("赵");
      qian.setName("钱");
      sun.setName("孙");
      li.setName("李");
      zhou.setName("周");
      seller=new BreadSeller();
   } 
   public void run()
   {
       if(Thread.currentThread()==zhao)
       {
          seller.sellBread(20,2);
       }
       else if(Thread.currentThread()==qian)
       {
          seller.sellBread(20,1);
       }
       else if(Thread.currentThread()==sun)
       {
          seller.sellBread(10,1);
       }
       else if(Thread.currentThread()==li)
       {
          seller.sellBread(10,2);
       }
       else if(Thread.currentThread()==zhou)
       {
          seller.sellBread(5,1);
       }
   }
}
public class SaleExample
{
   public static void main(String args[])
    {
        Breadshop myshop=new Breadshop();      
        myshop.zhao.start();
        try
          {
              Thread.sleep(1000); 
          } 
          catch(InterruptedException e)
          {
          }
        myshop.qian.start();
        try 
          {
             Thread.sleep(1000); 
          } 
          catch(InterruptedException e)
          {
          }
        myshop.sun.start();
        try 
           {
                 Thread.sleep(1000); 
           } 
          catch(InterruptedException e)
           {
           }
        myshop.li.start();
        try 
           {
                 Thread.sleep(1000); 
           } 
        catch(InterruptedException e)
           {
           } 
        myshop.zhou.start();
    }
}
