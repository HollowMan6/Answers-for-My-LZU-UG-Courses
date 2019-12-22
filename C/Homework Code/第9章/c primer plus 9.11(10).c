// 第九章 第10题
#include <stdio.h>
#include <stdlib.h>

void to_base_n(int integer, int base);

int main(void) {
	int integer, base;

	printf("输入一个十进制的整数并输入需要转换成的进制");
	while (scanf("%d %d", &integer, &base) == 2)
	{
		to_base_n(integer, base);
		putchar('\n');

		printf("输入一个十进制的整数并输入需要转换成的进制");
	}

	return 0;
}

void to_base_n(int integer, int base)
{
	if (base < 2 || 10 < base)
	{
		printf("错误，进制数必须在1到10之间.");
		return;
	}

	if (integer == 0) return;

	if (integer < 0)
	{
		putchar('-');
		integer = abs(integer);
	}

	to_base_n(integer / base, base);
	printf("%d", integer % base);
	return;

}