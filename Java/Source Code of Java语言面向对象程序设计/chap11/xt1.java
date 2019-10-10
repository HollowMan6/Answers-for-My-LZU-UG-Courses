import java.sql.*;
class databaseaccess{
    public static void main(String args[]){
        Connection con;
        Statement sql;
        ResultSet rs;
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        }catch(ClassNotFoundException e){
            System.out.println(""+e);
        }
        try {
            con=DriverManager.getConnection("jdbc:odbc:mydb","","");
            sql=con.createStatement();
            rs=sql.executeQuery("Select * from student");
            while(rs.next()){
                String num=rs.getString(1);
                String name=rs.getString(2);
                System.out.print("学号:"+num);
                System.out.print("姓名:"+name);
                System.out.println();
            }
            con.close();
        }catch(SQLException e1){}
    }
}