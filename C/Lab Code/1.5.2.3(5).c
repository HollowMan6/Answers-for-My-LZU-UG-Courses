#include <stdio.h>
#include <math.h>
int n, k[5];
void search(int m)
{
	int a, b, c, d, e;
	if (m == n)
	{
		a = k[0];
		b = k[1];
		c = k[2];
		d = k[3];
		e = k[4];
		if (((a && (b + c + d + e == 3)) || (!a && (b + c + d + e != 3))) && ((b && (a + c + d + e == 0)) || (!b && (a + c + d + e != 0))) && ((c && (a + b + d + e == 1)) || (!c && (a + b + d + e != 1))) && ((d && (a + b + c + e == 4)) || (!d && (a + b + c + e != 4))) == 1)
			printf("%d %d %d %d %d\n", a, b, c, d, e);
	}
	else
	{
		k[m] = 0; 
		search(m + 1);
		k[m] = 1; 
		search(m + 1);
	}
}
int main()
{
	int m = 0;
	n = 5;
	search(m);

	return 0;
}