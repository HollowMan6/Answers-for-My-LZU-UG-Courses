import java.net.*;
import java.io.*;
import java.util.*;
public class URLConnectionExample {
public static void main(String args[]) throws Exception {
    URL url = new URL(args[0]);
    URLConnection con = url.openConnection();
    System.out.println("URL used is: " + con.getURL().toExternalForm());
    System.out.println("Content Type: " + con.getContentType());
    System.out.println("Content Length: " + con.getContentLength());
    System.out.println("Last Modified: " + new Date(con.getLastModified()));
    System.out.println("First Three lines :");
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    for (int i = 0; i < 20; i++) {
      String line = in.readLine();
      if (line == null) {
        break;
      }
      System.out.println(" " + line);
    }
  }
}
