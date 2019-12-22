//第四章 第2题
#include<stdio.h>
int main()
{
	char name[20];
	int name_length;

	printf("输入你的名字: ");
	scanf("%s", name);
	name_length = strlen(name);
	printf("\"%s\"\n", name);
	printf("\"%20s\"\n", name);
	printf("\"%-20s\"\n", name);
	printf("\"%*s\"\n", name_length + 3, name);

	return 0;
}