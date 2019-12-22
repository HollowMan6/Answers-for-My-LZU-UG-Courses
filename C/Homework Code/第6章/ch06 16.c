//第六章 第16题
#include <stdio.h>

int main(void)
{
	const float DEIRDE_PRINCIPLE = 100.0f;
	const float DAPHNE_PRINCIPLE = 100.0f;
	const float DEIRDE_INTEREST = 0.05f;
	const float DAPHNE_INTEREST = 0.10f;

	// initialize years and balances
	int years = 0;
	float daphne_balance = DAPHNE_PRINCIPLE;
	float deirdre_balance = DEIRDE_PRINCIPLE;

	while (deirdre_balance <= daphne_balance)
	{
		// eq. for compound interest
		deirdre_balance *= 1.0f + DEIRDE_INTEREST;
		// eq. for simple interest
		daphne_balance += DAPHNE_PRINCIPLE * DAPHNE_INTEREST;
		years++;
	}
	printf(" %d 年后, Daphne的投资值 $%.2f 同时 "
		"Deirdre的投资值 $%.2f.\n", years,
		daphne_balance, deirdre_balance);

	return 0;
}