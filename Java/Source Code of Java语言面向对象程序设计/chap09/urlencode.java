import java.net.*;
class URLEncode {
  public static void main(String args[]) throws Exception{
    String str = "Jackson's bike-bell cost $5 中国";
    String str2 = URLEncoder.encode(str,"UTF-8");
    System.out.println(str);
    System.out.println(str2);
  }
}

