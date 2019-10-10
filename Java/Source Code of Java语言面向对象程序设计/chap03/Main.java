class Test1{
    private int i=100;
    public Test1(){}
    public void putI(int n){i=n;}
    public int getI(){return i;}
}
class Test2{
    public void method1(){
       int i=200;
       Test1 obj1=new Test1();
       obj1.putI(20);
       method2(obj1,i);
       System.out.print(obj1.getI());
    }
    public void method2(Test1 v,int i){
       i=0;
       v.putI(30);
       Test1 obj2=new Test1();
       v=obj2;
       System.out.print(v.getI()+","+i+",");
    }
}
public class Main{
    public static void main(String[] args){
       Test2 obj=new Test2();
       obj.method1();
    }
}