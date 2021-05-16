//第十章 第5题
#include <stdio.h>

double max_difference(double *arr, int arr_size);

int main(void)
{
	double test[5] = {1.0, 3.4, 2.2, 0.9, 2.0};

	printf("最大值和最小值之间的差是");
	for (int i = 0; i < 5; i++)
		printf("%.1f ", test[i]);
	printf("is %.1f\n", max_difference(test, 5));

	return 0;
}

double max_difference(double *arr, int arr_size)
{
	double max = arr[0];
	double min = arr[0];

	for (int i = 0; i < arr_size; i++)
	{
		if (arr[i] > max)
			max = arr[i];
		else if (arr[i] < min)
			min = arr[i];
	}

	return max - min;
}