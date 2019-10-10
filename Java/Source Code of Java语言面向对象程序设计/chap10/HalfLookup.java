import java.util.*;
public class HalfLookup{
  public static void main(String[] args){
    List Ints=new LinkedList();
    Ints.add("Jack");Ints.add("Tom");
    Ints.add("Mark");Ints.add("Anny");
    Ints.add("John");String Key="Anny";
    int dest=Collections.binarySearch(Ints,Key);
    System.out.println("The position of Key is "+dest);
    Collections.sort(Ints);
    dest=Collections.binarySearch(Ints,Key);
    System.out.println("The position of Key is "+dest);
  }
}