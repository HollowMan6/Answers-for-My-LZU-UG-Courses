import java.util.*;
public class BankTest{
   public static void main(String[] args){
      Bank bk=new Bank();
      boolean con=true;
      while(con){
        try{
          Scanner keyboard=new Scanner(System.in);
          int opt;
          opt=menu();
          switch(opt){
            case 1:
              Account a1=getDetails();
              bk.addAccount(a1);
              break;
            case 2:
              System.out.print("Enter a ID number:");
              String n1=keyboard.nextLine();
              System.out.print("Enter how much do you save:");
              double d1=keyboard.nextDouble();
              double db1=bk.save(d1,n1);
            
              System.out.println("The balance is: "+db1);
              break;
            case 3:
              System.out.print("Enter a ID number:");
              String n2=keyboard.nextLine();
              System.out.print("Enter how much do you need:");
              double d2=keyboard.nextDouble();
              double db2=bk.get(d2,n2);
              System.out.println("The balance is:"+db2);
              break;
            case 4:
              System.out.println("Enter a ID number:");
              String n3=keyboard.nextLine();
              System.out.print("Enter how much do you get:");
              double d3=keyboard.nextDouble();
              double db3=bk.consume(d3,n3);
              System.out.println("The balance is:"+db3);
              break;
            case 5:
              System.out.print("Enter a ID number:");
              String n4=keyboard.nextLine();
              bk.deleteAccount(n4);
              break;
            case 6:
              System.out.print("Enter a ID number:");
              String n5=keyboard.nextLine();
              double db4=bk.checkBalance(n5);
              System.out.println(n5+" the balance is:"+db4);
              break;
            case 7:
              System.out.print("Enter a ID number:");
              String n6=keyboard.nextLine();
              bk.showIndividualAccount(n6);
              break;
            case 8:
              bk.showAccount();
              break;
            case 9:
              System.out.print("Enter a ID number:");
              String n7=keyboard.nextLine();
              int x=bk.showIndividualOverdraftTime(n7);
              System.out.println("The overdraft time is:"+x);
              break;
            case 10:
              bk.checkOverdraftAccount();
              break;
            case 11:
              double db5=bk.statTotalBalance();
              System.out.println("The total of balance is: "+db5);
              break;
            case 12:con=false;break;
            default:System.out.println("Invalid input");
          }
        }catch(Exception e){
          System.out.println(e.getMessage());
        }
      }
   }
   static int menu(){
      int cho;
      Scanner keyboard=new Scanner(System.in);
      System.out.println("-------------------------Bank Account Operation Menu---------------------");
      System.out.println("  1: Add New Account");
      System.out.println("  2: Save Money");
      System.out.println("  3: Withdraw Money");
      System.out.println("  4: Consume");
      System.out.println("  5: Delete Account");
      System.out.println("  6: Check Balance");
      System.out.println("  7: Display Individual Account");
      System.out.println("  8: Display Total Account");
      System.out.println("  9: Check Individual Overdraft Time");
      System.out.println(" 10: Check Overdraft Account");
      System.out.println(" 11: Stat Bank Balance");
      System.out.println(" 12: Exit");
      System.out.println("--------------------------------------------------------------------------");
      System.out.print("Enter your Choice:");
      cho=keyboard.nextInt();
      return cho;
   }
   static Account getDetails() throws Exception{
      System.out.print("Name:");
      Scanner keyboard=new Scanner(System.in);
      String n=keyboard.nextLine();
      System.out.print("ID Number:");
      String idNum=keyboard.nextLine();
      System.out.print("Overdraft Limited:");
      double over=keyboard.nextDouble();
      Account a=new Account(idNum,n,over);
      return a;      
   }
}