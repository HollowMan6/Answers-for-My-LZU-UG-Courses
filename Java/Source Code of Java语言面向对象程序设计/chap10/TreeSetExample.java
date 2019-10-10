import java.util.*;
class TreeSetExample
{  
   public static void main(String args[])
   {
      TreeSet<Student> mytree=
             new TreeSet<Student>(new Comparator<Student>()
                                  {
                                      public int compare(Student a,Student b)
                                      {
                                          return a.compareTo(b);
                                      }
                                  });
       for(int i=0;i<5;i++)
       {
          Scanner read=new Scanner(System.in);
          System.out.println("学生的姓名:");
          String name=read.nextLine();
          System.out.println("输入分数(整数):");
          int score=read.nextInt();
          mytree.add(new Student(score,name));
       }
      Iterator<Student> te=mytree.iterator();
      while(te.hasNext())
      {
        Student stu=te.next();
        System.out.println(""+stu.name+"  "+stu.english);
      }
   }
}
class Student  implements Comparable 
{ 
   int english=0;
   String name;
   Student(int e,String n)
   {
      english=e;name=n;
   }
public int compareTo(Object b)
  { 
     Student st=(Student)b;
     return (this.english-st.english);
  }
}
