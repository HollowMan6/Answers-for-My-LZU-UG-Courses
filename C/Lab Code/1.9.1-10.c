#include<stdio.h>
void swap(int *,int *);
int main(void)
{
	int a = 3;
	int b = 5;
	int *p1 = &a;
	int *p2 = &b;
	swap(p1,p2);
	printf("%d %d\n",*p1,*p2);
}
void swap(int *p1,int *p2)
{
	int p;
	p = *p1;
	*p1 = *p2;
	*p2 = p;
}
