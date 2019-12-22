//第四章 第6题
#include<stdio.h>
#include <string.h>
int main(void)
{
	char first_name[20];
	char last_name[20];

	printf("输入名和姓: ");
	scanf("%s %s", first_name, last_name);
	printf("\n");
	printf("%s %s\n", first_name, last_name);
	printf("%*lu %*lu\n", 
		(int)strlen(first_name), strlen(first_name),
		(int)strlen(last_name), strlen(last_name));
	printf("\n");
	printf("%s %s\n", first_name, last_name);
	printf("%-*lu %-*lu\n", 
		(int)strlen(first_name), strlen(first_name),
		(int)strlen(last_name), strlen(last_name));
	printf("\n");

	return 0;
}