#include<stdio.h>
#define SQ1(y) ((y)*(y))
int SQ2(int y)
{
	return ((y)*(y));
}
int main(void)
{
	int i=1;
	printf("macro:\n");
	while(i<=5)
		printf("function:\n");
	printf("function:\n:");
	i=1;
	while(i<=5)
	printf("%d\n",SQ2(i++));
}
