#include<iostream>
using namespace std;
int main()
{
	int monthday[12]={31,28,31,30,31,30,31,31,30,31,30,31};
	int year,month,day,sum;
	cout<< "please enter year month day: ";
	cin>>year>> month>>day; //首先输入三个数： 年 月 日
	while(true)
	{
		sum=0;
		//对输入日期合理性检验
		if(year<=0)
			cout<<"please input a year that has positive value!"<<endl;
		if(month>12||month<1)
			cout<<"please input a valid month!"<<endl;
		if(year%4==0 && (year%100!=0||year%400==0)&&month==2&&(day>29||day<1))
			cout<<"please input a valid day!"<<endl;
		if(day>monthday[month-1]||day<1)
			cout<<"please input a valid day!"<<endl;
		else
			{
				for(int i=1;i<year;i++)
					if(i%4==0 && (i%100!=0||i%400==0))
						sum+=366;
					else
						sum+=365;
				for(int i=1;i<month;i++)
					if(year%4==0 && (year%100!=0||year%400==0)&&i==2)
						sum+=29;
					else
						sum+=monthday[i-1];
				sum+=day;
				sum=sum%7;
				switch(sum)
				{
					case 0:cout<<year<<"年"<<month<<"月"<<day<<"日是星期日"<<endl;break;
					case 1:cout<<year<<"年"<<month<<"月"<<day<<"日是星期一"<<endl;break;
					case 2:cout<<year<<"年"<<month<<"月"<<day<<"日是星期二"<<endl;break;
					case 3:cout<<year<<"年"<<month<<"月"<<day<<"日是星期三"<<endl;break;
					case 4:cout<<year<<"年"<<month<<"月"<<day<<"日是星期四"<<endl;break;
					case 5:cout<<year<<"年"<<month<<"月"<<day<<"日是星期五"<<endl;break;
					case 6:cout<<year<<"年"<<month<<"月"<<day<<"日是星期六"<<endl;break;
				}
			}
		
		//本次循环结束前再次输入三个数据（年月 日），为下次循环计算做准备
		cout<< "please enter year month day: ";
		cin>>year>> month>>day;
	}
}
/*
[思考与扩展]
1. if是二分支，而 switch 是多分支。
*/