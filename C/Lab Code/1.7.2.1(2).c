#include<stdio.h>
int n = 0;
int func(int x);
int main(void)
{
	int a,b;
	a=5;
	b=func(a);
	printf("\na=%d b=%d n=%d",a,b,n);
	a++;
	b=func(a);
	printf("\na=%d b=%d n=%d\n",a,b,n);
}
int func(int x)
{
	int a=1;
	static int b = 10;
	a++;
	b++;
	x++;
	n++;
	printf("\na=%d b=%d x=%d",a,b,x);
	return a+b;
}
