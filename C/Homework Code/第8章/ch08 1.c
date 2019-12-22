// 第八章 第1题
#include <stdio.h>

int main(void)
{
	int count = 0;

	while (getchar() != EOF)
	{
		count++;
	}
	printf("字符总数: %d\n", count);

	return 0;
}