class Lingxing extends Shape {
    private int l;

    public Lingxing(int x, int y, int l) {
        super(x, y);
        this.setL(l);
    }

    public void setL(int l) {// 设置打印行数
        this.l = l;
    }

    public void printme(Screen myscreen) {
        myscreen.setY(y);
        myscreen.setX(x);
        for (int i = 1; i <= (l + 1) / 2; i++) {
            myscreen.print((l + 1) / 2 - i, ' ');
            myscreen.print(2 * i - 1, '*');
            myscreen.println();
        }
        for (int i = l / 2; i >= 1; i--) {
            myscreen.print((l + 1) / 2 - i, ' ');
            myscreen.print(2 * i - 1, '*');
            myscreen.println();
        }
    }
}