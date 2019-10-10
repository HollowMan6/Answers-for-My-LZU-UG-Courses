public class Reptile extends Thread{
    public void run(){
        for(int a=0;a<50;a++){
            System.out.println(currentThread().getName()+": I am crawling "+a+" step!");
            try{
                sleep(100);
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }
    public static void main(String[] args) {
        Reptile reptile1=new Reptile();
        reptile1.setName("Reptile1");
        Reptile reptile2 = new Reptile();
        reptile2.setName("Reptile2");
        reptile1.start();
        reptile2.start();
    }
}