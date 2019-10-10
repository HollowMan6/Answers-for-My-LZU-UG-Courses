class FinallyDemo {
int no1,no2;
FinallyDemo(){}
public int test(String args[])	{
   try {
       no1 = Integer.parseInt(args[0]);
       no2 = Integer.parseInt(args[1]);
       System.out.println("相除结果为 "+no1/no2);
       return 1;
   }catch(ArithmeticException i){
       System.out.println("不能除以 0");
       return -1;
   }catch(ArrayIndexOutOfBoundsException e1){
       System.out.println("此程序要输入两个参数！");
       return -2;
   }catch(NumberFormatException e2){
       System.out.println("必须输入数字! ");
   }
   finally {
       System.out.println("Finally 已执行,用来做清理工作！");	
   } 
   return 0;	
}
public static void main(String args[]) {
    FinallyDemo fd=new FinallyDemo();
    int x=fd.test(args);
    System.out.println("x="+x); 
} 
}