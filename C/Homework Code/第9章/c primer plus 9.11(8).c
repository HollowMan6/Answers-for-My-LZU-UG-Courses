// 第九章 第8题
#include <stdio.h>
#include <stdlib.h>

double power(double base, int exponent);

int main(void)
{
	double base, output;
	int exponent;

	printf("输入底数和指数");
	while (scanf("%lf %d", &base, &exponent) == 2)
	{
		output = power(base, exponent);

		printf("%f ^ %d = %f \n", base, exponent, output);

		printf("输入底数和指数");
	}

	return 0;
}

double power(double base, int exponent)
{
	double power = base;

	if (base == 0)
	{
		if (exponent == 0)
		{
			printf("警告，0的0次方未定义.将默认输出1\n");
			return 1.0;
		}
		else
			return 0;
	}

	for (int i = 1; i < abs(exponent); i++)
	{
		power *= base;
	}

	if (exponent < 0)
		power = 1 / power;

	return power;
}