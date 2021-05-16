#include<stdio.h>
int main(void)
{
	int r, m, n;
	scanf("%d %d", &m, &n);
	if (m < n)
		r = n;
	n = m;
	m = r;
	r = m % n;
	while (r > 1)
	{
		m = n;
		n = r;
		r = m % n;
	}
	printf("%d\n", n);

	return 0;
}

