#include<iostream>
using namespace std;
//定义全局变量
int n=0;
int func(int x=10);
int main()
{
	//定义局部变量
	int a,b;
	a=5;
	b=func(a);
	//local a=2
	//local b=11
	//parameter x=6
	cout<<"\nlocal a="<<a<<endl
		<<"local b="<<b<<endl
		<<"global n="<<n<<endl;
	//local a=5
	//local b=13
	//global n=1
	a++;
	b=func(a);
	//local a=2
	//local b=12
	//parameter x=7
	cout<<"\nlocal a="<<a<<endl
		<<"local b="<<b<<endl
		<<"global n="<<n<<endl;
	//local a=6
	//local b=14
	//global n=2

	//默认x=10
	func();
	//local a=2
	//local b=13
	//parameter x=11
		system("pause");
}
int func(int x)
{
	//变量以局部定义的方式重新赋值
	int a=1;
	//定义静态变量，能影响到全局变量取值
	static int b=10;
	a++;
	b++;
	x++;
	n++;
	cout<<"\nlocal a="<<a<<endl
		<<"local b="<<b<<endl
		<<"parameter x="<<x<<endl;
	return a+b;
}
/*
[要求：]
3、区分程序中那些是全局变量、局部变量、局部静态变量？这些变量的差别是什么？
参见https://www.cnblogs.com/jiu0821/p/4471895.html
4、说明函数调用时实参和形参对应关系及值传递方式？
值传递，又称单向传递，把实参数值传给形参 ，形参最后的结果不影响实参
（形参改变大小 ，实参大小不变）
[测试数据]

local a=2
local b=11
parameter x=11

local a=10
local b=13
global n=1

local a=2
local b=12
parameter x=12

local a=11
local b=14
global n=2

local a=2
local b=13
parameter x=11

[思考与扩展]
1. 
local a=2
local b=11
parameter x=6

local a=5
local b=13
global n=1

local a=2
local b=11
parameter x=7

local a=6
local b=13
global n=2

local a=2
local b=11
parameter x=11

2.

local a=2
local b=11
parameter x=6

local a=5
local b=13
global n=1

local a=2
local b=12
parameter x=7

local a=6
local b=14
global n=2

local a=2
local b=13
parameter x=11
无变化，因为a,b在函数中重新赋了值，int a,b放在内外等效
*/