// 编程练习 第三章 第5题
#include<stdio.h>
int main(void)
{
	unsigned int SECONDS_PER_YEAR = 31560000;
	unsigned int age;

	printf("你的年龄: ");
	scanf("%u", &age);
	printf("你活了 %u 秒了!\n", SECONDS_PER_YEAR * age);

	return 0;
}