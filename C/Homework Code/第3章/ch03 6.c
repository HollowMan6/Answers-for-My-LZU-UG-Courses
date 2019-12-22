// 编程练习 第三章 第6题
#include <stdio.h>
int main(void)
{
	float H20_MASS = 3.0e-23;
	float GRAMS_H20_PER_QUART = 950.;
	float quarts;

	printf("输入水的夸脱数: ");
	scanf("%f", &quarts);
	printf("共有 %f 个分子在 %f 夸脱的水中.\n", quarts * GRAMS_H20_PER_QUART / H20_MASS, quarts);

	return 0;
}
