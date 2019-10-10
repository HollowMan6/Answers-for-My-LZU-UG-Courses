import java.sql.*;
import java.io.*;
//import oracle.jdbc.driver.*;
public class ProductDos {
  public static void main(String args[]) {
    try {
      Connection conn;
      Statement stmt;
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String input;
      int prodID, prodPrice, discount;
      String prodName;
      System.out.print("Product ID:");
      input = br.readLine();
      prodID = new Integer(input).intValue();
      System.out.print("Product Name:");
      prodName = br.readLine();
      System.out.print("Product Price:");
      input = br.readLine();
      prodPrice = new Integer(input).intValue();
      System.out.print("Discount:");
      input = br.readLine();
      discount = new Integer(input).intValue();
System.out.println("Connecting to the database...");
//***************************************************************
//Class.forName("oracle.jdbc.driver.OracleDriver");
//System.out.println("xxxxxxxxxxxxxxxxxxxxxxxx");
//conn =DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:MYORACLE","scott","tiger");
//System.out.println("yyyyyyyyyyyyyyyyyyyyyyy");
//****************************************************************
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
     conn = DriverManager.getConnection("jdbc:odbc:oradsn", "scott","tiger");
//****************************************************************
      System.out.println("Connected to the database...");
      stmt = conn.createStatement();
     String query = "insert into Customer values(" + prodID + ",'" + prodName +"'," + prodPrice + "," + discount + ")";
     System.out.println(query);
      stmt.execute(query);
      System.out.println("Database Updated...");
System.out.println("The database content are:");
System.out.println("****************************");
ResultSet r = stmt.executeQuery("SELECT * FROM Customer");
while(r.next()){
		int i = r.getInt(1);
		String s = r.getString(2);
		int f = r.getInt(3);
                                     int x=r.getInt(4);
		System.out.println("ROW="+i+" "+s+" "+f+"  "+x);
}
System.out.println("*************************");
r.close();
     stmt.close();
      conn.close();
    }
    catch (Exception e) {System.out.println(e.getMessage());}
  }
}
