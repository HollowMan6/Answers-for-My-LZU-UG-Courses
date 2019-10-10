#include <iostream>
#include <string.h>
using namespace std;
char Str1[20],Str2[10];
int main()
{
	int i;
	//用字符数组
	cin.getline(Str1,10);
	cin.getline(Str2,9);
	int len1=strlen(Str1);
	int len2=strlen(Str2);
	for(i=0;i<len2;i++)
		Str1[len1+i]=Str2[i];
	Str1[len1+len2]='\0';
	cout<<Str1<<endl;
	//用strcat
	char str1[15]="Using ";
	char str2[7]="strcat";
	strcat(str1,str2);
	cout<<str1<<endl;
	//用string 方法定义字符串变量
	string s1="Using ";
	string s2="String";
	cout<<s1+s2<<endl;
	system("pause");
}
/*
[思考与扩展]
字符串有结束符，字符数组没有结束符
*/