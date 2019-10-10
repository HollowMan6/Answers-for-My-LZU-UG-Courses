package mypackage;
//导入java类库
import java.sql.*;
import java.lang.*;
import oracle.jdbc.driver.*;
//MoneyBean由db派生出来，拥有db的成员变量和方法
public class MoneyBean extends db
{
private String Username=null; //用户名
private String Password=null; //密码
private long Money=0L; //金额

//在这个方法中，将所有的与设定用户有关的信息查询出来
boolean queryByName()
{
boolean beExit=false;
ResultSet rs=null;

if(Username==null) return false;
try 
{
//构建sql查询语句
String sSql="select * from userinfo where Username='"+Username+"'";
rs=executeQuery(sSql);
//调用父类的executeQuery方法
if(rs.next())
{
//查询出来的记录集不为空
Password=rs.getString("password");
Money=rs.getLong("Money");
beExit=true;
}
}
catch(Exception ex) 
{
//出错处理
System.err.println("MoneyBean.queryByName: " + ex.getMessage());
}
finally
{
//返回值
return beExit;
}

}

//根据用户名更新金额
boolean updateMoney(long newMoney)
{
if(Username==null) return false;
boolean bUpdate=false;
try 
{
//构建sql查询语句
String sSql="update userinfo set money="+newMoney+" where username='"+Username+"'";
//调用父类的executeQuery方法
bUpdate=executeUpdate(sSql);
}
catch(Exception ex) 
{
//出错处理
System.err.println("MoneyBean.updateMoney: " + ex.getMessage());
}
finally
{
//返回值
return bUpdate;
}
}

//根据用户名更新密码
boolean updatePassword(String newPasswd)
{
if(Username==null) return false;
boolean bUpdate=false;
try 
{
//构建sql查询语句
String sSql="update userinfo set password='"+newPasswd+"' where username='"+Username+"'";
//调用父类的executeQuery方法
bUpdate=executeUpdate(sSql);
}
catch(Exception ex) 
{
//出错处理
System.err.println("MoneyBean.updatePassword: " + ex.getMessage());
}
finally
{
//返回值
return bUpdate;
}
}
//属性的set/get方法，同请求的参数一致 
//属性用户名Username的get/set方法
public String getUsername()
{
return Username;
}
public boolean setUsername(String newUsername)
{
//用户名有可能是中文，需要进行转换
Username =db.toChinese(newUsername);
boolean success=queryByName();
return success;
}

//属性密码Password的get/set方法
public String getPassword()
{
return Password;
}
public boolean setPassword(String newPassword)
{
Password = newPassword;
boolean success=updatePassword(newPassword);
return success;
}

//属性Money的get/set方法
public long getMoney()
{
return Money;
}
public boolean setMoney(long newMoney)
{
Money = newMoney;
boolean success=updateMoney(newMoney);
return success;

}
//释放Bean占用的资源
public void release()
{
closeConnection();
}
//该方法仅供测试使用，测试完毕后删除
public static void main(String args[])
{
	MoneyBean testBean=new MoneyBean();
	//在这里键入userinfo中已经存在的用户名
	testBean.setUsername("yangqin");
	String passwd=testBean.getPassword();
	System.out.println("the old password of user is"+passwd+"\r\n");
	//在这里键入想使用的新密码
	testBean.setPassword("abcde");
        long tempMoney=	testBean.getMoney();
	System.out.println("the old money of user is"+tempMoney+"\r\n");        
        testBean.setMoney(2000L);
}

}
