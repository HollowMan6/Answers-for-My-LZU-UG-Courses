import java.sql.*;
class dbaccess
{
    public dbaccess(){}
    public static void main(String[] args)
    {
         String DBDriver="sun.jdbc.odbc.JdbcOdbcDriver";
         String sql="select * from dab";
         Connection conn=null;
         ResultSet rs=null;
         try
         {
              Class.forName(DBDriver);
              conn=DriverManager.getConnection("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=.\\student.mdb","","");
              Statement state = conn.createStatement();
//(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

             rs = state.executeQuery(sql);
              if( rs != null ){
                  while( rs.next() ){
                       System.out.print("\t" + rs.getString("xuehao"));
                       System.out.print("\t"+rs.getString("xingming"));
                       System.out.print("\t"+rs.getString("xingbie"));
                       System.out.println("\t"+rs.getInt("nianling"));
                  }

//           sql="insert into dab values('8888','888','88',88,'888','88888')";
//              int i=state.executeUpdate(sql);
              rs.close();
              conn.close();
            }
          }
          catch(Exception e){   
               e.printStackTrace();
         }
    }
}