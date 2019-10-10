import java.util.*;
class Families {
   private Integer person[]={1,3,6,8,12};
   public Families(){}
   public Integer[] getPerson(){return person;}
   public void setPerson(Integer[] a){person=a;}
}
class Side {
   String name;
   private TreeSet<Integer> ts;
   private boolean lamp;
   public Side(){
     lamp=false;
     ts=new TreeSet<Integer>();
   }
   public boolean getLamp(){return lamp;}
   public void setLamp(boolean l){lamp=l;}
   public TreeSet getTs(){return ts;}
   public void setTs(TreeSet t){ts=t;}
   public void setName(String n){name=n;}
   public String getName(){return name;}
   public void display(){
      if(!ts.isEmpty()){
         System.out.println(ts);
      }else{
         System.out.println("Empty!");
      }
   }
}
public class PassBridge{
   Side sideA,sideB,currentSide;
   Families family;
   int haveTime;
   public PassBridge(){
      haveTime=30;
      sideA=new Side();
      sideB=new Side();
      sideA.setName("SideA");
      sideB.setName("SideB");
      family=new Families();
      sideA.setLamp(true);
      currentSide=sideA;
      TreeSet<Integer> mylist=sideA.getTs();
      Integer[] persons=family.getPerson();
      for(int i=0;i<persons.length;i++){
         mylist.add(persons[i]);
      }
   }
   public void debug(){
      System.out.print("SideA: ");sideA.display();
      System.out.print("SideB: ");sideB.display();
   }
   public void go(){
     while(true){
       if(haveTime<0) {System.out.println("Fail!!");break;}
       if(currentSide.getName().equals("SideA")){  //SideA
         TreeSet<Integer> alist=currentSide.getTs(),blist=sideB.getTs();
         if(alist.size()!=3){
            Integer first=alist.first();
            blist.add(first);
            alist.remove(first);
            Integer second=alist.first();
            blist.add(second);
            alist.remove(second);
            haveTime=haveTime-second;            
            System.out.println("From SideA "+first+","+second+"----> SideB"); 
         }else{
            Integer first=alist.last();
            blist.add(first);
            alist.remove(first);
            Integer second=alist.last();
            blist.add(second);
            alist.remove(second);
            haveTime=haveTime-first;
            System.out.println("From SideA "+first+","+second+"----> SideB"); 
         }
         sideB.setLamp(true);sideA.setLamp(false);
         currentSide=sideB;
         if(sideA.getTs().isEmpty()) break;
       }else{   //SideB
         TreeSet<Integer> blist=currentSide.getTs(),alist=sideA.getTs();
         Integer first=blist.first();
         alist.add(first);
         blist.remove(first);
         haveTime=haveTime-first;
         System.out.println("From SideB "+first+" ----> SideA"); 
         sideB.setLamp(false);sideA.setLamp(true);
         currentSide=sideA;
       }
     } 
     System.out.println("haveTime="+haveTime);
     if(haveTime>=0)System.out.println("Success!!!");   
   }
   public static void main(String[] args){
      PassBridge myapp=new PassBridge();
      myapp.go();
   }
}