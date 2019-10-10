import java.io.*;
public class ReadFromKB
{
    public static void main(String args[])
    {
        try
       {
	byte bArray[]=new byte[128];
	String str;
	System.out.println("Enter something Using Keyborad:");
	System.in.read(bArray);
	str = new String(bArray, 0);
	System.out.print("You entered:");
	System.out.println(str);
       }
       catch(IOException ioe)
       {
	System.out.println(ioe.toString());
       }
    }
}
