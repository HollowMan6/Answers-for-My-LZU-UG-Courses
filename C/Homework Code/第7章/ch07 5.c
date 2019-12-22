//第七章 第5题
#include <stdio.h>

#define STOP '#'

int main(void)
{
	char ch;

	printf("输入 (%c 退出):\n", STOP);
	while ((ch = getchar()) != STOP)
	{
		switch (ch)
		{
		case '.':
			printf("!");
			break;
		case '!':
			printf("!!");
			break;
		default:
			printf("%c", ch);
		}
	}

	return 0;
}
