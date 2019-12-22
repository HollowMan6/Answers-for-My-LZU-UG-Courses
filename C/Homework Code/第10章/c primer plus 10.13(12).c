//第十章 第12题
#include <stdio.h>
#define MONTHS 12 
#define YEARS 5

void print_annual_average(int years, int months, const float data[years][months]);
void print_monthly_averages(int years, int months, const float data[years][months]);

int main(void)
{
	const float rain[YEARS][MONTHS] = {{4.3,4.3,4.3,3.0,2.0,1.2,0.2,0.2,0.4,2.4,3.5,6.6},
									   {8.5,8.2,1.2,1.6,2.4,0.0,5.2,0.9,0.3,0.9,1.4,7.3},
									   {9.1,8.5,6.7,4.3,2.1,0.8,0.2,0.2,1.1,2.3,6.1,8.4},
									   {7.2,9.9,8.4,3.3,1.2,0.8,0.4,0.0,0.6,1.7,4.3,6.2},
									   {7.6,5.6,3.8,2.8,3.8,0.2,0.0,0.0,0.0,1.3,2.6,5.2}};


	print_annual_average(YEARS, MONTHS, rain);
	print_monthly_averages(YEARS, MONTHS, rain);

	return 0;
}

void print_annual_average(int years, int months, const float data[years][months])
{
	float subtotal, total;

	printf(" YEAR RAINFALL (inches)\n");

	for (int year = 0, total = 0; year < years; year++)
	{
		for (int month = 0, subtotal = 0; month < months; month++)
			subtotal += data[year][month];

		printf("%5d %15.1f\n", 2010 + year, subtotal);
		total += subtotal;
	}
	printf("\nThe yearly average is %.1f inches.\n\n", total/years);	
}

void print_monthly_averages(int years, int months, const float data[years][months])
{
	float subtotal;

	printf("MONTHLY AVERAGES:\n\n");
	printf(" Jan Feb Mar Apr May Jun Jul Aug Sep Oct "); printf(" Nov Dec\n");

	for (int month = 0; month < months; month++)
	{ 
		for (int year = 0, subtotal = 0; year < years; year++)
			subtotal += data[year][month];

		printf("%4.1f ", subtotal/YEARS);
	}
	printf("\n");
}