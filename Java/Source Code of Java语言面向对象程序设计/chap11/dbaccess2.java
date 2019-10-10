import java.sql.*;
class dbaccess2
{
    public static void main(String[] args)
    {
         String DBDriver="sun.jdbc.odbc.JdbcOdbcDriver";
         Connection conn=null;
         ResultSet rs=null;
         try
         {
              Class.forName(DBDriver);
              conn=DriverManager.getConnection("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=.\\student.mdb","","");
              Statement state = conn.createStatement();
/*****************************/
            String sql="insert into dab values('8806','江泽明','44',67,'4444','444444')";
             int i=state.executeUpdate(sql);
/************************************/


              conn.close();
          }
          catch(Exception e){   
               e.printStackTrace();
         }
    }
}