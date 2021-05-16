//第七章 第4题
#include <stdio.h>

#define STOP '#'

int main(void)
{
	char ch;

	printf("输入 (%c 退出):\n", STOP);
	while ((ch = getchar()) != STOP)
	{
		if (ch == '.')
			printf("!");
		else if (ch == '!')
			printf("!!");
		else
			printf("%c", ch);
	}

	return 0;
}
