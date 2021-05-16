#include<iostream>
using namespace std;

int main()
{
	//定义变量 
	int worktime;
	double wage_per_hour,salary;
	//提示输入 
	cout<<"Please input employee's work time and wage_per_hour:"; 
	cin>>worktime>>wage_per_hour;
	
	
	//switch分支结构
	
	switch(worktime/10)
	{
	//工时小于40 
		case 0:
		case 1:
		case 2:
		case 3:
			salary=worktime*wage_per_hour;break;
		
	//工时小于60大于40 
		case 4:
		case 5:
		salary=40*wage_per_hour+(worktime-40)*wage_per_hour*1.5;break;
		
	//工时大于60 
		default:
		salary=40*wage_per_hour+20*wage_per_hour*1.5+(worktime-60)*wage_per_hour*3;break;
	}
	cout<<"The employee's wage:"<<salary<<endl;
 } 
