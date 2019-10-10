import java.util.*;
class MyObGen {
    private Object ob;
    public MyObGen(Object ob){
        this.ob=ob;
    }
    public Object getOb(){
        return ob;
    }
    public void setOb(Object ob){
        this.ob=ob;
    }
    public void showType(){
        System.out.println("对象实际类型是："+ob.getClass().getName());
    }
}
public class NoGeneric {
    public static void main(String[] args){
        MyObGen intOb=new MyObGen(new Integer(66));
        intOb.showType();
        int i=(Integer)intOb.getOb();
        System.out.println("value= "+i);
        System.out.println();
        MyObGen strOb=new MyObGen("One String");
        strOb.showType();
        String s=(String) strOb.getOb();
        System.out.println("value= "+s);
    }
}