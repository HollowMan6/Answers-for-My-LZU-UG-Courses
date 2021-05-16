#include<stdio.h>
int main(void)
{
	int x = 1, sum;
	sum = 0;
	
	while(1)
	{
		if(x>200)
		break;
		if(x%3==0)
		sum += x;
		x++;
	}
	printf(" %d\n",sum);
	
	return 0;
}
