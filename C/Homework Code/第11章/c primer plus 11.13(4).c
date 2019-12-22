// 第十一章 第4题
#include <stdio.h>
#include <ctype.h>
#include <stdbool.h>
#include <string.h>

#define SIZE 20

char * getword(char *target, int max);

int main(void)
{

	char hello[SIZE] = "Hello, ";
	int space = SIZE - strlen(hello) - 1;

	puts("What's your name?");
	getword(hello + 7, space);
	puts(hello);

	return 0;
}

char * getword(char *target, int max)
{
	char ch;
	int i = 0;
	bool inword = false;

	while ((ch = getchar()) != EOF && i < max)
	{
		if (isspace(ch))
		{
			if (inword)
				break;
			else
			{
				continue;
			}
		}

		if (!inword)
			inword = true;

		*(target + i) = ch;
		i++;
	}

	if (ch != '\n')
		while ((ch = getchar()) != '\n')
			continue;

	return target;
}