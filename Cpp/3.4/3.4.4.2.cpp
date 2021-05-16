#include<iostream>
using namespace std;
struct Student
{
	int num; //学号
	char name[20]; //班级注意字节长度
	char classname[20]; //班级
	float score[3]; //三门课程成绩
	float aver_score; //平均分
};
void averagescore(Student stu[], int number)
{
	for( int i = 0; i<= number; i++)
		stu[i].aver_score = (stu[i].score[0]+ stu[i].score[1]+stu[i].score[2])/3.0;
}

int highestscore(Student stu[], int number)
{
	int k = 0;
	int max=0;
	//查找最高平均分并记录在数组中的下标值
	for( int i = 0; i<= number; i++)
	{
		if(max<stu[i].aver_score)
		{
			max=stu[i].aver_score;
			k=i;
		}
		
	}
	return k; //返回最高平均分数组元素的下标
}
int main(void)
{
	Student stu[20];//定义有20 个变量的元素的结构体数组(根据需要确定数组的大小)。
	int stu_number = 0 ; //学生个数
	cout<< "please enter student number:";
	cin >> stu_number ; //输入学生个数
	for( int i = 1; i<= stu_number; i++)
	{
		cout<< "please enter NO. "<< i << " student info : num name class score1 score2 score3\n";
		cin >> stu[i]. num >> stu[i]. name >> stu[i]. classname;
		cin>> stu[i].score[0] >> stu[i].score[1] >> stu[i].score[2];
	}
	averagescore(stu,stu_number);
	int k = highestscore(stu,stu_number);
	cout<<"the score of the best student: score1 score2 score3\n"<<stu[k].score[0] <<ends<< stu[k].score[1] <<ends<< stu[k].score[2]<<endl;
	cout<<"information of he or she: num name class\n";
	cout<<stu[k].num <<ends<< stu[k].name<<ends<<stu[k].classname<<endl;
}
/*
[测试数据]
2 a 1 90 80 70
1 b 2 99 99 99

[思考与扩展]
1.student stu1;直接通过结构体创建新结构
STU stu2; 通过typedef进行类型定义代换
pSTU pstu; 通过指针创建新结构

2. 同用char*时，cin>>读入数据，cout<<输出数据;
*/