import java.util.*;
class treeset
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
   Student st1,st2,st3,st4;
   st1=new Student(90,"zhan ying");
   st2=new Student(66,"wang heng");
   st3=new Student(86,"Liuh qing");
   st4=new Student(76,"yage ming");
   mytree.add(st1);
   mytree.add(st2);
   mytree.add(st3);
   mytree.add(st4);
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

