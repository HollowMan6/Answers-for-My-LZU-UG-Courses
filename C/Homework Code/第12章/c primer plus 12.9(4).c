//第12章 第2题
#include <stdio.h>

unsigned int counter(void);

int main(void)
{
	int iterations = 0;
	printf("你想要调用函数多少次? ");
	scanf("%d", &iterations);
	for (int i = 0; i < iterations; i++)
		printf("函数返回值： %u\n", counter());

	return 0;
}

unsigned int counter(void)
{
	static unsigned int call_count = 0;
	return ++call_count;
}