//第六章 第5题
#include <stdio.h>

void print_spaces(unsigned int n);

int main(void)
{
	char uppercase_letter;
	char c1, c2;

	do 
	{
		printf("输入一个小写字母");
		scanf(" %c", &uppercase_letter);
	} while (uppercase_letter < 'A' || 'Z' < uppercase_letter);

	for (c1 = 'A'; c1 <= uppercase_letter; c1++)
	{
		print_spaces(uppercase_letter - c1);

		for (c2 = 'A'; c2 < c1; c2++)
		{
			printf("%c", c2);

		}
		for (; 'A' <= c2; c2--)
		{
			printf("%c", c2);
		}

		print_spaces(uppercase_letter - c1);
		printf("\n");
	}

	return 0;
}

void print_spaces(unsigned int n)
{
	for (int i = 0; i < n; i++)
	{
		printf(" ");
	}
}
