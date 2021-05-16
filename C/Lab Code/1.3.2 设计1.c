#include<stdio.h>
void main()
{
	short int a, b, c;
	float x, y, z, j, k ,l;
	long int u, n;
	a = 3;
	b = 4;
	c = 5;
	x = 1.2;
	y = 2.4;
	z = -3.6;
	u = 51274;
	n = 128765;
	char c1, c2;
	c1 = 'a';
	c2 = 'b';
	printf("a = %d, b = %d, c = %d\n", a, b, c);
	printf("x = %lf, y = %lf z = %lf\n", x, y, z);
	{j = x + y; k = y + z; l = z + x; printf("x + y = %lf, y + z = %lf, z + x = %lf\n", j, k, l);}
	printf("u = %ld, n = %ld\n", u, n);
	printf("c1 = %c or %d\n", c1, c1);
	printf("c2 = %c or %d\n", c2, c2);
}
