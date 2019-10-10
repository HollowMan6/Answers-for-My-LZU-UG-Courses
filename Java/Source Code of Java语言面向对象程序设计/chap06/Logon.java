import java.io.*;import java.util.*;
public class Logon implements Serializable {
    private Date date = new Date();
    private String username;
    private transient String password;
    Logon(String name, String pwd) {
              username = name;
              password = pwd;
    }
   public String toString() {
        String pwd = (password == null) ? "(n/a)" : password;
        return "logon info: \n   " + "username: " + username +
      "\n   date: " + date + "\n   password: " + pwd;  
   }

public static void main(String[] args)  throws IOException,  ClassNotFoundException 
{
      Logon a = new Logon("Hulk", "myLittlePony");
      System.out.println( "logon a = " + a);
      ObjectOutputStream o = new ObjectOutputStream(  new FileOutputStream("Logon.out"));
      o.writeObject(a); 
      o.close();
      System.out.println("****************************");
      ObjectInputStream in =new ObjectInputStream( new FileInputStream("Logon.out"));
      System.out.println("Recovering object at " + new Date());
      a = (Logon)in.readObject();
      System.out.println( "logon a = " + a);
  }
} 
