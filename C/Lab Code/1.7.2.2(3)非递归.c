#include<stdio.h>
int main(void)
{
	int f1, f2, f, a, i, n, t;
	f1 = 0;
	f2 = 1;
	a = 1;
	t = 0;
	scanf("%d", &n);
	for (i = 2; i <= n; i++)
	{
		f = f2 + f1;
		t = f2;
		f2 = f;
		f1 = t;
		a += f;
	}
	printf("%d", a);
}