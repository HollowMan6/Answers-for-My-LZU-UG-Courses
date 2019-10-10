import java.util.*;
class stack
{
  public static void main(String[] args)
  {
      Stack sk=new Stack();
      for(int i=0;i<10;i++) sk.push(new Integer(i));
      for(int j=0;j<10;j++) System.out.println(sk.pop());
  } 
}