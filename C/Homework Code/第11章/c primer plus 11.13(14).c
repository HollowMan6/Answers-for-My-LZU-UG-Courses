// 第十一章 第14题
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

void print_error_message(void);

int main(int argc, char *argv[])
{
	double base;
	long power;
	char *end;

	if (argc != 3)
	{
		print_error_message();
		return 1;
	}

	end = argv[1];
	while (*end != '\0')
		end++;
	base = strtod(argv[1], &end);

	if (*end)
	{
		print_error_message();
		return 1;
	}

	end = argv[2];
	while (*end != '\0')
		end++;
	power = strtol(argv[2], &end, 10);

	if (*end)
	{
		print_error_message();
		return 1;
	}

	printf("%f ^ %ld = %f\n", base, power, pow(base, power));

	return 0;
}

void print_error_message(void)
{
	puts("输入格式错误！");
}