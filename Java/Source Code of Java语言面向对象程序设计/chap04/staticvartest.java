class statictest1
{
   int a;
   static int b;
   statictest1(){a=20;b=30;}
   public static void main(String args[] )
   {
       statictest1 ss=new statictest1();
       statictest1 sb=new statictest1();
       ss.a=60;statictest1.b=80;
       sb.a=100;statictest1.b=10000;
       System.out.println("a="+ss.a);
       System.out.println("b="+b);
       System.out.println("a="+sb.a);
       System.out.println("b="+b);

   }
}
