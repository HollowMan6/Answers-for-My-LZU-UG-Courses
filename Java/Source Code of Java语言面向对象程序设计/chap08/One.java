class One {   
// synchronized 
void display(int num)  { 
       System.out.println("开始 "+num);
       try {
          Thread.sleep(2000); 
       }   
      catch(InterruptedException e)  {
           System.out.println("中断");
       }
       System.out.println(" 完成");
 }

}
