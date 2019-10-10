#include<stdio.h>
int main(void)
{
	int *p,a[5] = {1,3,5,7,9};
	p = a;
	printf("%d\n",*p++);
	printf("%d",*p);
}
