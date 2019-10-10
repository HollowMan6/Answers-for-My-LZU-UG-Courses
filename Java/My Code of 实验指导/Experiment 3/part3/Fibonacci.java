public class Fibonacci {
    public static void main(String[] args) {
        int n1=1,temp,sum=1;
        System.out.print("  1");
        for(int i=1;i<20;i++){
            System.out.print("  "+sum);
            temp=n1+sum;
            n1=sum;
            sum=temp;
        }
    }
}