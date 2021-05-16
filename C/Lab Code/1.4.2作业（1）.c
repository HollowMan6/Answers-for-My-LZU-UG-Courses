#include<stdio.h>
main()
{
	int x,j,k;
	scanf("%d",&x);
	j = 2 * x + 1;
	k = 3 * x - 8;
	if(x < 1)
	  printf("y = %d",x);
	else
	{
		if(1 <= x < 10)
		 printf("y = %d",j);
		 else
		 if(x >= 10)
		 printf("y = %d",k);
	} 
}
