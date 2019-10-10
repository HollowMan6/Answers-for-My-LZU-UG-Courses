import java.util.*;
public class Bank{
  private Vector bankAccount;
  public Bank(){
    bankAccount=new Vector();
  }
  public void addAccount(Account a) throws Exception{
    boolean found=checkID(a);
    if(!found){
      int j=0;boolean posFnd=false;
      while(j<bankAccount.size()&&!posFnd){
        Account ac=(Account)bankAccount.elementAt(j);
        if(a.compareID(ac)>0)
          j++;
        else
          posFnd=true;
      }
      bankAccount.insertElementAt(a,j);
    }else
       throw new Exception(a.getID()+" card is duplicate");
    }
    private boolean checkID(Account a){
       boolean fnd=false;int j=0;
       Account a1;
       while(j<bankAccount.size()&!fnd){
          a1=(Account)bankAccount.elementAt(j);
          if(a1.equalsID(a))
             fnd=true;
          else
             j++;
       }
       return fnd;
   }
   private Account searchAccount(String idNum){
      Account a1=null; boolean fnd=false;
      for(int j=0;j<bankAccount.size()&&!fnd;j++){  
         a1=(Account)bankAccount.elementAt(j); 
         if(idNum.equals(a1.getID())) fnd=true;
      }
      if(fnd) return a1;
      else return null;
   }
   public double save(double m,String idNum) throws Exception{
      Account a;
      a=searchAccount(idNum);
      if(a!=null) return a.saveMoney(m);
      else throw new Exception(idNum+" card is not exist");
   }
   public double get(double m,String idNum) throws Exception{
      Account a;
      a=searchAccount(idNum);
      if(a!=null) return a.getMoney(m);
      else throw new Exception(idNum+" card is not exist");
   }
   public double consume(double m,String idNum) throws Exception{
      Account a;
      a=searchAccount(idNum);
      if(a!=null) return a.consumed(m);
      else throw new Exception(idNum+" card is not exist");
   }
   public void deleteAccount(String idNum) throws Exception{
     Account a;
     a=searchAccount(idNum);
     if(a!=null){
       if(a.getBalance()!=0)      
          throw new Exception(idNum+" card has balance so can not delete");
       else{
          boolean fnd=false; Account a1=null;
          for(int j=0;j<bankAccount.size()&!fnd;j++){
             a1=(Account)bankAccount.elementAt(j);
             if(idNum.equals(a1.getID())){
                fnd=true;
                bankAccount.removeElementAt(j);
             }
          }
       }
     }else throw new Exception(idNum+" card is not exist");
   }
   public double checkBalance(String idNum) throws Exception{
      Account a;
      a=searchAccount(idNum);
      if(a!=null) return a.getBalance();
      else throw new Exception(idNum+" card is not exist");
   }
   public void showIndividualAccount(String idNum) throws Exception{
      Account a;
      a=searchAccount(idNum);
      if(a!=null) a.display();
      else throw new Exception(idNum+" card is not exist");
   }
   public void showAccount() throws Exception{
      System.out.println("Account List:");
      Account a1;
      for(int j=0;j<bankAccount.size();j++){
         a1=(Account)bankAccount.elementAt(j);
         a1.display();
      }
         System.out.print("Enter a character to continue:");
         char c=(char)System.in.read();
   }
   public int showIndividualOverdraftTime(String idNum)throws Exception{
         Account a;
         a=searchAccount(idNum);
         if(a!=null)
            return a.getOverdraftNum();
         else
            throw new Exception(idNum+" card is not exist");
   }
   public void checkOverdraftAccount(){
         Account a1=null;
         for(int j=0;j<bankAccount.size();j++){
            a1=(Account)bankAccount.elementAt(j);
            if(a1.getBalance()<0) a1.display();
         }
   }
   public double statTotalBalance()throws Exception{
         Account a1=null;
         double d=0.0;
         for(int j=0;j<bankAccount.size();j++){
           a1=(Account)bankAccount.elementAt(j);
           d=d+a1.getBalance();
         }
         return d;
   }
}