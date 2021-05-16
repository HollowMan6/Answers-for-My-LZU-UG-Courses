//第14章 第11题
#include <stdio.h>
#include <math.h>
void transform(const double * source, double * target, int n, double (*func)(double))
{
	for (int i = 0; i < n; i++)
		target[i] = func(source[i]);

	return;
}

double squared(double x)
{
	return x * x;
}

double cubed(double x)
{
	return x * x * x;
}

int main(void)
{
	
	double source[20];
	double target[20];

	for (int i = 0; i < 20; i++)
		source[i] = i;

	transform(source, target, 20, sin);
	for (int i = 0; i < 20; i++)
	{
		printf("sin(%.2f) = %.2f\n", source[i], target[i]);
	}
	puts("");

	transform(source, target, 20, tan);
	for (int i = 0; i < 20; i++)
	{
		printf("tan(%.2f) = %.2f\n", source[i], target[i]);
	}
	puts("");

	transform(source, target, 20, squared);
	for (int i = 0; i < 20; i++)
	{
		printf("%.2f ^ 2 = %.2f\n", source[i], target[i]);
	}
	puts("");

	transform(source, target, 20, cubed);
	for (int i = 0; i < 20; i++)
	{
		printf("%.2f ^ 3 = %.2f\n", source[i], target[i]);
	}
	puts("");

	return 0;
}