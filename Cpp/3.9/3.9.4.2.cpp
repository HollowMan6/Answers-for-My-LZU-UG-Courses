#include <iostream>
#include <fstream>
using namespace std;
struct person
{
    int num;
    string name;
    string sex;
    string birth;
    string place;
} per[10];
int main()
{
	//p1实现数据写入，p2实现文件读取
    ofstream p1("1.txt");
    int n;
    cout<<"请输入要输入员工的人数：";
    cin>>n;
    for(int i=0; i<n; i++)
    {
        cout<<"请输入第"<<i+1<<"个人的数据："<<endl<<"编号：";
        cin>>per[i].num;
        cout<<"姓名：";
        cin>>per[i].name;
        cout<<"性别：";
        cin>>per[i].sex;
        cout<<"出生日期：";
        cin>>per[i].birth;
        cout<<"职位：";
        cin>>per[i].place;
    }
    for(int i=0; i<n; i++)
    {
        p1<<"编号："<<per[i].num<<" 姓名："<<per[i].name<<" 性别："<<per[i].sex<<" 生日："<<per[i].birth<<" 职位："<<per[i].place<<endl;
    }
    ifstream p2("2.txt");
    int x=0;
    char str[100][1024];
    while(!p2.eof())
    {
        p2.getline(str[x++],1024,'\n');
    }
    for(int i=0; i<x; i++)
        cout<<str[i]<<endl;
    p2.close();
}