package lesson1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class UserDaoBean {
    public int addUser(User user){
    	Connection con=null;
    	PreparedStatement pstmt=null;
    	int n=0;
    	try{
    		con=JdbcUtil.getConnection();
    		String sql="insert User(id,username,userpass,email) values(?,?,?,?)";
    		pstmt=con.prepareStatement(sql);
    		pstmt.setInt(1, user.getId());
    		pstmt.setString(2, user.getUsername());
    		pstmt.setString(3, user.getPassword());
    		pstmt.setString(4, user.getEmail());
    		n=pstmt.executeUpdate();
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		JdbcUtil.close(con, pstmt, null);
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
    	PreparedStatement pstmt=null;
        int n=0;
        try {
        	con=JdbcUtil.getConnection();
        	String sql="update user set username=? userpass=? email=? where id=?";
        	pstmt=con.prepareStatement(sql);
        	/*String sql="update user set username='"+user.getUsername()+"',userpass='"
        			+user.getPassword()+"',email='"+user.getEmail()+"' "
        					+ "where id="+user.getId();
        	*/
    		pstmt.setInt(4, user.getId());
    		pstmt.setString(1, user.getUsername());
    		pstmt.setString(2, user.getPassword());
    		pstmt.setString(3, user.getEmail());
        	n=pstmt.executeUpdate();
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	JdbcUtil.close(con, pstmt, null);
        }
        return n;
    }
    public User findById(int id){
    	Connection con=null;
    	PreparedStatement pstmt=null;
    	ResultSet rs=null;
    	User user=null;
    	try {
    		con=JdbcUtil.getConnection();
    		String sql="select * from user where id=?";
    		pstmt=con.prepareStatement(sql);
    		pstmt.setInt(1, id);    		
    		rs=pstmt.executeQuery();
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
    		JdbcUtil.close(con, pstmt, rs);
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
    	PreparedStatement pstmt=null;
    	int n=0;
    	try {
    		con=JdbcUtil.getConnection();
    		String sql="delete from user where id="+id;
    		pstmt=con.prepareStatement(sql);
    		n=pstmt.executeUpdate();
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		JdbcUtil.close(con, pstmt, null);
    	}
    	return n;
    }
}
