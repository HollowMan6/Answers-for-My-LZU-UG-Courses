import java.util.regex.*;
class Patterntest1
{
   public static void main(String args[])
   {
     Pattern p;
     Matcher m;
     String s1="23&12$A69Q90W";
     p=Pattern.compile("[24680][13579]\\w");   //得到模式对象
     m=p.matcher(s1);            //初始化匹配对象
     while(m.find())
     {
       String str=m.group();   //返回由前面匹配操作所匹配的子字符串
       System.out.print("从"+m.start()+"到"+m.end()+"匹配模式子序列：");
       System.out.println(str);
     }
     System.out.println("****************************");
     String s2="as27Aiu86Adfs";
     p=Pattern.compile("[1-9&&[^46]]A[^234]"); //重新初始化模式对象
     m=p.matcher(s2);
     while(m.find())
     {
       String str=m.group();
       System.out.print("从"+m.start()+"到"+m.end()+"匹配模式子序列：");
       System.out.println(str);
     }
     s1="A11boyA12javaA21catA22ss";
     p=Pattern.compile("A[12]{2}\\w{3,}");
     m=p.matcher(s1);
     while(m.find())
     {
        String str=m.group();
        System.out.print("从"+m.start()+"到"+m.end()+"匹配模式子序列：");
        System.out.println(str);
     }
     System.out.println("***************************");
     p=Pattern.compile("A[12]{2}\\w{3}");
     m=p.matcher(s1);
     while(m.find())
     {
        String str=m.group();
        System.out.print("从"+m.start()+"到"+m.end()+"匹配模式子序列：");
        System.out.println(str);
     }
     System.out.println("*************************");
     s2="loveyouhateherloveapplejjkkhatemonkeylove444";
     p=Pattern.compile("love\\w{3}|hate\\w{3}");  //可以使用|或运算
     m=p.matcher(s2);
     while(m.find())
     {
        String str=m.group();
        System.out.print("从"+m.start()+"到"+m.end()+"匹配模式子序列：");
        System.out.println(str);
     }
   }
}