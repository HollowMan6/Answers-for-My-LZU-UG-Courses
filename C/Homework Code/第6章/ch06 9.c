//第六章 第9题
#include <stdio.h>

float calculate(float n1, float n2);

int main(void)
{
	float num1, num2;
	int reads;

	printf("输入两个浮点数：");
	while (scanf(" %f %f", &num1, &num2) == 2)
	{
		printf("(%.3f - %.3f)/(%.3f * %.3f) = %.3f\n", num1, num2, num1, num2,
			calculate(num1, num2));
		printf("输入两个浮点数（空格退出）");
	}

	return 0;
}

float calculate(float n1, float n2)
{
	return (n1 - n2) / (n1 * n2);
}
