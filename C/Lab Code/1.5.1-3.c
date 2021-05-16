#include<stdio.h>
int main(void)
{
	int n,m;
	
	for(n=1;n<=10;n++)
	{
		if(n==5)
		break;
		printf(" %d",n);
	}
	printf("\n");
	for(m=1;m<=10;m++)
	{
		if (m==5)
		continue;
		printf(" %d",m);
	}
	
	return 0;
}
