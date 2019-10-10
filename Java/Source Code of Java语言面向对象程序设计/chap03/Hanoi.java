import java.util.Scanner;
public class Hanoi{
  void moves(char a,char c){
     System.out.println("From "+a+" to "+c);
  }
  void hanoi(int n,char a,char b,char c){
     if(n==1) moves(a,c);
     else
     {
        hanoi(n-1,a,c,b);
        moves(a,c);
        hanoi(n-1,b,a,c);
     }
  }
  public static void main(String[] args)
  {
     int n;
     Scanner keyin=new Scanner(System.in);
     Hanoi test=new Hanoi();
     System.out.print("Enter a number:");
     n=keyin.nextInt();
     test.hanoi(n,'A','B','C');
  }
}