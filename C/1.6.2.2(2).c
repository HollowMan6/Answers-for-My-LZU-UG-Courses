#include<stdio.h>
int main(void)
{
	char i, j, k;
	for (i = 'x'; i <= 'z'; i++)
	{
		for (j = 'x'; j <= 'z'; j++)
		{
			if (i != j)
				for (k = 'x'; k <= 'z'; k++)
					if ((k != i) && (j != k))
						if ((k != 'x') && (k != 'z') && (i != 'x'))
							printf("order is a -- %c\tb -- %c\tc -- %c\n", i, j, k);

		}
	}
}
