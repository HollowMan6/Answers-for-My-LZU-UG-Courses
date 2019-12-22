// 第八章 第6题
#include <stdio.h>
#include <ctype.h>

int get_first(void);

int main(void)
{
	int ch;

	ch = get_first();
	printf("%c\n", ch);

	return 0;
}

int get_first(void)
{

	int ch, garbage;

	do {
		ch = getchar();
	}
	while (isspace(ch));
		

	while((garbage = getchar()) != '\n' && garbage != EOF)
		;

	return ch;
}