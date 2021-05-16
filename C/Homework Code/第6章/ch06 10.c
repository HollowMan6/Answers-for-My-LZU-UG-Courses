//第六章 第10题
#include <stdio.h>

int sum_of_squares(int lower, int upper);

int main(void)
{
	int upper, lower, reads;

	printf("Enter lower and upper integer limits: ");
	while (reads = scanf("%d%d", &lower, &upper), reads == 2 && lower < upper)
	{
		printf("The sums of the squares from %d to %d is %d\n",
			lower * lower, upper * upper, sum_of_squares(lower, upper));
		printf("Enter next set of limits: ");
	}
	printf("Done\n");

	return 0;
}

int sum_of_squares(int lower, int upper)
{
	int sum = 0;

	for (int i = lower; i <= upper; i++)
	{
		sum += i * i;
	}

	return sum;
}
