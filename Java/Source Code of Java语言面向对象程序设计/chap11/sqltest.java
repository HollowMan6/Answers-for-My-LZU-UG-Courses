import java.net.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class sqltest
{
public static void main(String[] args)
{
Connection conn=null;
Statement stmt = null;
ResultSet rs=null;

try 
{
Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
conn =DriverManager.getConnection("jdbc:microsoft:sqlserver://localhost:1433;databasename=userinfo","user","user");
stmt=conn.createStatement();
rs=stmt.executeQuery("select * from userinfo");
System.out.println("<html><table border=1>");
while(rs.next())
{
    System.out.println("<tr>");
    System.out.println("<td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td></tr>");
}
System.out.println("</table></html>");
rs.close();
stmt.close();
conn.close();

}
catch(java.lang.ClassNotFoundException e) 
{
System.err.println("db(): 1" + e.getMessage());
}
catch(java.sql.SQLException e)
{
System.err.println("db():2 " + e.getMessage());
}
catch(Exception e)
{
System.out.println("db():3 " + e.getMessage());	
}

}



}
