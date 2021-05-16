#include<stdio.h>
int main(void)
{
	char space = ' ';
	int i, j, a[6][10];
	for (i = 0; i < 6; i++)
	{
		a[0][i] = 1;
		a[i][i] = 1;
		a[i][0] = 1;
		for (j = 1; j < i; j++)
		{
			a[i][j] = (a[i - 1][j - 1] + a[i - 1][j]);
		}
	}
	for (i = 0; i < 6; i++)
	{
		printf("\n");
		for (j = 0; j <= i; j++)
		{
			printf("%c", space);
			printf("%d", a[i][j]);
		}
	}
	return 0;
}