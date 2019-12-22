// 编程练习 第二章 第5题
#include<stdio.h>
void br(void);
void ic(void);
int main(void)
{
	br();
	printf(", ");
	ic();
	printf("\n");
	ic();
	printf(",\n");
	br();

	return 0;
}
void br(void)
{
	printf("Brazil, Russia");
}
void ic(void)
{
	printf("India, China");
}