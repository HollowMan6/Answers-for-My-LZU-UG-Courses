// 第九章 第2题
#include <stdio.h>

void chline(char, unsigned int, unsigned int);

int main(void)
{
	char ch;
	unsigned int i, j;

	printf("输入一个字符和两个整数");
	while (scanf("%c %u %u", &ch, &i, &j) == 3)
	{
		chline(ch, i, j);
		printf("\n");

		while (getchar() != '\n') continue; 
		
		printf("输入一个字符和两个整数 ");
	}

	return 0;
}

void chline(char ch, unsigned int i, unsigned int j)
{
	unsigned int col;
	for (col = 1; col < i; col++)
	{
		putchar(' ');
	}

	for (; col <= j; col++)
	{
		putchar(ch);
	}
}