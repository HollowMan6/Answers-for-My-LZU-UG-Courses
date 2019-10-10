import java.net.*;
public class inetaddress {
public static InetAddress ina;
public static void main(String[] args) {
try {
   ina = InetAddress.getByName(args[0]);
   System.out.println(ina.getHostAddress());
   System.out.println(ina.getHostName());

   ina=InetAddress.getLocalHost();
   System.out.println(""+ina);

   }catch(ArrayIndexOutOfBoundsException e1){}
   catch (Exception e) {System.out.println(e);}
  }
}
