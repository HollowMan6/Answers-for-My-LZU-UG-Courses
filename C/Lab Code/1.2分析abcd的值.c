#include<stdio.h>
int main()
{
	int a,b,c,d;
	a = 10;
	b = a++;
	c = ++a;
	d = 10 * a++;
	printf("a,b,c,d: %d, %d, %d\n",a ,b, c, d);
	return 0;
}