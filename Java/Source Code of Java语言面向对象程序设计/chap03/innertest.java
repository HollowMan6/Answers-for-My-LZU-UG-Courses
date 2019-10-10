class A
{
   int a;
   public A(){a=29;}
   public void print()
   {
      System.out.println("a="+a);
      B myb=new B();   //使用内部类创建对象
      myb.display();
   }
   class B    //内部类
   {
      int b;
      B(){b=78;}
      public void display()
      {
        System.out.println("a="+a);   //可以直接访问外部类的成员变量
        System.out.println("b="+b);
      }
   }
}
class innertest
{
   public static void main(String[] args)
   {
      A mya=new A();
      mya.print();
      A.B innerobj=new A().new B();   //在其他类中创建内部类对象
      innerobj.display();  
   }
}