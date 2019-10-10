import java.net.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class db implements Serializable
{
static Connection conn=null;
Statement stmt = null;
ResultSet rs=null;

//db的构造函数
public db() 
{
}

public static Connection getConnection()
{
try 
{
Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
conn =DriverManager.getConnection("jdbc:microsoft:sqlserver://localhost:1433;databasename=userinfo","user","user");
}
catch(java.lang.ClassNotFoundException e) 
{
//这样写是为了方便调试程序，出错打印mydb()就知道在什么地方出错了
System.err.println("db(): 1" + e.getMessage());
}
catch(java.sql.SQLException e)
{
//这样写是为了方便调试程序，出错打印mydb()就知道在什么地方出错了
System.err.println("db():2 " + e.getMessage());
}
catch(Exception e)
{
System.out.println("db():3 " + e.getMessage());	
}
return conn;
}

//关闭数据库连接
public void closeConnection()
{
try
{
if(conn != null)
conn.close();
}
catch(java.sql.SQLException e)
{
//这样写是为了方便调试程序，出错打印mydb()就知道在什么地方出错了
System.err.println("db(): 4" + e.getMessage());
}
}

//executeQuery方法用于进行记录的查询操作
//入口参数为sql语句，返回ResultSet对象
public ResultSet executeQuery(String sql) 
{
rs = null;
try 
{
Connection conn1 = getConnection();
if(conn1 != null)
{
stmt = conn.createStatement();
//执行数据库查询操作
rs = stmt.executeQuery(sql);
//stmt.close();
}
else 
{
System.out.println("connection is null!\r\n");	
}
}
catch(SQLException ex) 
{
System.out.println("db.executeQuery: " + ex.getMessage());
}
return rs;
}

//executeUpdate方法用于进行add或者update记录的操作
//入口参数为sql语句，成功返回true，否则为false
public boolean executeUpdate(String sql) 
{
boolean bupdate=false;
try 
{
//建立数据库连接
Connection conn1 = getConnection();
if(conn1 != null)
{
stmt = conn.createStatement();
int rowCount = stmt.executeUpdate(sql);
//如果不成功，bupdate就会返回0
if(rowCount!=0)
bupdate=true;
}
}
catch(SQLException ex) 
{
//打印出错信息
System.err.println("db.executeUpdate: " + ex.getMessage());
}
return bupdate;
}

//toChinese方法用于将一个字符串进行中文处理
//否则将会是???这样的字符串
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
