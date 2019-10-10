class mythreadtest {
public static void main(String args[]) {
     Thread t= Thread.currentThread(); 
     System.out.println("主线程是: "+t);
     MyThread1 ex = new MyThread1();
     Thread myth1=new Thread(new Xyz());
     MyThread myth2=new MyThread("线程测试",400);
     ex.start();
     myth1.start();
     myth2.start();
     try
     {
        for(int i=0;i<5;i++)
        { System.out.println("i="+i); Thread.sleep(500);}
     }
     catch(InterruptedException e){}   
  }
}
/*******************************/
class MyThread1 extends Thread {
   public void run() {
     System.out.println("子线程是："+this);
     try
     {
        for(int j=0;j<=6;j++)
        { System.out.println(this+"\tj="+j); Thread.sleep(800);}
     }catch(InterruptedException e){}
  }
}
/********************/
 class Xyz implements Runnable {
 int i;
 public void run() 
 {
   i = 0;
   while (true)
   {
      System.out.println("Hello " + i++);
      if ( i == 50 ) 
      {
         break;
      } 
   } 
 } 
}
/*****************************/
class MyThread extends  Thread
{
     String s;
     int m,count=0;
     MyThread(String ss,int mm){s=ss;m=mm;}
     public void run()
     {
     try
     {
        while(true)
        {
             System.out.print(s);sleep(m);
             if(++count>=20)break;
        }
     }
     catch(InterruptedException e){return;}
   }
}