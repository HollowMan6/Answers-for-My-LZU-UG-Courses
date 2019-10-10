import java.io.*;
import java.net.*;
public class url {
public static void main(String args[]) {
try {
FileOutputStream fos = new FileOutputStream("my.pdf");
URL url = new URL("http://localhost/lzucc/ibmlife.pdf");
InputStream is  = url.openStream();
	int i = is.read();
	while(i !=-1) { 
	fos.write((byte)i);
	i = is.read();}
fos.flush();
fos.close();
is.close();}
catch (IOException e) {      }
}}
