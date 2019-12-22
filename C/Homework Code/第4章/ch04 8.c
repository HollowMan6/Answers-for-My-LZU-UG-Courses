//第四章 第8题
#include<stdio.h>
int main(void)
{
	const float KM_PER_MILE = 1.609;
	const float LT_PER_GALLON = 3.785;
	float miles_travelled, gallons_gas_consumed;
	float miles_per_gallon, liters_per_100km;

	printf("输入旅行的英里里程: ");
	scanf("%f", &miles_travelled);
	printf("输入消耗的加仑汽油量: ");
	scanf("%f", &gallons_gas_consumed);

	miles_per_gallon = miles_travelled / gallons_gas_consumed;
	liters_per_100km = 100. / miles_per_gallon * LT_PER_GALLON / KM_PER_MILE;

	printf("公里每加仑: %.1f\n", miles_per_gallon);
	printf("升每百公里: %.1f\n", liters_per_100km);

	return 0;
}
