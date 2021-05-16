// 第十一章 第3题
#include <stdio.h>
#include <ctype.h>
#include <stdbool.h>

#define SIZE 20

char * getword(char *target);

int main(void)
{

	char hello[SIZE] = "Hello, ";

	printf("What's your name?");
	getword(hello + 7);
	puts(hello);

	return 0;
}

char * getword(char *target)
{

	char ch;
	int i = 0;
	bool inword = false;

	while ((ch = getchar()) != EOF)
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