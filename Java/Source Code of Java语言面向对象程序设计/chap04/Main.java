class VarInBase{
    int i=10;
    public void print(){
       System.out.println("i="+i);
    }
}
class VarInSub extends VarInBase{
    int i=100;
    public void print(){
        int i=1000;
        System.out.println("i="+i);
    }
}
public class Main{
    public static void main(String args[]){
        VarInBase m=new VarInSub();
        m.print();
    }
}