#include<stdio.h>
#define SQ(y) (y)*(y)
int main(void)
{
	int a,sq;
	printf("input a number:");
	scanf("%d",&a);
	sq=160/SQ(a+1);
	printf("sq = %d\n",sq);
	
	return 0;
}
