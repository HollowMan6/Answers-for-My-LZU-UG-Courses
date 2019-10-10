import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
public class PropertiesTester{
  public static void print(Properties ps){
    Set<Object> keys=ps.keySet();
    Iterator<Object> it=keys.iterator();
    while(it.hasNext()){
      String key=(String)it.next();
      String value=ps.getProperty(key);
      System.out.println(key+"="+value);
    }
  }
  public static void main(String args[])throws IOException{
    Properties ps=new Properties();
    //myapp.properties文件与PropertiesTester类的.class文件位于同一个目录下 
    InputStream in=PropertiesTester.class.getResourceAsStream("myapp.properties");
    ps.load(in);
    JFrame myapp=new JFrame("属性测试");
    if(ps.getProperty("Color").equals("red"))
      myapp.getContentPane().setBackground(Color.red);
    else if(ps.getProperty("Color").equals("blue"))
      myapp.getContentPane().setBackground(Color.blue);
    else
      myapp.getContentPane().setBackground(Color.green);
    myapp.setSize(300,300);
    myapp.setVisible(true);
    myapp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    print(ps);
    System.out.println("************当前的系统属性*****************");
    ps=System.getProperties();
    print(ps);
    System.out.println("*****************************************");
  }
}


