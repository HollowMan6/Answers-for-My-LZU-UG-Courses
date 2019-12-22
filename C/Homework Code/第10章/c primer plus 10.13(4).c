//第十章 第4题
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define SIZE 10

int index_of_max(double *arr, int arr_size);

int main(void)
{
	double test[SIZE];

	srand(time(NULL));

	for (int i = 0; i < SIZE; i++)
		test[i] = rand() / (double) RAND_MAX;

	printf("%5s ", "下标");
	for (int i = 0; i < SIZE; i++)
		printf("| %6d ", i);
	printf("\n");
	printf("%5s ", "值");
	for (int i = 0; i < SIZE; i++)
		printf("| %6.4f ", test[i]);
	printf("\n");
	printf("\n");

	printf("最大值的下标是 %d\n", index_of_max(test, SIZE));

	return 0;
}

int index_of_max(double *arr, int arr_size)
{
	int index_of_max = 0;
	for (int i = 1; i < arr_size; i++)
		if (*(arr + i) > *(arr + index_of_max))
			index_of_max = i;

	return index_of_max;
}