#include<stdio.h>
#include<stdlib.h>
void main()
{
	int count = 1;
	int n, m, i;
	int converse = 0;
	scanf("%d", &n);
	m = n;
	i = m;
	if (i < 0)
		printf("输入数据错误");
	else
	{
		while (n >= 10)
		{
			count++;
			n = n / 10;
		}
		if (count > 5)
			printf("输入数据超过五位！");
		else
		{
			while (i > 0)
			{
				m = i % 10;
				i /= 10;
				converse = m + converse * 10;
			}
			printf("%d,%d", count, converse);
		}
	}
}