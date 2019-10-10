public class Synch {
   public static void main(String args[]) throws InterruptedException{
       One one=new One();
       int digit=10;

       Two s1=new Two(one,digit++);
       Two s2=new Two(one,digit++);
       Two s3=new Two(one,digit++);

       s1.t.join();
       s2.t.join();
       s3.t.join(); 
  }
}
