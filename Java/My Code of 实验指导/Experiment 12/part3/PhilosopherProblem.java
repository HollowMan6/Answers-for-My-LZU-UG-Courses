class ChopStick {
 boolean available;
 ChopStick() {  available=true; }
 public synchronized void takeup() {
  while(!available) {
    try {
  	   wait(); System.out.println("哲学家等待另一根筷子");  
	  }	catch(InterruptedException e) { }
  }
  available=false;
 }
 public synchronized void putdown() {
   available=true;
   notify();
 }
}
class Philosopher extends Thread{
  ChopStick left,right;
  int philo_num;
  Philosopher(int num,ChopStick c1,ChopStick c2){
    philo_num=num;
    left=c1;
    right=c2;
  }
  public void eat(){
    left.takeup();
    right.takeup();
    System.out.println("哲学家 "+(philo_num+1)+" 正在进餐");
  }
  public void think(){
    left.putdown();
    right.putdown();
    System.out.println("哲学家 "+(philo_num+1)+" 正在思考");
  }
  public void run(){
    while(true){
       eat();
       try{ sleep(1000);}
       catch(InterruptedException e){}
       think();
       try{ sleep(1000);}
       catch(InterruptedException e){}
    }
  }
}
public class PhilosopherProblem {
  static ChopStick[] chopsticks=new ChopStick[5];
  static Philosopher[] philos=new Philosopher[5];
  public static void main(String args[]) {
    for(int i=0;i<5;i++){
        chopsticks[i]=new ChopStick();
    }
    for(int i=0;i<5;i++){
       philos[i]=new Philosopher(i,chopsticks[i],chopsticks[(i+1)%5]);
    }
    for(int i=0;i<5;i++)
       philos[i].start();
  }
}

