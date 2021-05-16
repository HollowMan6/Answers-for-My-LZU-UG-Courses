// 编程练习 第三章 第7题
#include <stdio.h>
int main(void)
{
	float PINTS_PER_CUP = .5;
	float OUNCES_PER_CUP = 8;
	float TBS_PER_CUP = 2 * OUNCES_PER_CUP; 
	float TSP_PER_CUP = 3 * TBS_PER_CUP; 
	float cups;

	printf("输入杯数:");
	scanf("%f", &cups);
	printf("%f 杯相当于:\n", cups);
	printf("%f 品脱\n", cups * PINTS_PER_CUP);
	printf("%f 盎司\n", cups * OUNCES_PER_CUP);
	printf("%f 汤勺\n", cups * TBS_PER_CUP);
	printf("%f 茶勺\n", cups * TSP_PER_CUP);

	return 0;
}