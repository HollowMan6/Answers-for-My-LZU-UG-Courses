class ExceptionA extends Exception {
    public ExceptionA(String str) {
        super();
    }
}
 
class ExceptionB extends ExceptionA {
 
    public ExceptionB(String str) {
        super(str);
    }
}
 
class ExceptionC extends ExceptionA {
    public ExceptionC(String str) {
        super(str);
    }
}
public class NeverCaught {
    static void f() throws ExceptionB{
        throw new ExceptionB("exception b");
    }
 
    static void g() throws ExceptionC {
        try {
            f();
        } catch (ExceptionB e) {
            ExceptionC c = new ExceptionC("exception a");
            throw c;
        }
    }
 
    public static void main(String[] args) {
            try {
                g();
            } catch (ExceptionC e) {
                e.printStackTrace();
            }
    }
 
}
/*
exception.ExceptionC
at exception.NeverCaught.g(NeverCaught.java:12)
at exception.NeverCaught.main(NeverCaught.java:19)
*/