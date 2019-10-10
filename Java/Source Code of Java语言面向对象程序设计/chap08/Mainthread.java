class Mainthread  {
  public static void main(String args[]) {
    Thread t= Thread.currentThread(); 
    System.out.println("当前线程是: "+t); 
    t.setName("MyJavaThread"); 
    System.out.println("当前线程名是: "+t); 
    try { 
      for(int i=0;i<3;i++) { 
        System.out.println(i); Thread.sleep(1500); 
      }
    }
    catch(InterruptedException e) { 
      System.out.println("主线程被中断"); 
    }
    System.out.println("主线程结束");
  }
}
