#include<iostream>
using namespace std;
#define PI 3.1415926
int main()
{
	//*1. const int PI = 3.1415926;
	double radius, high;
	double volume;
	cout<< "please input two numbers:"<<endl;
	//*2. cout<< "please input radius and high:"<<endl;
	 
	cin>>radius >>high;
	
	volume =PI*radius*radius*high;
	cout<<"radius:"<<radius<<" high:"<<high<<endl;
	cout<<"The volume is:"<<volume<<endl;
	return 0;
}
/*[思考与扩展]
1.见代码处注释*1. 
2.	radius:3 high:0
	The volume is:0
	不正确；
	radius:3.8 high:5
	The volume is:226.823
	正确；
	因为输入浮点数类型时，输入第一个变量为int类型，
	将不读入小数点及以后部分，所以high将读入“.”,造成读入错误。 
3.见代码处注释*2. 
4.见3.4.1.3.p4.cpp
*/
