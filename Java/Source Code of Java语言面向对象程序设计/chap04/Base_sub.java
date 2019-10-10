class Base{
  String var="Base's Variable";
  void method(){System.out.println("call Base's method"); }
}

class Sub extends Base{
  String var="Sub's variable"; //隐藏父类的var变量
  void method(){   //覆盖父类的method()方法
    System.out.println("call Sub's method");
  } 
  void test(){
    String var="Local variable"; //局部变量
    System.out.println("var is "+var); //打印method()方法的局部变量
    System.out.println("this.var is "+ this.var); //打印Sub实例的实例变量
    System.out.println("super.var is "+ super.var); //打印在Base类中定义的实例变量
    method(); //调用Sub实例的method()方法
    this.method(); //调用Sub实例的method()方法
    super.method(); //调用在Base类中定义的method()方法
   }
   public static void main(String args[]){
     Sub sub=new Sub();
     sub.test();
   }
}
