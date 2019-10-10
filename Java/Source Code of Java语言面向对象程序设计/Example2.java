public class Example2 {
   public static void main(String[] args){
      int b1,b2,b3;
      for(int m=101;m<1000;m++){
         b3=m/100;
         b2=m%100/10;
         b1=m%10;
         if((b3*b3*b3+b2*b2*b2+b1*b1*b1)==m){
             System.out.println(m+"是一个水仙花数");
         }
      }
   }
}