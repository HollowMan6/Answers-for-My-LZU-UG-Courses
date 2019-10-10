#include <iostream>
#include <math.h>
#define pi 3.1415926
using namespace std;
class Shape{
    protected:
        double x,y;
    public:
        Shape(double x,double y){
            this->x=x;
            this->y=y;
        }
        Shape(){
            x=0;
            y=0;
        }
        double GetArea(){
        }
        void Show(){
            cout<<"x："<<x<<" y："<<y<<endl;
        }
        void Set(double x,double y){
            this->x=x;
            this->y=y;
        }
};
class Circle: public Shape{
    private:
        double r;
    public:
        Circle(double x,double y,double r):Shape(x,y){
            this->r=r;
            if(r<0){
                cout<<"半径不可为负数，将使用默认值"<<endl;
                this->r=0;
            }
        }
        Circle():Shape(){
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

class Rectangle:public Shape{
    public:
        Rectangle(double x,double y):Shape(x,y){
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
        Rectangle():Shape(){
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

class Triangle:public Shape{
    private:
        double x2,y2,x3,y3;
        double a,b,c;
    public:
        Triangle(double x,double y,double x2,double y2,double x3,double y3):Shape(x,y){
            a=Calculate(x,y,x2,y2);
            b=Calculate(x,y,x3,y3);
            c=Calculate(x2,y2,x3,y3);
            if(a+b>c&&a+c>b&&b+c>a){
                this->x2=x2;
                this->y2=y2;
                this->x3=x3;
                this->y3=y3;
            }else{
                Triangle();
                cout<<"输入的三角形的顶点不能组成一个三角形！将使用默认值！"<<endl;
            }
        }
        Triangle():Shape(){
            x2=0;
            y2=0;
            x3=0;
            y3=0;
            a=0;
            b=0;
            c=0;
        }
        double Calculate(double x,double y,double x2,double y2){
            return sqrt(pow(x-x2,2)+pow(y-y2,2));
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
        void Set(double x,double y,double x2,double y2,double x3,double y3){
            a=Calculate(x,y,x2,y2);
            b=Calculate(x,y,x3,y3);
            c=Calculate(x2,y2,x3,y3);
            if(a+b>c&&a+c>b&&b+c>a){
                this->x=x;
                this->y=y;
                this->x2=x2;
                this->y2=y2;
                this->x3=x3;
                this->y3=y3;
            }else{
                this->x=0;
                this->y=0;
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
   Circle circle(30,45,35);
   circle.Show();
   cout<<endl;
   circle.Set(50,89,23);
   circle.Show();
   cout<<endl;
   cout<<"长方形"<<endl;
   cout<<"-------------------"<<endl;
   Rectangle rectangle(30,53);
   rectangle.Show();
   cout<<endl;
   rectangle.Set(28,24);
   rectangle.Show();
   cout<<endl;
   cout<<"三角形"<<endl;
   cout<<"-------------------"<<endl;
   Triangle triangle(34,45,89,45,54,67);
   triangle.Show();
   cout<<endl;
   triangle.Set(22,34,67,43,86,64);
   triangle.Show();
   cout<<endl;
}