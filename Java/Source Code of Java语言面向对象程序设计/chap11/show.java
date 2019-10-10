import java.io.*;
import java.sql.*;
public class StudentStruct
{
    public static void main(String args[])
    {
try
{ 
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  //加载JdbcOdbcDriver驱动
}
    catch(ClassNotFoundException e)
    { System.out.println(e.getMessage());}
    try
    {
	Connection con=DriverManager.getConnection("jdbc:odbc:student");
	Statement stmt=________________________//创建语句对象
	boolean status=stmt.execute("select * from stu");
	ResultSet rs=stmt.getResultSet();
	showResultSetMeta(rs);
	stmt.close();
	con.close();
     }
    catch(SQLException e)
    {  System.out.println(e.getSQLState());}
    catch(IOException e2)
    {  System.out.println(e2.getMessage());}
    }
    public static void showResultSetMeta(ResultSet rs) throws IOException,SQLException
    {
	ResultSetMetaData md=rs.getMetaData();
	int colCount=md.getColumnCount();
	String colLabel[]=new String[colCount+1];
	for(int i=1;i<=colCount;i++){
		colLabel[i]=md.getColumnLabel(i);
        System.out.print(“”+colLabel[I]+”\t”);}
   }
}
