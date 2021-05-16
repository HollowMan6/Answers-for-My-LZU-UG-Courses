//第六章 第4题
#include <stdio.h>

int main(void)
{
	char c = 'A';

	for (int i = 1; i < 7; i++)
	{
		for (int j = 1; j <= i; j++)
		{
			printf("%c", c++);
		}
		printf("\n");
	}

	return 0;
