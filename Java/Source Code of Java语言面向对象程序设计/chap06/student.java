import java.io.*;
public class student implements Serializable
{
  int id;
  String name;
  int age;
  String department;
  public student(int id, String name, int age, String department)
  {
     this.id=id;
     this.name=name;
     this.age=age;
     this.department=department;
  }
}
