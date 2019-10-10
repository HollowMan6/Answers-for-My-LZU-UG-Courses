#include<stdio.h>
void swap(int *,int *);
int main(void)
{
	int a,b;
	a=3;b=4;
	swap(&a,&b);
	printf(" %d,%d\n",a,b);
}
void swap(int *p1,int *p2)
{
	int temp;
	temp = *p1;*p1 = *p2;*p2 = temp;
}
