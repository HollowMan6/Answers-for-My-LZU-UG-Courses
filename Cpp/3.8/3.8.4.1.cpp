#include <iostream>
#include <cmath>
using namespace std;
const float PI = (float)3.14159; //给出圆周率
class Shape
{ //基类
public:
     virtual double getArea() {}
     virtual double getPerimeter() {}
     virtual void Show() {}
};
class Triangle : public Shape //Triangle 类的定义
{
public:                                                        //外部接口
     Triangle();                                               //无参构造函数
     Triangle(int x1, int y1, int x2, int y2, int x3, int y3); //有参构造函数
     void Set(int x1, int y1, int x2, int y2, int x3, int y3); //设置三个顶点
     double getArea();                                         //计算三角形面积
     double getPerimeter();                                    //计算三角形周长
     void Show();                                              //取三条边长，三角形面积，三角形周长

private:
     int xx1, yy1, xx2, yy2, xx3, yy3;
};
//类的实现
Triangle::Triangle() //无参构造函数初始化数据成员
{
     xx1 = 0;
     yy1 = 0;
     xx2 = 0;
     yy2 = 0;
     xx3 = 0;
     yy3 = 0;
}
Triangle::Triangle(int x1, int y1, int x2, int y2, int x3, int y3) //有参构造函数初始化数据成员
{
     xx1 = x1;
     yy1 = y1;
     xx2 = x2;
     yy2 = y2;
     xx3 = x3;
     yy3 = y3;
}
void Triangle::Set(int x1, int y1, int x2, int y2, int x3, int y3) //设置三个顶点
{
     xx1 = x1;
     yy1 = y1;
     xx2 = x2;
     yy2 = y2;
     xx3 = x3;
     yy3 = y3;
}

double Triangle::getArea() //计算三角形的面积
{
     double a, b, c;

     a = sqrt((xx1 - xx2) * (xx1 - xx2) + (yy1 - yy2) * (yy1 - yy2));
     b = sqrt((xx2 - xx3) * (xx2 - xx3) + (yy2 - yy3) * (yy2 - yy3));
     c = sqrt((xx3 - xx1) * (xx3 - xx1) + (yy3 - yy1) * (yy3 - yy1));
     return a * b * abs(abs(1 - ((a * a + b * b - c * c) / (2 * a * b)) * ((a * a + b * b - c * c) / (2 * a * b)))) / 2;
}

double Triangle::getPerimeter() //计算三角形的周长
{
     double a, b, c;
     a = sqrt((xx1 - xx2) * (xx1 - xx2) + (yy1 - yy2) * (yy1 - yy2));
     b = sqrt((xx2 - xx3) * (xx2 - xx3) + (yy2 - yy3) * (yy2 - yy3));
     c = sqrt((xx3 - xx1) * (xx3 - xx1) + (yy3 - yy1) * (yy3 - yy1));
     return a + b + c;
}
void Triangle::Show() //显示三边长，面积，周长
{
     double a, b, c;
     a = sqrt((xx1 - xx2) * (xx1 - xx2) + (yy1 - yy2) * (yy1 - yy2));
     b = sqrt((xx2 - xx3) * (xx2 - xx3) + (yy2 - yy3) * (yy2 - yy3));
     c = sqrt((xx3 - xx1) * (xx3 - xx1) + (yy3 - yy1) * (yy3 - yy1));
     cout << "三边为：" << a << " " << b << " " << c << " " << endl;
     cout << "面积：" << getArea() << endl;
     cout << "周长：" << getPerimeter() << endl;
}
class Rectangle : public Shape //Rectangle类的定义
{
public:                             //外部接口
     Rectangle();                   //无参构造函数
     Rectangle(int NewL, int NewW); //有参构造函数
     void Set(int NewL, int NewW);  //设置长度，宽度
     double getArea();              //计算矩形面积
     double getPerimeter();         //计算矩形周长
     void Show();                   //取长度 ，宽度 ，矩形面积，矩形周长

private: //私有数据成员
     int Length, Width;
};
//类的实现
Rectangle::Rectangle() //无参构造函数初始化数据成员
{
     Length = 0;
     Width = 0;
}
Rectangle::Rectangle(int NewL, int NewW) //有参构造函数初始化数据成员
{
     Length = NewL;
     Width = NewW;
}
void Rectangle::Set(int NewL, int NewW) //设置长度，宽度
{
     Length = NewL;
     Width = NewW;
}

double Rectangle::getArea() //计算矩形的面积
{
     return Length * Width;
}

double Rectangle::getPerimeter() //计算矩形的周长
{
     return 2 * (Length + Width);
}
void Rectangle::Show() //显示长度 ，宽度 ，矩形面积，矩形周长
{
     cout << "长：" << Length << endl;
     cout << "宽：" << Width << endl;
     cout << "面积：" << getArea() << endl;
     cout << "周长：" << getPerimeter() << endl;
}

class Circle : public Shape //Circle类的定义
{
public:
     Circle();                      //无参构造函数
     Circle(int r);                 //有参构造函数
     void set(int x, int y, int r); //设置圆心，半径
     double getArea();              //取面积
     double getPerimeter();         //计算圆周长
     void Show();                   //显示圆心，直径，周长，面积
private:
     int xx, yy, radius;
};
Circle::Circle() //无参构造函数初始化数据成员
{
     radius = 0;
}
Circle::Circle(int r) //有参构造函数初始化数据成员
{
     radius = r;
}

void Circle::set(int x, int y, int r) //设置圆心，半径
{
     xx = x;
     yy = y;
     radius = r;
}

double Circle::getPerimeter() //取面积
{
     return 2 * PI * radius;
}
double Circle::getArea() //取面积
{
     return PI * radius * radius;
}
void Circle::Show() //显示 圆心，直径，周长，面积
{
     cout << "圆心:(" << xx << "," << yy << ")" << endl;
     cout << "直径:" << 2 * radius << endl;
     cout << "周长:" << getPerimeter() << endl;
     cout << "面积:" << getArea() << endl;
}

//主函数实现
int main()
{
     int x1, y1, x2, y2, x3, y3;
     double a, b, c;
     int x, y, r;
     int L, W;
     int which;
     int flag = 1;

     while (flag)
     {
          do
          {
               cout << "0 == 退出 ,1 == 三角形, 2 == 正方形, 3 == 圆形" << endl;
               cin >> which;
          } while (which < 0 || which > 3);
          switch (which)
          {
          case 0:
          {
               flag = 0;
               break;
          }
          case 1:
          {
               cout << "输入三个顶点:" << endl;
               cin >> x1 >> y1 >> x2 >> y2 >> x3 >> y3;
               a = sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
               b = sqrt((x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3));
               c = sqrt((x3 - x1) * (x3 - x1) + (y3 - y1) * (y3 - y1));
               if ((a + b) > c && (a + c) > b && (b + c) > a) //判断能否构成三角形
               {
                    Triangle p(x1, y1, x2, y2, x3, y3); //声明 Triangle对象
                    p.Show();
               }
               else
                    printf("输入错误!");
               system("pause");
               system("cls");
               break;
          }
          case 2:
          {
               cout << "输入长、宽:" << endl;
               cin >> L >> W;
               Rectangle K(L, W);
               K.Show();
               system("pause");
               system("cls");
               break;
          }

          case 3:
          {
               cout << "输入圆心：";
               cin >> x >> y;
               cout << "输入半径：";
               cin >> r;
               Circle C(r);
               C.Show();
               system("pause");
               system("cls");
               break;
          }
               if (flag == 0)
                    break;
          }
     }

     return 0;
}