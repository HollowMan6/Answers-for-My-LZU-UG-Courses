#include<stdio.h>
int main(void)
{
	char a[5]={'#','#','#','#','#'};
	int i,j;
	
	char space=' ';
	for (i=0;i<5;i++)
	{
		printf("\n");
		for(j=0;j<=i;j++)
		{
			printf("%c",space);
			printf("%c",a[j]);
		}
	}
	printf("\n");
	
	return 0;
}
