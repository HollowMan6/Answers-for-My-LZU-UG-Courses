#include<stdio.h>
#include<math.h>
int main(void)
{
	int n, m, t;
	m = 2;
	t = 0;
	for (n = 2; n <= 2000; n++)
	{
		m = 1;
		while (m <= n/2)
		{
			if (n%m == 0)
			{
				t += m;
			}
			m++;
		}
		if (t == n)
		{
			printf("%d ", n);
		}
		t = 0;
	}

	return 0;
}