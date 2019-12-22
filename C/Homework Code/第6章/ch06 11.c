//第六章 第8题
#include <stdio.h>

int main(void)
{
	int int_array[8];
	int i;

	printf("输入八个整数:\n");
	for (i = 0; i < 8; i++)
	{
		scanf("%d", &int_array[i]);
	}
	for (i--; i >= 0; i--)
	{
		printf("%d", int_array[i]);
	}
	printf("\n");

	return 0;
}