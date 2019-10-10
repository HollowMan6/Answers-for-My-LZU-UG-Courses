public class StudentTest
{
   public static void main(String[] args)
   {
     Student stu1=new Student();
     Student stu2=new Student("张三",'M',23);
     stu1.setName("李斯");
     stu1.setAge(75);
     stu1.inputData();
     stu2.inputData();
     System.out.println("stu1:"+stu1);
     System.out.println("stu2:"+stu2);
     System.out.println("stu1's total="+stu1.total());
     System.out.println("stu2's total="+stu2.total());
   }
}