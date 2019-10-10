import java.sql.*;
public class test
{
  public static void main(String[] args) throws SQLException
  {
     StudentBean mybean=new StudentBean();
     Student mystu=mybean.getStudent("100001");
     System.out.println("id="+mystu.getId());
     System.out.println("name="+mystu.getName());
     System.out.println("english="+mystu.getEnglish());
     System.out.println("chinese="+mystu.getChinese());
     System.out.println("math="+mystu.getMath());
     Student stu1=new Student("100002","zhangsan",88,77,66);
     mybean.insertOrUpdate(stu1);
  }
}