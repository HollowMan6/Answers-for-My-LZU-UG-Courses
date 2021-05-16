#include<stdio.h>
void main()
{
	int a = 0,b = 2,c = 1,d;
	d = a++||++b&&++c;
	printf("%d, %d, %d, %d\n",d,a,b,c);
}
