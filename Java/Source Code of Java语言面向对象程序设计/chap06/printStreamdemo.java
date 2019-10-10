import java.io.*;
class printStreamDemo{
  public static void main(String[] args) {
     FileOutputStream fout;
     try{
       fout=new FileOutputStream("sintable.txt");
       PrintStream ps=new PrintStream(fout);

       for(int i=1;i<=100;i++) {
          ps.println("sqrt("+i+")="+Math.sqrt(i)); 
       } 
       ps.close();
       fout.close();      
     }
     catch(Exception e){}
  }
}