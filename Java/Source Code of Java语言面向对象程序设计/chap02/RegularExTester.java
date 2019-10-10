public class RegularExTester
{
  public static void main(String[] args)
  {
    System.out.println("aaabaaacaaa".replaceAll("a{3}","z"));
    System.out.println("aaabaaca".replaceAll("a{1,3}","\\*"));
    System.out.println("123a44b35cc".replaceAll("\\d","z"));
    System.out.println("1234abc435def".replaceAll("\\D","0"));
    System.out.println("com.abc.dollapp.Doll".replaceAll("\\.","\\\\"));
    System.out.println("azbhelloahball".replaceAll("a.b","-"));
    System.out.println("a.b.c.1.2.3.4".replaceAll("[a-zA-Z_0-9]","#"));
  }
}