//第十章 第6题
#include <stdio.h>
#include <stdlib.h>
#include <time.h>


void reverse_array(double *arr, int arr_size);

int main(void)
{

	double test1[9];
	double test2[10];
	double test3[2];

	srand(time(NULL));

	// 用9个double类型的数据初始化数组1
	for (int i = 0; i < 9; i++)
		test1[i] = rand() / (double) RAND_MAX;

	// 用10个double类型的数据初始化数组2
	for (int i = 0; i < 10; i++)
		test2[i] = rand() / (double) RAND_MAX;

	// 用2个double类型的数据初始化数组3
	for (int i = 0; i < 2; i++)
		test3[i] = rand() / (double) RAND_MAX;


	printf("First Test\n");
	printf("%10s: ", "原来");
	for (int i = 0; i < 9; i++)
		printf("%5.2f ", test1[i]);
	putchar('\n');

	reverse_array(test1, 9);
	printf("%10s: ", "反转");
	for (int i = 0; i < 9; i++)
		printf("%5.2f ", test1[i]);
	putchar('\n');


	printf("Second Test\n");
	printf("%10s: ", "原来");
	for (int i = 0; i < 10; i++)
		printf("%5.2f ", test2[i]);
	putchar('\n');

	reverse_array(test2, 10);
	printf("%10s: ", "反转");
	for (int i = 0; i < 10; i++)
		printf("%5.2f ", test2[i]);
	putchar('\n');


	printf("Third Test\n");
	printf("%10s: ", "原来");
	for (int i = 0; i < 2; i++)
		printf("%5.2f ", test3[i]);
	putchar('\n');

	reverse_array(test3, 2);
	printf("%10s: ", "反转");
	for (int i = 0; i < 2; i++)
		printf("%5.2f ", test3[i]);
	putchar('\n');

	return 0;
}

void reverse_array(double *arr, int arr_size)
{
	double tmp;

	for (int i = 0; i < arr_size / 2; i++)
	{
		tmp = arr[i];
		arr[i] = arr[arr_size - 1 - i];
		arr[arr_size - 1 - i] = tmp;
	}
}