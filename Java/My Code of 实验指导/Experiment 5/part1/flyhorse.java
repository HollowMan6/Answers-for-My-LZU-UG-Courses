import www.horsefly.Trangle;
import java.util.Date;
public class flyhorse{
    public static void main(String[] args) {
        Trangle trangle=new Trangle(12,3,104);
        trangle.jsmj();
        trangle.set(3,4,5);
        trangle.jsmj();
        Date 今天 = new Date();
        System.out.println("今天是"+今天);
    }
}