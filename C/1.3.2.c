#include<stdio.h>
void main()
{
	short int a, b;
	short unsigned c, d;
	long int e, f, g, h;
	a = 1;
	b = -1;
	c = a;
	d = b;
	e = 50000;
	f = 2147483648;
	printf("%d, %d\n", a, b);
	printf("%u, %u\n", a, b);
	printf("%u, %u\n", c, d);
	printf("%ld, %ld\n", e, f);
	c = e;
	d = f;
	g = f + 1;
	h = -f - 2;
	printf("%u, %u\n", c, d);
	printf("%d, %d\n", g, h);
	printf("%lu, %lu\n", g, h);
}
