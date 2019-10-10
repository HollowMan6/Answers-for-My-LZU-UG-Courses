import java.util.*;
import java.io.*;
interface Shape
{
   public abstract void draw();
}
class Circle implements Shape
{
  public void draw()
  {
    System.out.println("draw a circle");
  }
}
class Line implements Shape
{
  public void draw(){
    System.out.println("draw a line");
  }
}
class Rectangle implements Shape {
  public void draw(){
    System.out.println("draw a rectangle");
  }
}

public class ShapeFactory{
  public static final int SHAPE_TYPE_CIRCLE=1;
  public static final int SHAPE_TYPE_RECTANGLE=2;
  public static final int SHAPE_TYPE_LINE=3;
  public static Shape getShape(int type){
     switch(type)
     {
        case SHAPE_TYPE_CIRCLE: return new Circle();
        case SHAPE_TYPE_RECTANGLE: return new Rectangle();
        case SHAPE_TYPE_LINE:return new Line();
        default: return null;
     }
  }
}
class Panel{
  public void selectShape() throws IOException{
    System.out.println("请输入形状类型(1-Circle,2-Rectangle,3-Line):");
    BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
    int shapetype=Integer.parseInt(input.readLine());
    Shape shape=ShapeFactory.getShape(shapetype);
    if(shape==null) System.out.println("输入的形状类型不存在!");
    else shape.draw();
  }
  public static void main(String[] args) throws IOException{
    new Panel().selectShape();
  }
}