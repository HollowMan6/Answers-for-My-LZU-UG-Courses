// 第九章 第3题
#include <stdio.h>

void printgrid(char ch, unsigned int cols, unsigned int rows);

int main(void)
{
	char ch;
	unsigned int rows, cols;

	printf("");
	while (scanf("%c %u %u", &ch, &rows, &cols) == 3)
	{
		printgrid(ch, cols, rows);
		printf("输入一个字符，行数和列数");
	}

	return 0;
}

void printgrid(char ch, unsigned int cols, unsigned int rows)
{
	for (unsigned int i = 0; i < rows; i++)
	{
		for (unsigned int j = 0; j < cols; j++)
		{
			putchar(ch);
		}
		putchar('\n');
	}
}