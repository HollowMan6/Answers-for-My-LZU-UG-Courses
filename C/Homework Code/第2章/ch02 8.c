// 编程练习 第二章 第8题
#include<stdio.h>
void one_three(void);
void two(void);
int main(void)
{
	printf("starting now:\n");
	one_three();
	printf("done!");

	return 0;
}
void one_three(void)
{
	printf("one\n");
	two();
	printf("three\n");
}
void two(void)
{
	printf("two\n");
}