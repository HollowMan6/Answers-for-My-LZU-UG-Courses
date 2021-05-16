#include<iostream>
using namespace std;

int main()
{
	//定义变量 
	double worktime, wage_per_hour,salary;
	//提示输入 
	/*
	 while(true)
	{
	*/
	cout<<"Please input employee's work time and wage_per_hour:"; 
	cin>>worktime;
	/*
	if(worktime==0)
		break;
	*/
	cin>>wage_per_hour;
	
	
	//分支结构
	//工时小于40 
	if(worktime<=40)
		salary=worktime*wage_per_hour;
		
	//工时小于60大于40 
	else if(worktime<=60)
		salary=40*wage_per_hour+(worktime-40)*wage_per_hour*1.5;
		
	//工时大于60 
	else
		salary=40*wage_per_hour+20*wage_per_hour*1.5+(worktime-60)*wage_per_hour*3;
	cout<<"The employee's wage:"<<salary<<endl;
	/*
	}
	*/
 } 
 /*[思考与扩展]
 1.见 3.4.1.4(switch).cpp
 2.修改见用/*包围的代码
 3. 30 4
 	45 4.5
	70 5 
 */
