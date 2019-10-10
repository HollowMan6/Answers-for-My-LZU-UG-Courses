/* Trangle.java */
package www.horsefly;

public class Trangle {
    double sideA, sideB, sideC;
    boolean flag;

    public Trangle(double a, double b, double c) {
        sideA = a;
        sideB = b;
        sideC = c;
        if (a + b > c && a + c > b && c + b > a) {
            System.out.println("我是一个三角形");
            flag = true;
        } else {
            System.out.println("我不是一个三角形");
            flag = false;
        }
    }

    public void jsmj() {
        if (flag) {
            double p = (sideA + sideB + sideC) / 2.0;
            double area = Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
            System.out.println("是一个三角形，能计算面积");
            System.out.println("面积是：" + area);
        } else {
            System.out.println("不是一个三角形，不能计算面积");
        }
    }

    public void set(double a, double b, double c) {
        sideA = a;
        sideB = b;
        sideC = c;
        if (a + b > c && a + c > b && c + b > a) {
            flag = true;
        } else {
            flag = true;
        }
    }
}