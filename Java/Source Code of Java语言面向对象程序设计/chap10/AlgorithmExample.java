import java.util.*;
class AlgorithmExample {
  public static void main(String args[]) {
    LinkedList link = new LinkedList();
    link.add(new Integer(10));
    link.add(new Integer(35));
    link.add(new Integer(23));
    link.add(new Integer(54));
    link.add(new Integer(36));

    Iterator itt = link.iterator();
    System.out.println("列表为： ");
    while (itt.hasNext()) {
      System.out.println(itt.next());
    }
System.out.println("*************************");

    Comparator cmp = Collections.reverseOrder();
    Collections.sort(link, cmp);
    Iterator it = link.iterator();
    System.out.println("逆序排序的列表为： ");
    while (it.hasNext()) {
      System.out.println(it.next());
    }
    System.out.println("给定列表中的最大值为："+Collections.max(link));
    System.out.println("给定列表中的最小值为："+Collections.min(link));
  }
}
