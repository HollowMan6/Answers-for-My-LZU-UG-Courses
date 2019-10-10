import  java.sql.*;
import java.io.*;
public class odbctest
{
        public static void main(String args[])
        {
              String ccode,cname,caddress,cphone;
              try{
                     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
              }catch(ClassNotFoundException e){}
              try{
                     Connection con=DriverManager.getConnection("jdbc:odbc:student");
                     Statement stmt=con.createStatement();
                     String sql="select * from dab";
                     ResultSet rs=stmt.executeQuery(sql);
                     System.out.println("ccode    \t cname   \t    caddress   \t  cphone");
                     System.out.println("-------------------------------------------------------------");
                     while(rs.next())
                     {
                            ccode=rs.getString(1);
                            cname=rs.getString("xingming");
                            caddress=rs.getString(3);
                            cphone=rs.getString("phone"); 
                            System.out.println(ccode+"\t"+cname+"\t"+caddress+"\t"+cphone);
                     }
                     System.out.println("=====================================================");
                     rs.close();
                     stmt.close();
                     con.close();
              }catch(Exception e1){e1.printStackTrace();}
        }
}