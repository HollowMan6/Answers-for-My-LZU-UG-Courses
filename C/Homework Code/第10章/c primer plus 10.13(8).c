//第十章 第8题
#include <stdio.h>

void copy_ptrs(double *target, double *source_start, double *source_end);

int main(void)
{
	double source[7] = {2.4, 5.9, 7.8, 1.5, 3.3, 5.3, 6.8};
	double target[3];

	copy_ptrs(target, source + 2, source + 5);

	for (int i = 0; i < 7; i++)
		printf("%.1f ", source[i]);
	putchar('\n');

	for (int i = 0; i < 3; i++)
		printf("%.1f ", target[i]);
	putchar('\n');

	return 0;
}

void copy_ptrs(double *target, double *source_start, double *source_end)
{
	for (double *ptr = source_start; ptr < source_end; ptr++, target++)
		*target = *ptr;
}