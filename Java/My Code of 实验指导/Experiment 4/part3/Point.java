import java.awt.Color;
import java.awt.color.*;
public class Point{
    private int x,y;
    private Color mycolor;
    public Point(){
        x=0;
        y=0;
    }
    public Point(int x,int y){
        this.x=x;
        this.y=y;
    }
    public Point(int x,int y,Color mycolor){
        this.x=x;
        this.y=y;
        this.mycolor=mycolor;
    }
    public double distance(Point another){
        return Math.sqrt((another.x-x)*(another.x-x)+(another.y-y)*(another.y-y));
    }
    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x=x;
    }
    public int getY(){
        return y;
    }
    public void setY(int y){
        this.y=y;
    }
    public Color getColor(){
        return mycolor;
    }
    public void setColor(Color mycolor){
        this.mycolor=mycolor;
    }
    public boolean equals(Point another){
        if(x==another.getX()&&y==another.getY()&&mycolor==another.getColor())
            return true;
        else
            return false;
    }
    public String toString(){
        String info = "";
        info+="x: "+x+"\n"+"y: "+y+"\n"+"Color: "+mycolor;
        return info;
    }
    public static void main(String[] args) {
        Point A=new Point();
        Point B=new Point(50, 60);
        Point C=new Point(100,200,Color.red);
        System.out.println("B:("+B.getX()+","+B.getY()+")"+"color:"+B.getColor());
        A.setX(100);
        A.setY(200);
        A.setColor(Color.red);
        System.out.println("A==B? "+A.equals(B));
        System.out.println("A->B "+A.distance(B));
    }
}