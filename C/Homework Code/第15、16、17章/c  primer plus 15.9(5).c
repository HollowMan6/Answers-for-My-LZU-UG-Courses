#include <stdio.h>
#include <limits.h>

const unsigned int mask = 1 << 31;

unsigned int rotate_l(unsigned int value, int n)
{
	int leftbit;

	for (int i = 0; i < n; i++)
	{
		leftbit = value & mask;
		value <<= 1;
		if (leftbit)
			value |= 1;
	}

	return value;
}

int main(void)
{

	unsigned int value, rotated;
	int n;

	printf("输入一个非负整数(其它符号退出):");
	while (scanf("%u", &value) == 1)
	{
		printf("输入向左旋转指定数量的位(其它符号退出):");
		while (scanf("%d", &n) == 1)
		{
			puts("向左旋转前:");
			for (int i = 0; i < 32; i++)
			{
				if ((value << i) & mask)
					putchar('1');
				else
					putchar('0');
			}
			putchar('\n');
			printf("向左旋转 %d 位后:\n", n);
			rotated = rotate_l(value, n);
			for (int i = 0; i < 32; i++)
			{
				if ((rotated << i) & mask)
					putchar('1');
				else
					putchar('0');
			}
			putchar('\n');
			printf("输入向左旋转指定数量的位(其它符号退出): ");
		}
		 while (getchar() != '\n') 
		 	continue;
		printf("\n输入一个非负整数(其它符号退出): ");
	}
	return 0;
}