//第七章 第9题
#include <stdio.h>
#include <stdbool.h>

void flush_input_buffer(void);

int main(void)
{
	bool prime_flag;
	int limit;
	printf("输入一个正整数: \n");
	while (scanf("%d", &limit) != 1 || limit < 1)
	{
		flush_input_buffer();
		printf("输入一个正整数: \n");
	}

	for (int i = 2; i <= limit; i++)
	{
		prime_flag = true;
		for (int j = 2; j < i; j++)
		{
			if (i % j == 0)
			{
				prime_flag = false;
				break; 
			}
		}
		if (prime_flag)
			printf("%d 是素数.\n", i);
	}

	return 0;
}

void flush_input_buffer(void)
{
	while (getchar() != '\n')
		;
}
