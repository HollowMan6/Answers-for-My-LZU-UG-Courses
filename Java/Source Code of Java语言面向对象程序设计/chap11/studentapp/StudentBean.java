import java.net.*;
import java.sql.*;
import java.lang.*;
import java.io.*;
import java.util.*;

public class StudentBean implements Serializable
{
  static Connection conn=null;
  private Statement stmt = null;
  private ResultSet rs=null;
  static 
  {
    try 
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:myoracle","scott","tiger");
    }
    catch(java.lang.ClassNotFoundException e) 
    {
      System.err.println("db(): " + e.getMessage());
    }
    catch(java.sql.SQLException e)
    {
      System.err.println("db(): " + e.getMessage());
    }
  }
  public StudentBean() {}

public static Connection getConnection()
{
    return conn;
}

public void closeConnection()
{
  try
  {
    if(conn != null)
    conn.close();
   }
   catch(java.sql.SQLException e)
   {
      System.err.println("db(): " + e.getMessage());
   }
}

public boolean insertOrUpdate(Student stu) throws SQLException
{
   boolean flag=true;
   Connection myconn=StudentBean.getConnection();
   stmt=myconn.createStatement();
   rs=stmt.executeQuery("select * from student where id='"+stu.getId()+"'");
   if(rs.next()) 
   {
      int i=stmt.executeUpdate("update student set name='"+stu.getName()+"',english="+stu.getEnglish()+",chinese="+stu.getChinese()+",math="+stu.getMath()+" where id='"+stu.getId()+"'");
      if(i==0) flag=false;
   }
   else
   {
      int i=stmt.executeUpdate("insert into student values('"+stu.getId()+"','"+stu.getName()+"',"+stu.getEnglish()+","+stu.getChinese()+","+stu.getMath()+")");
      if(i==0)flag=false;
   }
   return flag;
}
public boolean delStudent(Student stu) throws SQLException
{
   boolean flag=true;
   Connection myconn=StudentBean.getConnection();
   stmt=myconn.createStatement();
   int i=stmt.executeUpdate("delete from student where id='"+stu.getId()+"'");
   if(i==0) flag=false;
   return flag;  
}
public Student getStudent(String id) throws SQLException
{
    Connection myconn=StudentBean.getConnection();
    stmt=myconn.createStatement();
    String sql="select * from student where id='"+id+"'";
    rs=stmt.executeQuery(sql);
    Student stu=null;
    if(rs.next())
    {
      stu=new Student();
      stu.setId(rs.getString(1));
      stu.setName(rs.getString(2));
      stu.setEnglish(rs.getFloat(3));
      stu.setChinese(rs.getFloat(4));
      stu.setMath(rs.getFloat(5));
    }
    else
    {
       stu=null;
    } 
    rs.close();
    stmt.close();
    return stu;  
}

public ArrayList<Student> getAll() throws SQLException
{
    ArrayList<Student> studentlist=new ArrayList<Student>();
    Connection myconn=StudentBean.getConnection();
    stmt=myconn.createStatement();
    rs=stmt.executeQuery("select * from student");
    while(rs.next())
    {
      Student stu=new Student();
      stu.setId(rs.getString(1));
      stu.setName(rs.getString(2));
      stu.setEnglish(rs.getFloat(3));
      stu.setChinese(rs.getFloat(4));
      stu.setMath(rs.getFloat(5));
      studentlist.add(stu);      
    }
    rs.close();
    stmt.close();
    return studentlist;
}

public static String toChinese(String strvalue) 
{
try
{
if(strvalue==null)
{
return null;
}
else
{
strvalue = new String(strvalue.getBytes("ISO8859_1"), "GBK");
return strvalue;
}
}
catch(Exception e)
{
return null;
}
}

}
