import java.util.*;
class Student 
{
   String name;
   int score;
   Student(String name,int score)
   {
      this.name=name;
      this.score=score;
   }
}
public class HashSettest
{
   public  static void main(String args[])
   { 
      Student zh=new Student("张红铭",77),
              wa=new Student("王家家",68),
              li=new Student("李佳佳",67);
      HashSet<Student> set=new HashSet<Student>();
      HashSet<Student> subset=new HashSet<Student>();
      set.add(zh); 
      set.add(wa);
      set.add(li);
      subset.add(wa);
      subset.add(li); 
      if(set.contains(zh))
      {
          System.out.println("学生有叫张红铭的");
      }
      if(set.containsAll(subset))
      {
           System.out.println("集合set包含集合subset");
      }
      int number=subset.size();
      System.out.println("集合subset中有"+number+"个元素:");
      Object s[]=subset.toArray();
      for(int i=0;i<s.length;i++)
      {
        System.out.printf("学生:%s,分数:%d\n",((Student)s[i]).name,((Student)s[i]).score);
      }
      number=set.size();
      System.out.println("集合set中有"+number+"个元素:");
      Iterator<Student> iter=set.iterator();
      while(iter.hasNext())
      { 
        Student te=iter.next();
        System.out.printf("学生:%s,分数:%d\n",te.name,te.score);
      }
   }
}

