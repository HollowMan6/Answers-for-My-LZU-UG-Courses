//第七章 第3题
#include <stdio.h>
#include <ctype.h>

#define STOP 0

int main(void)
{
	int even_count = 0, even_sum = 0, odd_count = 0, odd_sum = 0;
	float even_avg, odd_avg;
	int input;

	printf("输入整数 (0取消):\n");
	while (scanf("%d", &input) == 1 && input != STOP)
	{
		if (input % 2 == 0)
		{
			even_count++;
			even_sum += input;
		}
		else
		{
			odd_count++;
			odd_sum += input;
		}
	}

	even_avg = even_sum / (float)even_count;
	odd_avg = odd_sum / (float)odd_count;

	printf("输入的偶数个数: %d\n", even_count);
	printf("偶数的平均值: %.2f\n", even_avg);
	printf("输入的奇数个数: %d\n", odd_count);
	printf("奇数的平均值: %.2f\n", odd_avg);

	return 0;
}
