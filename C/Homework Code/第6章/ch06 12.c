//第六章 第12题
#include <stdio.h>

int main(void)
{
	long int limit;
	float sign = 1.0f;
	float series1 = 0, series2 = 0;

	printf("输入相加的项数: ");
	scanf("%ld", &limit);

	for (long int i = 1; i <= limit; i++)
	{
		series1 += 1.0f / i;
		series2 += (1.0f / i) * sign;
		sign = -sign;
	}

	printf("第一个公式前 %ld 项和是: %.5f\n", limit, series1);
	printf("第二个公式前 %ld 项和是: %.5f\n", limit, series2);


	return 0;
}
