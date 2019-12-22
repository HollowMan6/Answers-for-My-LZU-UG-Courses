#include<stdio.h>
int i = 0,j = 1,k = 2,l = 0;
void main()
{
	l = ++i||--j&&k;
	printf("%d %d  %d %d\n",i,j,k,l);
}
