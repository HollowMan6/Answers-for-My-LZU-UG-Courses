//第四章 第1题
#include<stdio.h>
int main()
{
	char first_name[20];
	char last_name[20];

	printf("输入你的名和姓: ");
	scanf("%s %s", first_name, last_name);
	printf("%s, %s\n", last_name, first_name);

	return 0;
}