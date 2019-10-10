/*
* 云数据库地址：www.flyhorsespace.com	
* 端口: 3306
* 数据库：test
* 账号：user
* 密码：usertest
* 采用以下java代码，或安卓代码连接，创建数据表，查询删除都可以！
* 做实验用，不建议大量的数据读写！
* 也可以用mysql客户端或其它数据库工具连接使用！
*/

import java.sql.*;
public class ConnectToMySQL {
 public static Connection getConnection() throws SQLException,java.lang.ClassNotFoundException
 {
 String url = "jdbc:mysql://www.flyhorsespace.com:3306/test";  //连接MySQL中的test数据库
 Class.forName("com.mysql.jdbc.Driver");
 String userName = "user";   //登录用户名
 String password = "usertest"; //密码
 Connection con = DriverManager.getConnection(url,userName,password);
 return con;
 }
 public static void main(String[] args) {
  try{
   Connection con = getConnection();
   Statement sql = con.createStatement();
   sql.execute("drop table if exists student");   //如果原来存在student表，则删除
   sql.execute("create table student("+     //创建新表student
    "id int not null auto_increment,"+
    "name varchar(20) not null default 'name',"+
    "math int not null default 60,"+
    "primary key(id))");
   sql.execute("insert student values(1,'AAA','99')");  //插入一条记录
   sql.execute("insert student values(2,'BBB','77')");
   sql.execute("insert student values(3,'CCC','65')");
   String query = "select * from student";   //查询表student中的记录
   ResultSet result = sql.executeQuery(query);
   System.out.println("Student表数据如下：");
   System.out.println("---------------------------------");
   System.out.println("学号"+" "+"姓名"+" "+"数学成绩");
   System.out.println("---------------------------------");
   int number;
   String name;
   String math;
   while(result.next()){
       number = result.getInt("id");
       name = result.getString("name");
       math = result.getString("math");
       System.out.println(number + " " + name + " " + math);
   }
   sql.close();
   con.close();
  }catch(java.lang.ClassNotFoundException e){
   System.err.println("ClassNotFoundException:" + e.getMessage());
  }catch(SQLException ex){
   System.err.println("SQLException:" + ex.getMessage());
  }
 }
}