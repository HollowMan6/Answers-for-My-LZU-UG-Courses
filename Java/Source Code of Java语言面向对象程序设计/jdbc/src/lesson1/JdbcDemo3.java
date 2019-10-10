package lesson1;

import java.util.List;

public class JdbcDemo3 {
	public static void main(String[] args) {
		UserDaoBean udb=new UserDaoBean();
		User user=new User(1007,"abcokong","12345","xya@126.com");
		int n=udb.addUser(user);
		System.out.println(n);
		List<User> users=udb.getAll();
		for(User user1:users){
			System.out.println(user1);
		}
	}

}
