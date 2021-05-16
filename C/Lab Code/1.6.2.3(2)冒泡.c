#include<stdio.h>
int main(void)
{
	int j, i, t, jhc, bjc;
	bjc = 0;
	jhc = 0;
	char a[12] = { 'q','w','e','r','t','y','u','I','o','p','e' };
	for (i = 0; i < 11; i++)
	{
		for (j = 0; j < 11 - i; j++)
		{
			bjc++;
			if (a[j] > a[j + 1])
			{
				t = a[j];
				jhc++;
				a[j] = a[j + 1];
				jhc++;
				a[j + 1] = t;
				jhc++;
			}
		}
	}
	for (i = 0; i < 11; i++)
		printf("%c ", a[i]);
	printf("\n比较次:%d , 交换次:%d", bjc, jhc);

	return 0;
}
