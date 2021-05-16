#include<stdio.h>
#include<math.h>
int main(void)
{
	int n;
	double m, s;
	s = 0;
	n = 1;
	m = 3;
	while (m / ((2 * n)*(2 * n)) > 0.001)
	{
		m = 2 * n + 1;
		s += pow(-1, n - 1)*m / ((2 * n)*(2 * n));
		n++;
	}
	printf("s = %lf", s);
}
