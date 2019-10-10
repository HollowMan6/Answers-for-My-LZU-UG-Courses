import java.sql.*;
class dbaccess1
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
              conn=DriverManager.getConnection("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=.\\student.mdb","","");
              Statement state = conn.createStatement();
//(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

              rs = state.executeQuery(sql);
              if( rs != null ){
                 System.out.println("\t学号\t 姓名\t性别\t年龄\t电话\t地  址");
                 System.out.println("\t=================================");
                  while( rs.next() ){
                       System.out.print("\t" + rs.getString("xuehao"));
                       System.out.print("\t"+rs.getString("xingming"));
                       System.out.print("\t"+rs.getString("xingbie"));
                       System.out.print("\t"+rs.getInt("nianling"));
                       System.out.print("\t"+rs.getString(5));
                       System.out.println("\t"+rs.getString(6));
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