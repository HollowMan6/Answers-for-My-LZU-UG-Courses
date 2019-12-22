//第十章 第7题
#include <stdio.h>
#include <stdlib.h>

void copy_ptr(double *target, double *source, int arr_len);
void print_row(double (*arr)[10], int row);

int main(void)
{

	double source[10][10];
	double target[10][10];

	// 随机生成数组数据
	for (int i = 0; i < 10; i++)
		for (int j = 0; j < 10; j++)
			source[i][j] = rand() / (double) RAND_MAX;

	for (int i = 0; i < 10; i++)
		copy_ptr(target[i], source[i], 10);

	printf("%-50s", "源");
	printf("   ");
	printf("%-50s", "目标");
	putchar('\n');
	for (int i = 0; i < 103; i++)
		putchar('-');
	putchar('\n');
	for (int i = 0; i < 10; i++)
	{
		print_row(source, i);
		printf("   ");
		print_row(target, i);
		putchar('\n');
	}

	return 0;
}

void copy_ptr(double *target, double *source, int arr_len)
{

	for (int i = 0; i < arr_len; i++)
	{
		*(target + i) = *(source + i);
	}
}

void print_row(double (*arr)[10], int row)
{

	for (int i = 0; i < 10; i++)
	{
		printf("%.2f ", arr[row][i]);
	}
}