package lesson1;

public class JdbcDemo2 {
    public static void main(String[] args) {
    //	createDB();
 		UserDao ud=new UserDao();
//		User user=new User(1003,"majun","12345","majun@163.com");
//		int n=ud.addUser(user);
//		System.out.println(n);
//		List<User> users=ud.getAll();
//		for(User user1:users){
//			System.out.println(user1);
//		}
//		User user2=ud.findById(1001);
//		if(user2!=null) {
//			System.out.println("=======================");
//			System.out.println(user2);
//			user2.setUsername("aaaa");
//			user2.setPassword("bbbb");
//			ud.updateUser(user2);
//			System.out.println("========================");
//		}
//		List<User> users=ud.getAll();
//		for(User user1:users){
//			System.out.println(user1);
//		}
// 		User user=new User();
// //		user.setUsername("lisi");
// 		List<User> users=ud.findBy(user);
// 		for(User user4:users){
// 			System.out.println(user4);
// 		}
 	    int n=ud.deleteUser(1001);
 	    System.out.println(n);
	}
    public static void createDB(){
    	String createdbsql="create table User("+    
			    "id int not null,"+
			    "username varchar(20) not null default 'name',"+
			    "userpass varchar(20) not null default 'pass',"+
			    "email varchar(40) not null,primary key(id))";
    	JdbcUtil.createDB(createdbsql);
    }
}
