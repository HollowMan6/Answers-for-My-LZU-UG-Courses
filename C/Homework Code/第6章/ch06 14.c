//第六章 第14题
#include <stdio.h>

int main(void)
{
	int int_array[8], cumulative_sum[8];
	int sum = 0;

	printf("输入八个整数:\n");
	for (int i = 0; i < 8; i++)
	{
		scanf("%d", &int_array[i]);
		sum += int_array[i];
		cumulative_sum[i] = sum;
	}
	printf("\n");
	printf("      整数:");
	for (int i = 0; i < 8; i++)
	{
		printf("%6d ", int_array[i]);
	}
	printf("\n");
	printf("累积之和:");
	for (int i = 0; i < 8; i++)
	{
		printf("%6d ", cumulative_sum[i]);
	}
	printf("\n");

	return 0;
}
