import java.util.Scanner;
class lingxing
{
   public static void main(String args[])
   {
      int row;
      Scanner keyin=new Scanner(System.in);
      System.out.print("请输入菱形的行数:");
      row=keyin.nextInt();

      for(int i=1;i<=row/2+1;i++)
      {
         repeat(' ',row/2+1-i);
         repeat('*',2*i-1);
         printaline('*',2*i-1);
         System.out.println();
      }
      for(int i=row/2;i>=1;i--)
      {
         repeat(' ',row/2+1-i);
//         repeat('*',2*i-1);
         printaline('*',2*i-1);
         System.out.println();
      }

   }
   public  static void repeat(char ch,int m)
   {
      for(int i=1;i<=m;i++) System.out.print(ch);
   }
   public static void printaline(char ch,int i){
      System.out.print(ch);
      if(i==1) return;
      else {
          repeat(' ',i-1);
          System.out.print(ch);
          return;
      }       
   }
}