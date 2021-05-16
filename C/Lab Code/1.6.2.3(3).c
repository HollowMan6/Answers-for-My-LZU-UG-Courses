#include<stdio.h>
int main(void)
{
	static int m[] = { 0,5,13,19,21,27,56,64,75,80,88,92,105,121,160 };
	int n, i, a, b;
	i = 1;
	a = 0;
	b = 15;
	scanf("%d", &n);
	while (1)
	{
		if (i == 0)
		{
			printf("ÎÞ´ËÊý");
			break;
		}
		i = (a + b) / 2;
		if (m[i] < n)
			a = i;
		if (m[i] > n)
			b = i;
		if (m[i] == n)
		{
			printf("%d", i + 1);
			break;
		}
		if (b - a == 1)
			i = 0;
	}
	return 0;
}
