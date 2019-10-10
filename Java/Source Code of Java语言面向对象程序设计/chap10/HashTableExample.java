import java.util.*;

class HashTableExample {
  public static void main(String args[]) {
    Hashtable h = new Hashtable();
    Enumeration e;
    String str;
    double bal;
    h.put("Maria", new Double(4545.50));
    h.put("Joseph", new Double(2000.00));
    h.put("Johnson", new Double(5000.00));
    e = h.keys();
    while (e.hasMoreElements()) {
      str = (String) e.nextElement();
      System.out.println(str + ": " + h.get(str));
    }
    System.out.println();
    bal = ( (Double) h.get("Maria")).doubleValue();
    h.put("Maria", new Double(bal + 1000));
    System.out.println("Maria's new balance: " + h.get("Maria"));
  }
}

