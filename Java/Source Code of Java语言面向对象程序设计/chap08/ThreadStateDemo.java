class ThreadStateDemo extends Thread {
	Thread t;
	public ThreadStateDemo() {
		t=new Thread(this);
		System.out.println ("线程 t 为新建！");
		t.start();
		System.out.println ("线程 t 为就绪！");

	}
	public void run()  {
	  try {
		System.out.println ("线程 t 在运行！");
                System.out.println("线程t将要休息！");
		t.sleep(2000);
		System.out.println("线程 t 在短时间睡眠后重新运行！");
                System.out.println("线程t将要死亡");
	  } catch (InterruptedException IE) {
		System.out.println("休息线程被中断");
	  }
	}
public static void main(String args[]) {
		new ThreadStateDemo();
          
  }
}
