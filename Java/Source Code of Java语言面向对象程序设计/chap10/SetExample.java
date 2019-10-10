import java.util.*;
public class SetExample {
  public static void main(String[] args) {
    Set set = new HashSet();
    set.add("one");
    set.add("second");
    set.add("3rd");
    set.add(new Integer(4));
    set.add(new Float(5.0F));
    set.add("second");
    set.add(new Integer(4));
    set.add(new Student("zhangsan",222));
    System.out.println(set);
  }
}
