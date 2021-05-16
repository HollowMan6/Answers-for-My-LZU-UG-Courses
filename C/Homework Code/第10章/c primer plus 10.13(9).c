//第十章 第9题
#include <stdio.h>
#define ROWS 3
#define COLUMNS 5

void copy_2dim_arr(int rows, int cols, double source[rows][cols],
	               double target[rows][cols]);
void print_2dim_arr(int rows, int cols, double arr[rows][cols]);

int main(void)
{
	double array1[ROWS][COLUMNS] = {{4.3, 5.7, 2.1, 6.6, .8},
						            {5.6, 23.5, 73.2, 12.3, 123},
						            {22.1, 35.3, 6.35, 0.132, 11.1}};
	double array2[ROWS][COLUMNS];

	copy_2dim_arr(ROWS, COLUMNS, array1, array2);

	printf("数组1:\n");
	print_2dim_arr(ROWS, COLUMNS, array1);
	putchar('\n');

	printf("数组2:\n");
	print_2dim_arr(ROWS, COLUMNS, array2);

	return 0;
}

void copy_2dim_arr(int rows, int cols, double source[rows][cols],
	               double target[rows][cols])
{

	for (int i = 0; i < rows; i++)
	{
		for (int j = 0; j < cols; j++)
		{
			target[i][j] = source[i][j];
		}
	}
}

void print_2dim_arr(int rows, int cols, double arr[rows][cols])
{

	for (int i = 0; i < rows; i++)
	{
		for (int j = 0; j < cols; j++)
			printf(" %10.3f ", arr[i][j]);

		putchar('\n');
	}
}
