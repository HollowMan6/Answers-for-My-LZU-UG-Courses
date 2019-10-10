package lesson1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class UserDao {
    public int addUser(User user){
    	Connection con=null;
    	Statement stmt=null;
    	int n=0;
    	try{
    		con=JdbcUtil.getConnection();
    		stmt=con.createStatement();
    		String sql="insert User(id,username,userpass,email) "+
    		"values("+user.getId()+",'"+user.getUsername()+"','"+
    				user.getPassword()+"','"+user.getEmail()+"')";
    		n=stmt.executeUpdate(sql);
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		JdbcUtil.close(con, stmt, null);
    	}
    	return n;
    }
    public List<User> getAll(){
    	Connection con=null;
    	Statement stmt=null;
        ResultSet rs=null;
    	ArrayList<User> users=new ArrayList<User>();
    	try {
    		con=JdbcUtil.getConnection();
    		stmt=con.createStatement();
    		String sql="select * from user";
    		rs= stmt.executeQuery(sql);
    		while(rs.next()){
    			User user=new User();
    			user.setId(rs.getInt("id"));
    			user.setUsername(rs.getString("username"));
    			user.setPassword(rs.getString("userpass"));
    			user.setEmail(rs.getString("email"));
    			users.add(user);
    		}

    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		JdbcUtil.close(con, stmt, rs);
    	}
    	return users;
    }
    public int updateUser(User user){
    	Connection con=null;
    	Statement stmt=null;
        int n=0;
        try {
        	con=JdbcUtil.getConnection();
        	stmt=con.createStatement();
        	String sql="update user set username='"+user.getUsername()+"',userpass='"
        			+user.getPassword()+"',email='"+user.getEmail()+"' "
        					+ "where id="+user.getId();
        	n=stmt.executeUpdate(sql);
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	JdbcUtil.close(con, stmt, null);
        }
        return n;
    }
    public User findById(int id){
    	Connection con=null;
    	Statement stmt=null;
    	ResultSet rs=null;
    	User user=null;
    	try {
    		con=JdbcUtil.getConnection();
    		stmt=con.createStatement();
    		String sql="select * from user where id="+id;
    		rs=stmt.executeQuery(sql);
    		if(rs.next()){
    			user=new User();
    			user.setId(rs.getInt("id"));
    			user.setUsername(rs.getString("username"));
    			user.setPassword(rs.getString("userpass"));
    			user.setEmail(rs.getString("email"));    			
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		JdbcUtil.close(con, stmt, rs);
    	}
    	return user;
    }
	/*
	 * User对象除主键外
	 * 哪个属性有值就根据哪个属性查询
	 * @param user
	 * @return 
	 */
    public List<User> findBy(User user){
        Connection con=null;
        Statement stmt=null;
        ResultSet rs=null;
        ArrayList<User> users=new ArrayList<User>();
        
        String sql="select * from user where 1=1";
        String username=user.getUsername();
        if(username!=null&&!"".equals(username)){
        	sql+=" and username='"+username+"'";
        }
        String userpass=user.getPassword();
        if(userpass!=null&&!"".equals(userpass)){
        	sql+=" and userpass='"+userpass+"'";
        }
        String email=user.getEmail();
        if(email!=null&&"".equals(email)){
        	sql+=" and email='"+email+"'";
        }
        System.out.println(sql);
        try {
        	con=JdbcUtil.getConnection();
        	stmt=con.createStatement();
        	rs=stmt.executeQuery(sql);
        	while(rs.next()){
        		User u=new User();
    			u.setId(rs.getInt("id"));
    			u.setUsername(rs.getString("username"));
    			u.setPassword(rs.getString("userpass"));
    			u.setEmail(rs.getString("email"));  
    			users.add(u);
        	}
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	JdbcUtil.close(con, stmt, rs);
        }
        return users;
    }
    public int deleteUser(int id){
    	Connection con=null;
    	Statement stmt=null;
    	int n=0;
    	try {
    		con=JdbcUtil.getConnection();
    		stmt=con.createStatement();
    		String sql="delete from user where id="+id;
    		n=stmt.executeUpdate(sql);
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		JdbcUtil.close(con, stmt, null);
    	}
    	return n;
    }
}
