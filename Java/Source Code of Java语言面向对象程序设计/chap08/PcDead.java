class A{
    synchronized void afirst(B b){
        String name=Thread.currentThread().getName();
        System.out.println(name + " enter A.afirst()");
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println(name + " trying to call B.last()");
        
        b.last();
    }
    
    synchronized void last(){
        System.out.println("Inside A.last()");
    }
}

class B{
    synchronized void bfirst(A a){
        String name=Thread.currentThread().getName();
        System.out.println(name + " enter B.bfirst()");
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println(name + " trying to call A.last()");
        
        a.last();
    }
    
    synchronized void last(){
        System.out.println("Inside B.last()");
    }
}

public class PcDead implements Runnable{

    A a = new A();
    B b = new B();
    
    PcDead(){
        Thread.currentThread().setName("Main thread:");
        Thread t = new Thread(this,"Sub  thread:");
        t.start();
        
        a.afirst(b);
        System.out.println("Back in mian thread");
    }
    
    public void run(){
        b.bfirst(a);
        System.out.println("Back in sub  thread");
    }
    
    public static void main(String[] args) {
        new PcDead();
    }
}