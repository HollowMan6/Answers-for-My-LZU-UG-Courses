import java.net.*;
import java.io.*;
class DownloadResource
{
   public static void main(String args[])
   {
     if(args.length!=1) 
     {  System.out.println("Usage: java DownloadResource url filename");
        System.exit(0); 
     }
     try
      {
        URL bookURL=new URL(args[0]);
        String line_str;
        BufferedReader book_is;
        BufferedWriter bw=new BufferedWriter(new FileWriter(args[1]));
        book_is=new BufferedReader(new InputStreamReader(bookURL.openStream()));
        while((line_str=book_is.readLine())!=null)
        {
 		   bw.write(line_str);
		   bw.newLine();

        }
        book_is.close();
	bw.close();
     }
     catch(MalformedURLException e)
     {
        System.out.println("MalformedURLException:"+e);
     }
     catch(IOException ioe)
     {
        System.out.println("IOException:"+ioe);
     }
  }
}

