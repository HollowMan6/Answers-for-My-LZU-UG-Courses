//第七章 第1题
#include <stdio.h>
#define STOP '#'

int main(void)
{
	char ch;
	unsigned int spaces = 0, newlines = 0, other = 0;
	printf("Enter input (%c to stop):\n", STOP);
	while ((ch = getchar()) != STOP)
	{
		if (ch == ' ')
			spaces++;
		else if (ch == '\n')
			newlines++;
		else
			other++;
	}
	printf("\n");
	printf("读取的元素个数:\n");
	printf("----------------\n");
	printf("空格: %u\n"
		"换行: %u\n"
		"其它: %u\n", spaces, newlines, other);

	return 0;
}
