//====================PrepareAuthRec.java===================
import java.sql.*;
public class PrepareAuthRec
{
	public static void main(String args[])
	{
	String auth[]={"张三","李四","王五"};
	String tel[]={"(03)3333333","(04)4444444","(05)5555555"};
	try 
	{  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");}
	catch(ClassNotFoundException e)
	{  System.out.println(e.getMessage());}
	try
	{
		Connection con=DriverManager.getConnection("jdbc:odbc:bookbase");
		Statement stmt=con.createStatement();
		PreparedStatement pstmt=con.prepareStatement("insert into authTab values(?,?)");
		for(int i=0;i<auth.length;i++)
		{
   		   pstmt.setString(1,auth[i]);
		   pstmt.setString(2,tel[i]);
		   pstmt.executeUpdate();
		}
                pstmt.close();
		ResultSet rs=stmt.executeQuery("select * from authTab");
		while(rs.next())
		{
			System.out.println(rs.getString("auth")+"   \t"+rs.getString("tel"));
		}
		stmt.close();
		con.close();
	}catch(SQLException ex)
	{  System.err.println("SQLException:"+ex.getMessage());
	}
    }
}