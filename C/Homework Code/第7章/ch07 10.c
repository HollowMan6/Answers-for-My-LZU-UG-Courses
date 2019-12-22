//第七章 第10题
#include <stdio.h>

#define SINGLE 1
#define HEAD_OF_HOUSEHOLD 2
#define MARRIED_JOINT 3
#define MARRIED_SEPARATE 4
#define EXIT 5

#define RATE_1 0.15f
#define RATE_2 0.28f

void flush_input_buffer(void);

int main(void)
{
	int category;
	float income, bracket, taxes;

	while (1)
	{
		printf("1) 单身  2) 户主  3) 已婚，共有  4) 已婚，离异\n");
		printf("Enter your tax category (1-4) or 5 to quit: ");
		scanf("%d", &category);

		switch (category)
		{
		case (SINGLE):
			bracket = 17850.0;
			break;
		case (HEAD_OF_HOUSEHOLD):
			bracket = 23900.0;
			break;
		case (MARRIED_JOINT):
			bracket = 29750.0;
			break;
		case (MARRIED_SEPARATE):
			bracket = 14875.0;
			break;
		case (EXIT):
			printf("Bye.\n");
			return 0; // Exit Program
		default:
			flush_input_buffer();
			printf("无效输入，请输入1-5之间的整数.\n");
			continue;
		}
		printf("输入你的收入: ");
		while (scanf("%f", &income) != 1 || income < 0)
		{
			flush_input_buffer();
			printf("无效输入，请输入一个正数.\n");
			printf("输入你的收入: ");
		}

		if (income > bracket)
			taxes = RATE_2 * (income - bracket) + RATE_1 * bracket;
		else
			taxes = RATE_1 * income;

		printf("你将要交$%.2f税.\n\n", taxes);
	}
}

void flush_input_buffer(void)
{
	while (getchar() != '\n')
		;
}
