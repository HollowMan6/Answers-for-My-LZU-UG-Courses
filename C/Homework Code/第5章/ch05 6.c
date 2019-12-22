//第五章 第6题
#include<stdio.h>
int main(void)
{
	int sum, count, max_count;
	sum = 0;
	count = 1;

	printf("你想相加多少个平方和? ");
	scanf("%d", &max_count);
	while (count <= max_count)
	{
		sum = sum + count * count;
		count++;
	}
	printf("头 %d 个平方和是: %d\n", max_count, sum);

	return 0;
}