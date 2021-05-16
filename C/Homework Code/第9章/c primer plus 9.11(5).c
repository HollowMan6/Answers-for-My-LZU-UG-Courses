// 第九章 第5题
#include <stdio.h>

void larger_of(double * x, double * y);

int main(void)
{
	double x, y;

	printf("输入两个数: ");
	while(scanf("%lf %lf", &x, &y) == 2)
	{
		printf("x = %f, y = %f\n", x, y);

		larger_of(&x, &y);

		printf("x = %f, y = %f\n", x, y);	

		printf("输入两个数: ");
	}

	return 0;
}

void larger_of(double * x, double * y)
{
	if (*x > *y) *y = *x;
	else *x = *y;
}