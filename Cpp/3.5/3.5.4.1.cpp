#include <iostream>
#include <math.h>
#define pi 3.1415926
using namespace std;
class Circle{
    private:
        double x,y,r;
    public:
        Circle(double x,double y,double r){
            this->r=r;
            this->x=x;
            this->y=y;
            if(r<0){
                cout<<"半径不可为负数，将使用默认值"<<endl;
                this->r=0;
            }
        }
        Circle(){
            x=0;
            y=0;
            r=0;
        }
        double GetArea(){
            return r*r*pi;
        }
        void Show(){
            cout<<"圆心坐标：("<<x<<","<<y<<")"<<endl;
            cout<<"直径:"<<2*r<<endl;
            cout<<"周长:"<<2*pi*r<<endl;
            cout<<"面积:"<<GetArea()<<endl;
        }
        void Set(double x,double y,double r){
            this->r=r;
            this->x=x;
            this->y=y;
            if(r<0){
                cout<<"半径不可为负数,将使用默认值"<<endl;
                this->r=0;
            }
        }
};

class Rectangle{
    private:
        double x,y;
    public:
        Rectangle(double x,double y){
            this->x=x;
            this->y=y;
            if(x<0){
                cout<<"长不可为负数，将使用默认值"<<endl;
                this->x=0;
                this->y=0;
            }
            if(y<0){
                cout<<"宽不可为负数，将使用默认值"<<endl;
                this->x=0;
                this->y=0;
            }
        }
        Rectangle(){
            x=0;
            y=0;
        }
        double GetArea(){
            return x*y;
        }
        void Show(){
            cout<<"长："<<x<<ends<<"宽："<<y<<endl;
            cout<<"周长:"<<2*(x+y)<<endl;
            cout<<"面积:"<<GetArea()<<endl;
        }
        void Set(double x,double y){
            this->x=x;
            this->y=y;
            if(x<0){
                cout<<"长不可为负数，将使用默认值"<<endl;
                this->x=0;
                this->y=0;
            }
            if(y<0){
                cout<<"宽不可为负数，将使用默认值"<<endl;
                this->x=0;
                this->y=0;
            }
        }
};

class Triangle{
    private:
        double x1,y1,x2,y2,x3,y3;
        double a,b,c;
    public:
        Triangle(double x1,double y1,double x2,double y2,double x3,double y3){
            a=Calculate(x1,y1,x2,y2);
            b=Calculate(x1,y1,x3,y3);
            c=Calculate(x2,y2,x3,y3);
            if(a+b>c&&a+c>b&&b+c>a){
                this->x1=x1;
                this->y1=y1;
                this->x2=x2;
                this->y2=y2;
                this->x3=x3;
                this->y3=y3;
            }else{
                Triangle();
                cout<<"输入的三角形的顶点不能组成一个三角形！将使用默认值！"<<endl;
            }
        }
        Triangle(){
            x1=0;
            y1=0;
            x2=0;
            y2=0;
            x3=0;
            y3=0;
            a=0;
            b=0;
            c=0;
        }
        double Calculate(double x1,double y1,double x2,double y2){
            return sqrt(pow(x1-x2,2)+pow(y1-y2,2));
        }
        double GetArea(){
            double p=(a+b+c)/2;
            return sqrt(p*(p-a)*(p-b)*(p-c));
        }
        void Show(){
            cout<<"三边之长："<<a<<ends<<b<<ends<<c<<endl;
            cout<<"周长:"<<2*(a+b+c)<<endl;
            cout<<"面积:"<<GetArea()<<endl;
        }
        void Set(double x1,double y1,double x2,double y2,double x3,double y3){
            a=Calculate(x1,y1,x2,y2);
            b=Calculate(x1,y1,x3,y3);
            c=Calculate(x2,y2,x3,y3);
            if(a+b>c&&a+c>b&&b+c>a){
                this->x1=x1;
                this->y1=y1;
                this->x2=x2;
                this->y2=y2;
                this->x3=x3;
                this->y3=y3;
            }else{
                this->x1=0;
                this->y1=0;
                this->x2=0;
                this->y2=0;
                this->x3=0;
                this->y3=0;
                a=0;
                b=0;
                c=0;
                cout<<"输入的三角形的顶点不能组成一个三角形！将使用默认值！"<<endl;
            }
        }
};

int main(void)
{
   cout<<"圆形"<<endl;
   cout<<"-------------------"<<endl;
   Circle circle(20,30,5);
   circle.Show();
   cout<<endl;
   circle.Set(-20,40,30);
   circle.Show();
   cout<<endl;
   circle.Set(-40,-10,-5);
   circle.Show();
   cout<<endl;
   cout<<"长方形"<<endl;
   cout<<"-------------------"<<endl;
   Rectangle rectangle(20,50);
   rectangle.Show();
   cout<<endl;
   rectangle.Set(40,5);
   rectangle.Show();
   cout<<endl;
   rectangle.Set(-9,10);
   rectangle.Show();
   cout<<endl;
   rectangle.Set(43,-8);
   rectangle.Show();
   cout<<endl;
   cout<<"三角形"<<endl;
   cout<<"-------------------"<<endl;
   Triangle triangle(20,30,40,50,40,20);
   triangle.Show();
   cout<<endl;
   triangle.Set(10,10,50,10,35,10);
   triangle.Show();
   cout<<endl;
   system("pause");
}