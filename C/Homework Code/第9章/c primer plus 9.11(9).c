// 第九章 第9题
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
	double dbl_power;

	// handle powers of zero
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

	if (exponent == 0) return 1;

	dbl_power = base * power(base, abs(exponent) - 1); 

	if (exponent < 0) dbl_power = 1 / dbl_power;

	return dbl_power;
}