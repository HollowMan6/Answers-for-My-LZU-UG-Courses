class Two implements Runnable 
{
  int number; 

   One one;
  
   Thread t;
  
public Two(One one_num, int n)   
{
  one=one_num;  
   number=n;
     
   t=new Thread(this);

   t.start();
   }

   public void run() {

     synchronized(one){
        one.display(number);
     }
   }

}


