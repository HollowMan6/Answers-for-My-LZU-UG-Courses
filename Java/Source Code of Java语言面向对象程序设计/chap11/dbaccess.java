import java.sql.*;
class dbaccess
{
    public static void main(String[] args)
    {
         String DBDriver="sun.jdbc.odbc.JdbcOdbcDriver";
         String sql="select * from dab";
         Connection conn=null;
         ResultSet rs=null;
         try
         {
              Class.forName(DBDriver);
              conn=DriverManager.getConnection("jdbc:odbc:driver=Microsoft Access Driver (*.mdb, *.accdb);DBQ=E:/mybook/chap11/student.mdb","","");
              Statement state = conn.createStatement();

             rs = state.executeQuery(sql);
              if( rs != null ){
                  while( rs.next() ){
                       System.out.print("\t" + rs.getString("xuehao"));
                       System.out.print("\t"+rs.getString("xingming"));
                       System.out.print("\t"+rs.getString("xingbie"));
                       System.out.println("\t"+rs.getInt("nianling"));
                  }

              rs.close();
              conn.close();
            }
          }
          catch(Exception e){   
               e.printStackTrace();
         }
    }
}