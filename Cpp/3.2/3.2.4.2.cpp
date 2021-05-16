#include<iostream>
using namespace std;
double fn(int n){
	int sum=1;
	if(n==0)
		return sum;
	else
		for(int i=1;i<=n;i++)
			sum*=i;
		return sum;
}
double Cnr(int n, int r){
	return fn(n)/(fn(r)*fn(n-r));
}
int main()
{
	int n,r;
	while(true){
		cin>>n>>r;
		if(n==0&&r==0)
			break;
		if(n<1||n<r)
			cout<<"Wrong input"<<endl;
		else
			cout<<Cnr(n,r)<<endl;
	}
}
/*
基本要求：
2、递归见3.2.4.2(递归).cpp
3、3.2.4.2.p2.cpp	3.2.4.2.p1.cpp	3.2.4.2.h
[思考与扩展]
2、函数自身调用函数
3、n所调用的值超出栈大小时
	非递归函数，执行速度快。
4、提供全局变量、全局函数的声明或提供公用数据类型的定义，从而实现分离变异或代码复用。
5、使文件结构更加清晰
*/