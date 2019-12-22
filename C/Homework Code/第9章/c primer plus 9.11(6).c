// 第九章 第6题
#include <stdio.h>

void sort_variables(double *x, double *y, double *z);

int main(void)
{
	double x, y, z;

	printf("输入三个数:\n");
	while(scanf("%lf %lf %lf", &x, &y, &z) == 3)
	{
		putchar('\n');
		printf("x = %f, y = %f, z = %f\n", x, y, z);

		sort_variables(&x, &y, &z);

		putchar('\n');
		printf("x = %f, y = %f, z = %f\n", x, y, z);

		putchar('\n');

		printf("输入三个数:\n");
	}

	return 0;
}

void sort_variables(double *x, double *y, double *z)
{
	double tmp;

	if (*x > *y)
	{
		tmp = *y;
		*y = *x;
		*x = tmp;
	}

	if (*y > *z)
	{
		tmp = *z;
		*z = *y;
		*y = tmp;

		if (*x > *y)
		{
			tmp = *y;
			*y = *x;
			*x = tmp;
		}
	}
}
