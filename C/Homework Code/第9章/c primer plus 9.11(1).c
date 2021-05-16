// 第九章 第1题
#include <stdio.h>

double min(double, double);

int main(void)
{
	double x, y;

	printf("输入两个double类型的数（输错则退出）");
	while (scanf("%lf %lf", &x, &y) == 2)
	{
		printf("%.3f 和 %.3f 的最小值是 %.3f.\n", x, y, min(x,y));
		printf("输入两个double类型的数（输错则退出）");
	}

	return 0;
}

double min(double x, double y)
{
	return x < y ? x : y;
}