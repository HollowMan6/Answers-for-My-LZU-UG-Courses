//第五章 第6题
#include<stdio.h>
double cubed(double n); // prototype declaration for cubed
int main(void)
{
	double input;
	printf("输入一个数来立方: ");
	scanf("%lf", &input);

	printf("%.3f 的立方是 %.3f\n", input, cubed(input));

	return 0;
}

double cubed(double n)
{
	return n * n * n;
}