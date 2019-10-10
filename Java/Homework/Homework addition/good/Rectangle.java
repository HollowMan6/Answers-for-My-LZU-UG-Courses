class Rectangle extends Shape {
    private int l, c;

    public Rectangle(int x, int y, int l, int c) {
        super(x, y);
        this.setL(l);
        this.setC(c);
    }

    public void setL(int l) {// 设置长
        this.l = l;
    }

    public void setC(int c) {// 设置宽
        this.c = c;
    }

    public void printme(Screen myscreen) {
        myscreen.setY(y);
        myscreen.setX(x);
        for (int i = 1; i <= c; i++) {
            myscreen.print(l, '*');
            myscreen.println();
        }
    }
}