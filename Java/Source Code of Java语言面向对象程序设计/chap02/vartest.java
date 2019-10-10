public class Vartest {
  int var1=0;    //对象成员变量
  static int var2=0;    //类成员变量
  void method1(int var3) {    //形参变量
    int var4=0;     //方法内局部变量
    if(var4==0){
      int var5=0;    //代码块内局部变量
      var1++;
      var2++;
      var3++;
      var4++;
      var5++;
    }
      var1++;
      var2++;
      var3++;
      var4++;
      var5++;    //非法，var5已经消失
  }
  void method2(){
      var1++;
      var2++;
      var3++;   //非法，不存在
      var4++;   //非法，不存在
      var5++;   //非法，不存在
  }
  public static void main(String[] args)
  {
     Vartest t1=new Vartest();
     Vartest t2=new Vartest();
     t1.var1++;    //将t1对象的var1变量自加
     t2.var1++;    //将t2对象的var1变量自加
     Vartest.var2++;   //将类变量var2自加
  }
}