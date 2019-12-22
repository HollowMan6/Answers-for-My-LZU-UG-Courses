// 编程练习 第二章 第7题
#include<stdio.h>
void smile(void);
int main(void)
{
	smile();
	smile();
	smile();
	printf("\n");
	smile();
	smile();
	printf("\n");
	smile();

	return 0;
}
void smile(void)
{
	printf("Smile!");
}