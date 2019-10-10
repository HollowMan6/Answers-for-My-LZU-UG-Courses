import java.util.*;
import java.io.*;
class Missionary {
  private String name;
  public Missionary(String n){name=n;}
  public String getName(){return name;}
}
class Cannibal {
  private String name;
  public Cannibal(String n){name=n;}
  public String getName(){return name;} 
}
class Riverside {
  private String name;
  private ArrayList missList;
  private ArrayList cannList;
  public Riverside(String n){
    name=n;
    missList=new ArrayList();
    cannList=new ArrayList();
  }
  public String getName(){
    return name;
  }
  public void setName(String n){
    name=n;
  }
  public boolean isSafe(){
    if(missList.size()==0) return true;
    return missList.size()>=cannList.size();
  }
  public ArrayList getMissList(){return missList;}
  public ArrayList getCannList(){return cannList;}
  public void display(){
    for(int i=0;i<missList.size();i++){
      System.out.print("  "+((Missionary)missList.get(i)).getName());
    }
    for(int i=0;i<cannList.size();i++){
      System.out.print("  "+((Cannibal)cannList.get(i)).getName());
    } 
    System.out.println(); 
  }
}
class Boat {
  private ArrayList list;
  private Riverside currentSide,otherSide;
  public ArrayList getList(){return list;}
  public Riverside getCurrentSide(){return currentSide;}
  public void setCurrentSide(Riverside r){currentSide=r;}  
  public Riverside getOtherSide(){return otherSide;}
  public void setOtherSide(Riverside r){otherSide=r;}  
  public Boat(){
    list=new ArrayList();
  }
  public void go(){
    Object oref;
    if(list.isEmpty()||list.size()>2) {
       System.out.println("Cannot Go!");
    }else{
    System.out.println("Boat from "+currentSide.getName()+" to "+otherSide.getName()+". Loading:");
    for(int i=0;i<list.size();i++){
       oref=list.get(i);
       if(oref instanceof Missionary){
          System.out.println(((Missionary)oref).getName());
          otherSide.getMissList().add(oref);
       }else{
          System.out.println(((Cannibal)oref).getName());
          otherSide.getCannList().add(oref);
       }
    }
    list.clear();
    Riverside tmp=currentSide;   //The boat arrived at the otherside
    currentSide=otherSide;
    otherSide=tmp;
    System.out.println("===============================");
    }
  }
  public void display(){
    Object oref;
    for(int i=0;i<list.size();i++){
      oref=list.get(i);
      if(oref instanceof Missionary)
         System.out.print("  "+((Missionary)oref).getName());
      else
         System.out.print("  "+((Cannibal)oref).getName());
    }
  }
}
public class MissionaryPassRiver {
  Riverside rs1,rs2;
  Boat boat;
  public MissionaryPassRiver(){
    rs1=new Riverside("Riverside 1");
    rs2=new Riverside("Riverside 2");
    boat=new Boat();
    boat.setCurrentSide(rs1);boat.setOtherSide(rs2);
    for(int i=0;i<3;i++){
      rs1.getMissList().add(new Missionary("Missionary "+i));
      rs1.getCannList().add(new Cannibal("Cannibal "+i));
    }
  }
  public boolean checksafe(){
    Object oref;
   
    if(boat.getCurrentSide().isSafe()){
      int mnum=boat.getOtherSide().getMissList().size();
      int cnum=boat.getOtherSide().getCannList().size();

      for(int i=0;i<boat.getList().size();i++){
         oref=boat.getList().get(i);
         if(oref instanceof Missionary){
            mnum++;
         }else{
            cnum++;
         }
      }
      if(mnum==0||mnum>=cnum) return true;
      else return false;       
    }
    return false;
  }
  public void debug(){
           System.out.println(boat.getCurrentSide().getName());
           System.out.println("rs1:"); rs1.display();
           System.out.println("rs2:"); rs2.display();
/*           System.out.println("boat:");boat.display();System.out.println();
*/
           try{
             int op=System.in.read(); 
             if(op==49) System.exit(0);
           }catch(IOException e){}     
  }
  public void transport(){
    boolean completed=true,goflag=false;
    Object tmpref;
/*********************************************/ 
    while(completed){ 
      goflag=false;
      if(boat.getCurrentSide().getName().equals("Riverside 1")){   //河岸1
      ArrayList mlist=boat.getCurrentSide().getMissList();
      ArrayList clist=boat.getCurrentSide().getCannList();
      ArrayList list=boat.getList();

      if(clist.size()>=2) 
      {
         list.add(clist.remove(0));
         list.add(clist.remove(0));
         if(!checksafe()) {
            clist.add(list.remove(0));
            clist.add(list.remove(0));
         }  else{
             goflag=true;
         }
      } else{
         if(mlist.size()>=2) {
            list.add(mlist.remove(0));
            list.add(mlist.remove(0));
            if(!checksafe()) {
               mlist.add(list.remove(0));
               mlist.add(list.remove(0));
            }  else  {
               goflag=true;
            }
         }
      }
      if(goflag) {
         boat.go();
      }  else if(clist.size()>=2) {
         if(mlist.size()>=2) {
            list.add(mlist.remove(0));
            list.add(mlist.remove(0));
            if(!checksafe())  {
               mlist.add(list.remove(0));
               mlist.add(list.remove(0));
            }  else {
               boat.go();
            }
         }          
      }  else {
         if(!clist.isEmpty()&&!mlist.isEmpty()) {
            list.add(clist.remove(0));
            list.add(mlist.remove(0));
            if(!checksafe())  {
               System.out.println("error 1");
            }  else  {
               boat.go();
            }
         } else {
            System.out.println("error 2");  
         }
      }
     }else{         //河岸2
      goflag=false;
      if(boat.getOtherSide().getCannList().isEmpty()&&boat.getOtherSide().getMissList().isEmpty()) {completed=false; break;}
      ArrayList mlist=boat.getCurrentSide().getMissList();
      ArrayList clist=boat.getCurrentSide().getCannList();
      ArrayList list=boat.getList();
         if(clist.size()>=1) { 
            list.add(clist.remove(0));
            if(!checksafe())  {
               clist.add(list.remove(0));
            } else {
               goflag=true;
            }
         }  else  {
            if(mlist.size()>=1)   {
               list.add(mlist.remove(0));
               if(!checksafe()) {
                  mlist.add(list.remove(0));
               }  else  {
                  goflag=true;
               }
            }
         }
         if(goflag)     {
            boat.go();
         }  else   {
             if(!mlist.isEmpty()&&!clist.isEmpty())  {
                list.add(clist.remove(0));
                list.add(mlist.remove(0));
                if(!checksafe())   {
                   System.out.println("error 1");
                }  else  {
                   boat.go();
                }            
             }
         }
      }
    }
/********************************************/         
  }
  public static void main(String[] args){
     MissionaryPassRiver myapp=new MissionaryPassRiver();
     myapp.transport();
  }
}