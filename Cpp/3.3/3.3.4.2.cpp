#include <iostream>
using namespace std;
//第一种方法：
int num[5]={1,2,3,4,5};
int sco[5][5]={99,98,86,93,91,
			   88,84,92,93,95,
			   89,87,87,86,79,
			   78,97,89,76,98,
			   67,96,75,67,94};
int avsco[5];
void averscore ( int sco[][5] ,int avsco[5] )
{
	for(int i=0;i<5;i++)
	{
		int sum=0;
		for(int j=0;j<5;j++)
			sum+=sco[i][j];
		avsco[i]=sum/5;
	}
}
int avercourse( int (*psco)[5],int n )
{
	int sum=0;
	for(int i=0;i<5;i++)
		sum+=psco[i][n-1];
	return sum/5;
}

//第二种方法：
int stu_info[5][7]={1,99,98,86,93,91,0,
					2,88,84,92,93,95,0,
					3,89,87,87,86,79,0,
					4,78,97,89,76,98,0,
					5,67,96,75,67,94,0};
void averscore ( int stu_info[5][7] )
{
	for(int i=0;i<5;i++)
	{
		int sum=0;
		for(int j=1;j<6;j++)
			sum+=sco[i][j];
		stu_info[i][6]=sum/5;
	}
}

int avercourse(int (*psco)[7], int n )
{
	int sum=0;
	for(int i=1;i<6;i++)
		sum+=psco[i][n];
	return sum/5;
}

//测试
int main(void)
{
	averscore(sco,avsco);
	avercourse(sco,3);
	for(int j=0;j<5;j++)
		cout<<avsco[j]<<ends;
	cout<<endl;
	cout<<avercourse(sco,3)<<endl;
	averscore(stu_info);
	for(int i=0;i<5;i++)
	{
		for(int j=0;j<7;j++)
			cout<<stu_info[i][j]<<ends;
		cout<<endl;
	}
	system("pause");
}
/*
[思考与扩展]
1.进行数据过滤
*/