//第五章 第9题
#include<stdio.h>
#include <stdio.h>
void Temperatures(double fahr);
int main(void)
{
	double fahr;
	printf("这个程序将华氏度转为摄氏度和开尔文度.\n");
	printf("输入一个华氏度(q 退出): ");
	while (scanf("%lf", &fahr) == 1)
	{
		Temperatures(fahr); 
		printf("输入一个华氏度 (q 退出): ");
	}

	printf("结束\n");
}

void Temperatures(double fahr)
{
	const double FAHR_TO_CEL_SCALE = 5.0 / 9.0;
	const double FAHR_TO_CEL_OFFSET = -32.0;
	const double CEL_TO_KEL_OFFSET = 273.16;

	double celsius = (fahr + FAHR_TO_CEL_OFFSET) * FAHR_TO_CEL_SCALE;
	double kelvin = celsius + CEL_TO_KEL_OFFSET;

	printf("%.2f 华氏度等于 %.2f 摄氏度或 %.2f 开尔文度.\n",
		fahr, celsius, kelvin);
}