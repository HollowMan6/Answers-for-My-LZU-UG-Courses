//第六章 第8题
#include <stdio.h>

int main(void)
{
	float num1, num2;
	int reads;

	printf("Enter two floating-point numbers: ");
	while (scanf(" %f %f", &num1, &num2) == 2)
	{
		printf("(%.3f - %.3f)/(%.3f * %.3f) = %.3f\n", num1, num2, num1, num2,
			(num1 - num2) / (num1 * num2));
		printf("Enter two floating-point numbers (enter non-numeric to quit): ");
	}

	return 0;
}
