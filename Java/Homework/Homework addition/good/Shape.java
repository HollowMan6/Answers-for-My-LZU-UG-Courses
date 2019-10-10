abstract class Shape {
    protected int x, y;

    public Shape(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public void setX(int x) {// 设置打印起始的横坐标
        this.x = x;
    }

    public int getX() {// 获取打印起始的横坐标
        return x;
    }

    public void setY(int y) {// 设置打印起始的纵坐标
        this.y = y;
    }

    public int getY() {// 获取打印起始的纵坐标
        return y;
    }

    abstract void printme(Screen myscreen);// 抽象打印方法
}