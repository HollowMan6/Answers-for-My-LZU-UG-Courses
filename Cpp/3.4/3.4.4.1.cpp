#include<iostream>
using namespace std;
struct Date{
	int year;
	int month;
	int day;
}dates;
int main()
{
	int monthday[12]={31,28,31,30,31,30,31,31,30,31,30,31};
	int sum;
	cout<< "please enter year month day: ";
	cin>>dates.year>> dates.month>>dates.day; //首先输入三个数： 年 月 日
	while(true)
	{
		sum=0;
		//对输入日期合理性检验
		if(dates.year<=0)
			cout<<"please input a year that has positive value!"<<endl;
		if(dates.month>12||dates.month<1)
			cout<<"please input a valid month!"<<endl;
		if(dates.year%4==0 && (dates.year%100!=0||dates.year%400==0)&&dates.month==2&&(dates.day>29||dates.day<1))
			cout<<"please input a valid day!"<<endl;
		if(dates.day>monthday[dates.month-1]||dates.day<1)
			cout<<"please input a valid day!"<<endl;
		else
			{
				for(int i=1;i<dates.year;i++)
					if(i%4==0 && (i%100!=0||i%400==0))
						sum+=366;
					else
						sum+=365;
				for(int i=1;i<dates.month;i++)
					if(dates.year%4==0 && (dates.year%100!=0||dates.year%400==0)&&i==2)
						sum+=29;
					else
						sum+=monthday[i-1];
				sum+=dates.day;
				sum=sum%7;
				switch(sum)
				{
					case 0:cout<<dates.year<<"年"<<dates.month<<"月"<<dates.day<<"日是星期日"<<endl;break;
					case 1:cout<<dates.year<<"年"<<dates.month<<"月"<<dates.day<<"日是星期一"<<endl;break;
					case 2:cout<<dates.year<<"年"<<dates.month<<"月"<<dates.day<<"日是星期二"<<endl;break;
					case 3:cout<<dates.year<<"年"<<dates.month<<"月"<<dates.day<<"日是星期三"<<endl;break;
					case 4:cout<<dates.year<<"年"<<dates.month<<"月"<<dates.day<<"日是星期四"<<endl;break;
					case 5:cout<<dates.year<<"年"<<dates.month<<"月"<<dates.day<<"日是星期五"<<endl;break;
					case 6:cout<<dates.year<<"年"<<dates.month<<"月"<<dates.day<<"日是星期六"<<endl;break;
				}
				cout<<sizeof(Date);
			}
		
		//本次循环结束前再次输入三个数据（年月 日），为下次循环计算做准备
		cout<< "please enter year month day: ";
		cin>>dates.year>> dates.month>>dates.day;
	}
}
/*
[思考与扩展]
1. 
	Date* pdate=dates;
	然后将代码中所有dates.year改为pdate->year
				   dates.month改为pdate->month
				   dates.day改为pdate->day
2. 12字节 可以
*/