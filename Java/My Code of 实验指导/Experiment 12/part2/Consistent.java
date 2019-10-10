public class Consistent{
    static boolean started=false;
    public synchronized static void setStarted(){
        started=true;
    }
    public synchronized static boolean getStarted(){
        return started;
    }
    public static void main(String[] args) {
        Thread thread1=new Thread(new Runnable(){
            public void run(){
                try{
                    Thread.sleep(3000);
                }catch(InterruptedException e){

                }
                setStarted();
                System.out.println("started set to true");
            }
        });
        thread1.start();
        while(!started){
        }
        System.out.println("Wait 3 seconds and exit");
    }
}