#include<stdio.h>
#include<math.h>
int main(void)
{
	int n, m, c, t;
	m = 2;
	c = 0;
	t = 0;
	for (n = 100; n <= 200; n++)
	{
		m = 2;
		while (m <= sqrt(n))
		{
			if (n%m == 0)
			{
				t++;
			}
			m++;
		}
		if (t == 0)
		{
			c++;
			printf("%d ", n);
		}
		t = 0;
	}
	printf("\n%d", c);

	return 0;
}