//第五章 第4题
#include<stdio.h>
const float CM_PER_IN = 2.54;
const int IN_PER_FT = 12;
int main(void)
{
	float height_cm, height_in, inches;
	int feet;

	printf("Enter a height in centimeters: ");
	scanf("%f", &height_cm);

	while (height_cm > 0)
	{
		height_in = height_cm / CM_PER_IN; // convert height to inches
		feet = (int)height_in / IN_PER_FT; // get number of feet in height
		inches = height_in - feet * IN_PER_FT; // get remaining inches

		printf("%.1f cm = %d feet, %.1f inches\n",
			height_cm, feet, inches);

		printf("Enter a height in centimeters (<= 0 to quit): ");
		scanf("%f", &height_cm);
	}

	printf("bye\n");
	return 0;