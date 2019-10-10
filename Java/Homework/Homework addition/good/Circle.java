class Circle extends Shape {
    private int r;

    public Circle(int x, int y, int r) {
        super(x, y);
        this.setR(r);
    }

    public void setR(int r) {// 设置半径
        this.r = r;
    }

    public void printme(Screen myscreen) {
        myscreen.setY(y);
        myscreen.setX(x);
        for (int i = 0; i < 2 * r + 1; i += 2) {
            int dy = (int) Math.round(Math.sqrt(Math.pow(r, 2) - Math.pow(r - i, 2)));
            int dx = r - dy;
            myscreen.print(dx, ' ');
            myscreen.print(2 * dy + 3, '*');
            myscreen.println();
        }
    }
}