package lesson1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import java.sql.ResultSet;

public class JdbcUtil {
	private static Properties prop=new Properties();
	static {
		try {
			prop.load(JdbcUtil.class.getResourceAsStream("/database.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		 try {
			Class.forName(prop.getProperty("driverClass"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 String url=prop.getProperty("url");
		 String userName = prop.getProperty("user");   //登录用户名
		 String password =prop.getProperty("pass"); //密码
		 Connection con = null;
		try {
			con = DriverManager.getConnection(url,userName,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return con;
	}
	public static void createDB(String sqlstr){
		 try{
			   Connection con = getConnection();
			   Statement sql = con.createStatement();
			   sql.execute(sqlstr);
		 }catch(SQLException e1){
			 e1.printStackTrace();
		 }
	}
	public static void close(Connection con,Statement stmt,ResultSet rs){
		if(rs!=null){
			try{
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
    
}
