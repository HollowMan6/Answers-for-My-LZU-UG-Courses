// 编程练习 第三章 第1题
#include <stdio.h>
#include <limits.h>
#include <float.h>

int main(void)
{
	int int_overflow;
	int MAX_INTEGER = INT_MAX;
	float flt_overflow, flt_underflow;
	float MIN_FLOAT = FLT_MIN;
	float MAX_FLOAT = FLT_MAX;

	int_overflow = INT_MAX + 1;
	flt_overflow = FLT_MAX * 2.;
	flt_underflow = FLT_MIN / 2.;

	printf("最大整数: %d \最大整数 加 1: %d\n", INT_MAX, int_overflow);
	printf("最大浮点数: %f \最大浮点数 乘 2: %f\n", FLT_MAX, flt_overflow);
	printf("最小浮点数: %f \t最小浮点数 除以 2: %f\n", FLT_MIN, flt_underflow);

	return 0;
}