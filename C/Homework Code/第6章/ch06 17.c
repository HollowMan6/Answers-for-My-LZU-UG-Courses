//第六章 第17题
#include <stdio.h>

#include <stdio.h>

int main(void)
{
	const float WINNINGS = 1000000.0f;
	const float INTEREST = 0.08f;
	const float SPENDING = 100000.0f;

	int years = 0;
	float balance = WINNINGS;

	while (balance > 0)
	{
		balance -= SPENDING;
		balance *= 1.0f + INTEREST;
		years++;
	}

	printf("%d 年后, Chuckie 将会取完账户所有的钱"
		" %.2f USD.\n", years, balance);

	return 0;
}