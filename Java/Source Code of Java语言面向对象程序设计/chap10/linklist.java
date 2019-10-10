import java.util.*;
class Student implements Comparable
{
    String name ;
    int number;
    float score;
    Student(String name,int number,float score)
    {
      this.name=name;
      this.number=number;
      this.score=score;
    }
public int compareTo(Object b)
  { 
     Student st=(Student)b;
     return (int)(this.score-st.score);
  }
}
public class linklist
{
    public  static void main(String args[])
    {
      LinkedList<Student> mylist=new LinkedList<Student>();
      Student stu_1=new Student("赵民" ,9012,80.0f),
              stu_2=new Student("钱青" ,9013,90.0f),  
              stu_3=new Student("孙枚" ,9014,78.0f),
              stu_4=new Student("周右" ,9015,55.0f);
      mylist.add(stu_1); 
      mylist.add(stu_2);
      mylist.add(stu_3); 
      mylist.add(stu_4);
      Iterator<Student> iter=mylist.iterator();
      while(iter.hasNext())
      { 
         Student te=iter.next();
         System.out.println(te.name+" "+te.number+"  "+te.score);
       }
      Collections.sort(mylist);
      System.out.println("sorted:");
      Iterator<Student> iter1=mylist.iterator();
      while(iter1.hasNext())
      { 
         Student te1=iter1.next();
         System.out.println(te1.name+" "+te1.number+"  "+te1.score);
       }
    }
}

