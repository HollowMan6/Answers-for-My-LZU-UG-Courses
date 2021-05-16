//第七章 第6题
#include <stdio.h>
#include <stdbool.h>
#include <ctype.h>

#define STOP '#'

int main(void)
{
	char ch;
	unsigned int ei_count = 0;
	bool e_flag = false;

	printf("输入 (%c 停止):\n", STOP);

	while ((ch = getchar()) != STOP)
	{
		ch = tolower(ch);
		if (ch == 'e')
			e_flag = true;
		else if (ch == 'i')
		{
			if (e_flag)
				ei_count++;
			e_flag = false;
		}
		else
			e_flag = false;

	}

	printf(" 'ei' 出现 %u 次.\n", ei_count);

	return 0;
}
