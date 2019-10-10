class Trangle {
	double sideA,sideB,sideC,area,length;
	boolean flag;
	public Trangle(double a,double b,double c){
		if(a+b>c&&a+c>b&&b+c>a){	//判断构成三角形的条件表达式
			sideA=a;				//参数a、b、c分别赋值给sideA,sideB,sideC
			sideB=b;
			sideC=c;
			flag=true;				//给flag赋值
		}	else {
			flag=false;
		}
	}
	public double getLength(){
		return length=sideA+sideB+sideC;	//计算出周长的值并返回
	}
	public double getArea(){
		if(flag){
			double p=(sideA+sideB+sideC)/2.0;
			area=Math.sqrt(p*(p-sideA)*(p-sideB)*(p-sideC));
			return area;
		}
		else{
			System.out.println("不是一个三角形，不能计算面积");
			return 0;
		}
	}
	public void setABC(double a,double b,double c){//注意过滤数据
		if(a+b>c&&a+c>b&&b+c>a){	//判断构成三角形的条件表达式
			sideA=a;				//参数a、b、c分别赋值给sideA,sideB,sideC
			sideB=b;
			sideC=c;
			flag=true;				//给flag赋值
	}	else {
		sideA=sideB=sideC=0;
		flag=false;
		}
	}
}
class Lader	{
	double above,bottom,height,area;
	Lader(double a,double b,double h){
		above=a;bottom=b;height=h;	//初始化成员变量
	}
	public double getArea(){
		return area=(above+bottom)*height/2;		//计算出面积并返回
	}
}
class Circle{
	double radius,area;
	Circle(double r){
		if(r>0) radius = r;			//初始化半径，注意封装
		else radius=0;
	}
	double getArea(){
		return area=radius*radius*3.14159;	//计算出面积并返回
	}
	double getLength(){ return 2*Math.PI*radius;}//计算出周长并返回
	void setRadius(double newRadius){
		if(newRadius>0)radius=newRadius;//注意过滤非法数据
	}
	double getRadius(){return radius;}
}
public class AreaAndLength {
	public static void main(String args[]){
		double length,area;
		Circle circle =null;
		Trangle trangle =null;
		Lader lader =null;
		circle=new Circle(1);
		trangle=new Trangle(3,4,5);
		lader =new Lader(4,8,5);
		length = circle.getLength();
		System.out.println("圆的周长："+ length);
		area=circle.getArea();
		System.out.println("圆的面积："+ area);
		length = trangle.getLength();
		System.out.println("三角形的周长："+ length);
		area=trangle.getArea();
		System.out.println("三角形的面积："+ area);
		area=lader.getArea();
		System.out.println("梯形的面积："+ area);
		trangle.setABC(12, 34, 1);
		area=trangle.getArea();
		System.out.println("三角形的面积："+ area);
		length = trangle.getLength();
		System.out.println("三角形的周长："+ length);
	}
}