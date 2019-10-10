class JoinTest  implements Runnable {
    Thread threadA,threadB;
    String msg[ ]={"今天晚上,","大家不要","回去的太早,","还有工作","需要大家做!"};
    JoinTest() {
       threadA=new Thread(this);
       threadB=new Thread(this);
       threadB.setName("主任");
    }
   public void run() {
       if(Thread.currentThread()==threadA)  {
             System.out.println("我等"+threadB.getName()+"说完再说话");
             try{
                  threadB.join();      //线程threadA开始等待threadB结束.
                }  catch(InterruptedException e) {}
             System.out.printf("\n我开始说话:\'我明白你的意思了,谢谢\'");
         }else if(Thread.currentThread()==threadB) { 
            System.out.println(threadB.getName()+"说:");
            for(int i=0;i<msg.length;i++) {
                 System.out.print(msg[i]) ;
                  try {   threadB.sleep(1000);    
                  }catch(InterruptedException e) { }
             } 
         }
    }    
}
class Example {
    public static void main(String args[ ]) {
        JoinTest  a=new JoinTest ();
        a.threadA.start();
        a.threadB.start();
   }
}
