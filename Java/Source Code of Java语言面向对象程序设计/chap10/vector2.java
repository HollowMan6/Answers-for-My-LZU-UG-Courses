import java.util.*;
class Student
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
}
class vector2
{
   public static void main(String[] args)
   {
     Vector<Student> myv=new Vector<Student>();
           Student stu_1=new Student("赵民" ,9012,80.0f),
              stu_2=new Student("钱青" ,9013,90.0f),  
              stu_3=new Student("孙枚" ,9014,78.0f),
              stu_4=new Student("周右" ,9015,55.0f);
     myv.add(stu_1);
     myv.add(stu_2);
     myv.add(stu_3);
     myv.add(stu_4);
     Iterator<Student> iter=myv.iterator();
     while(iter.hasNext())
     {
        Student st=iter.next();
        System.out.println("name:"+st.name+"\tnumber:"+st.number+"\tscore:"+st.score);
     }
   }
}