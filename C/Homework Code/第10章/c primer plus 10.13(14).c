//第十章 第14题
#include <stdio.h>
#define ROWS 3
#define COLUMNS 5

double compute_row_average(double *array, int cols);
double compute_array_average(int rows, int cols, double array[rows][cols]);
double largest_value(int rows, int cols, double array[rows][cols]);

int main(void)
{
	double data[ROWS][COLUMNS];

	for (int i = 0; i < ROWS; i++)
	{
		printf("输入%d个double类型的数据: ", COLUMNS);
		for (int j = 0; j < COLUMNS; j++)
		{
			scanf("%lf", data[i] + j);
		}
	}

	for (int i = 0; i < ROWS; i++)
	{
		printf("\t行%d的均值是: %.3f\n", i + 1,
			   compute_row_average(data[i], COLUMNS));
	}

	printf("整个数组的均值是: %.3f\n",
		   compute_array_average(ROWS, COLUMNS, data));

	printf("数组中最大值是: %.3f\n",
		   largest_value(ROWS, COLUMNS, data));

	return 0;
}

double compute_row_average(double *array, int cols)
{
	double total = 0;
	for (int i = 0; i < cols; i++)
		total += array[i];

	return total / cols;
}

double compute_array_average(int rows, int cols, double array[rows][cols])
{
	double total = 0;
	for (int i = 0; i < rows; i++)
		for (int j = 0; j < cols; j++)
			total += array[i][j];

	return total / (rows * cols);
}

double largest_value(int rows, int cols, double array[rows][cols])
{
	double max = array[0][0];
	for (int i = 0; i < rows; i++)
		for (int j = 0; j < cols; j++)
			if (array[i][j] > max)
				max = array[i][j];

	return max;
}