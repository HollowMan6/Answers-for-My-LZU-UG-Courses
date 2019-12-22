//第五章 第2题
#include<stdio.h>
int main(void)
{
	int input;
	int i = 0;

	printf("输入一个整数: ");
	scanf("%d", &input);
	while (i <= 10)
	{
		printf("%d\n", input + i);
		i++;
	}

	return 0;
}