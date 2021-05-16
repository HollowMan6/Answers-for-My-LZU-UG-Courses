//第五章 第5题
#include<stdio.h>
int main(void)
{
	int count, sum, max_count;
	sum = 0;
	count = 1;

	printf("你想相加多少个整数? ");
	scanf("%d", &max_count);
	while (count <= max_count)
	{
		sum = sum + count;
		count++;
	}
	printf("和 = %d\n", sum);

	return 0;
}