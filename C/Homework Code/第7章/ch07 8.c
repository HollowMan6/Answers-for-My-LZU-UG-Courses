//第七章 第8题
#include <stdio.h>
#include <stdbool.h>

#define RATE_1 8.75
#define RATE_2 9.33
#define RATE_3 10.00
#define RATE_4 11.20

#define OVERTIME_HOURS 40.0
#define OVERTIME_MULTIPLIER 1.5
#define TAX_RATE_1 0.15
#define TAX_BRACKET_1 300.0
#define TAX_RATE_2 0.20
#define TAX_BRACKET_2 450.0
#define TAX_RATE_3 0.25

void flush_input_buffer(void);
float calculate_gross_pay(float hours, float rate);
float calulate_taxes(float gross_pay);

int main(void)
{
	bool exit_flag = false;
	int rate_option;
	float rate, hours, gross_pay, taxes;

	while (1) // main program loop
	{

		// print usage instructions
		printf("*****************************************************************\n");
		printf("Enter the number corresponding to the desired pay rate or action:\n");
		printf("1) $%.2f/hr 				2) $%.2f/hr\n", RATE_1, RATE_2);
		printf("3) $%.2f/hr 				4) $%.2f/hr\n", RATE_3, RATE_4);
		printf("5) quit \n");
		printf("*****************************************************************\n");

		scanf("%d", &rate_option);
		switch (rate_option)
		{
		case (1):
			rate = RATE_1;
			break;
		case (2):
			rate = RATE_2;
			break;
		case (3):
			rate = RATE_3;
			break;
		case (4):
			rate = RATE_4;
			break;
		case (5):
			exit_flag = true;
			break;
		default: // invalid input
			flush_input_buffer();
			printf("请输入一个1-5之间的整数.\n\n");
			continue; // repeat main program loop
		}

		if (exit_flag)
			break; // exit program

		printf("输入一周工作的小时数: ");
		while (scanf("%f", &hours) != 1 || hours <= 0)
		{
			flush_input_buffer();
			printf("输入一个正数. \n");
			printf("输入一周工作的小时数: ");
		}

		gross_pay = calculate_gross_pay(hours, rate);
		taxes = calulate_taxes(gross_pay);

		printf("对于 %.1f 小时以 $%.2f/hr速率的工作, 你挣了 $%.2f 并付了"
			" $%.2f 税.\n", hours, rate, gross_pay, taxes);
		printf("\n");

	}

	printf("再见.\n");

	return 0;
}

void flush_input_buffer(void)
{
	while (getchar() != '\n')
		;
}

float calculate_gross_pay(float hours, float rate)
{
	if (hours > OVERTIME_HOURS)
		return OVERTIME_HOURS * rate + (hours - OVERTIME_HOURS) * rate * OVERTIME_MULTIPLIER;
	else
		return hours * rate;
}

float calulate_taxes(float gross_pay)
{
	if (gross_pay > TAX_BRACKET_2)
		return TAX_RATE_3 * (gross_pay - TAX_BRACKET_2) + TAX_RATE_2 * (TAX_BRACKET_2 - TAX_BRACKET_1) + TAX_RATE_1 * TAX_BRACKET_1;
	else if (gross_pay > TAX_BRACKET_1)
		return TAX_RATE_2 * (gross_pay - TAX_BRACKET_1) + TAX_RATE_1 * TAX_BRACKET_1;
	else
		return TAX_RATE_1 * gross_pay;
}