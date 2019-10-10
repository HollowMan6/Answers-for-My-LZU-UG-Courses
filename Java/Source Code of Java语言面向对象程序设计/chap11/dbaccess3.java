import java.sql.*;
class dbaccess3
{
    public static void main(String[] args)
    {
         String DBDriver="sun.jdbc.odbc.JdbcOdbcDriver";
         Connection conn=null;
         ResultSet rs=null;
         String sql;
         try
         {
              Class.forName(DBDriver);
              conn=DriverManager.getConnection("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=.\\student.mdb","","");
              Statement state = conn.createStatement();
/*****************************/
//             sql="insert into cjb values('8802',85,98,18,38,67)";
               sql="delete * from dab where xuehao='8804'";
             int i=state.executeUpdate(sql);
/************************************/


              conn.close();
          }
          catch(Exception e){   
               e.printStackTrace();
         }
    }
}