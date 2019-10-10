import java.util.*;
class Pirate {
  private String name;
  private int[] schemes;
  private int index;
  public Pirate(int t,int i){
     name="unknown";
     index=i;
     schemes=makeSchemes(t);
  }
  public String getName(){return name;}
  public void setName(String s){name=s;}
  public int getIndex(){return index;}
  public int[] getSchemes(){return schemes;}

  public int handvote(int table[]){
    return myhandvote(table,index);
  }
  private int myhandvote(int[] t,int i)
  {
    if(t[i]==0) return 0;
    else return 1;
  }
  public int[] makeSchemes(int t){
        int vote=0;
        schemes=new int[t-index];
        do{
          int needvote=(int)Math.ceil((float)(t-index)/2)-1;
          schemes[0]=100-needvote;
          for(int i=1;i<schemes.length;i++){
            schemes[i]=(i+1)%2;
          }
          for(int i=0;i<schemes.length;i++){
            vote=vote+myhandvote(schemes,i);  //自己模拟其他编号的海盗投票
          }
        }while(!(2*vote>=t/2));
        return schemes;
  }
}
class Judge
{
   int[] allot;
   Pirate[] pirates;
   public Judge(Pirate[] pirates,int[] a)
   {
      this.pirates=pirates;
      allot=a; 
   }
   public int[] getAllot(){return allot;}
   public void setAllot(int[] a){ allot=a;}

   public Pirate[] getPirates(){return pirates;}
   public void setPirates(Pirate[] p){pirates=p;}

   public boolean evaluate()
   {
      int vote=0;
      for(int i=0;i<pirates.length;i++)
      {
         vote+=pirates[i].handvote(allot); 
      }
      if(2*vote>=pirates.length) return true;
      else return false;
   }
}
class MaxInterest{
   public static void main(String[] args){
      int piratecounts=5;
      Pirate[] pirates=new Pirate[piratecounts];
      for(int i=0;i<piratecounts;i++){
         pirates[i]=new Pirate(piratecounts,i);
         pirates[i].setName("name"+i);
      }
      int[] table=pirates[0].getSchemes();
      Judge ajudge=new Judge(pirates,table);
      if(ajudge.evaluate()){
         int[] scheme=ajudge.getAllot();
         for(int i=0;i<scheme.length;i++) System.out.print("  "+scheme[i]);
      }
      System.out.print("\n************************************");
      for(int k=0;k<piratecounts;k++){
        System.out.print("\n"+k+":");
        int[] temp=pirates[k].getSchemes();
        for(int i=0;i<temp.length;i++) System.out.print("  "+temp[i]);
      }
   }
}
