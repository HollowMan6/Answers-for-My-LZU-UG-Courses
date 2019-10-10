public class StringSpliter
{
  public static void print(String[] s)
  {
     for(int i=0;i<s.length;i++)  System.out.print(s[i]+" ");
     System.out.println();
  }
  public static void main(String[] args) throws Exception
  {
    String[]  result;
    String str="user=Linda";
    result=str.split("=");
    print(result);
    str="11:23:14";
    result=str.split(":");
    print(result);
    str="11::14";
    result=str.split(":");
    print(result);
  }
}