class Anonymousedemo {
   Anonymousedemo(){
      System.out.println("默认构造方法!");
   }
   Anonymousedemo(int x){
      System.out.println("带一个参数的构造方法！");
   }
   void method(){
     System.out.println("一成员方法");
   }
   public static void main(String[] args)
   {
     new Anonymousedemo().method();   //创建匿名对象并调用成员方法
     Anonymousedemo a=new Anonymousedemo(){
        System.out.println("初始化代码");
        void method(){
          System.out.println("匿名类中的成员方法");
        }
     };
     a.method(); 
   }
}