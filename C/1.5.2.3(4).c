#include<stdio.h>
#include<math.h>
int main(void)
{
	double c, a, b;
	b = 3;
	a = 0;
	c = 0;
	while (fabs(2 * c * c * c - 4 * c * c + 3 * c - 6) > 1e-6)
	{
		if (2 * c * c * c - 4 * c * c + 3 * c - 6 > 0)
		{
			b = c;
		}
		if (2 * c * c * c - 4 * c * c + 3 * c - 6 < 0)
		{
			a = c;
		}
		c = (a + b) / 2;
	}
	printf("%lf", c);

	return 0;
}