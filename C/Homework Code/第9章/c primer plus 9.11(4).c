// 第九章 第4题
#include <stdio.h>

double harmonic_mean(double, double);

int main(void)
{
	double x, y;

	printf("调和平均数\n");
	printf("输入两个数: ");
	while (scanf("%lf %lf", &x, &y) == 2)
	{
		printf("%f\n", harmonic_mean(x, y));

		printf("输入两个数: ");
	}

	return 0;
}

double harmonic_mean(double x, double y)
{
	return 2 / (1 / x + 1 / y);
}