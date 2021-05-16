public class Triangle extends Shape {
    private int l;

    public Triangle(int x, int y, int l) {
        super(x, y);
        this.setL(l);
    }

    public void setL(int l) {// 设置打印行数
        this.l = l;
    }

    public void printme(Screen myscreen) {
        myscreen.setY(y);
        myscreen.setX(x);
        for (int i = 1; i <= l; i++) {
            myscreen.print(l - i, ' ');
            myscreen.print(2 * i - 1, '*');
            myscreen.println();
        }
    }
}