﻿//第六章 第6题
#include <stdio.h>

int main(void)
{
	long upper = -1, lower = 0;
	int reads;

	printf("This program prints a table of integers with their "
		"squares and cubes.\n");
	do
	{
		printf("Enter lower and upper integer limits (in that order): ");
		reads = scanf("%ld%ld", &lower, &upper);
		if (reads != 2)
		{
			while (getchar() != '\n'); // if read fails, clear input buffer
		}
	} while (lower > upper); // if lower is greater than upper, get new input

	printf("\n");
	// table header
	printf(" Integer       | Square        | Cube          \n");
	printf("---------------|---------------|---------------\n");
	for (long int i = lower; i <= upper; i++)
	{
		printf(" %-14ld| %-14ld| %-14ld\n", i, i * i, i * i * i);
	}
	printf("\n");

	return 0;
}
