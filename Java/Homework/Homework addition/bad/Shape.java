public class Shape{
    private int x,y;
    public Shape(int x,int y){
        this.setX(x);
        this.setY(y);
    }
    public void setX(int x){
        this.x=x;
    }
    public int getX(){
        return x;
    }
    public void setY(int y){
        this.y=y;
    }
    public int getY(){
        return y;
    }
    public void printme(Screen myscreen){
    }
}