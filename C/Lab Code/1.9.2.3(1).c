#include<stdio.h>
int main(void)
{
	int m[3][3];
	int i,j,temp;
	temp = 0;
	for(i=0;i<3;i++)
		for(j=0;j<3;j++)
			scanf("%d",&m[i][j]);
	for(i=0;i<3;i++)
		for(j=i;j<3;j++)
		{
			temp = m[i][j];
			m[i][j] = m[j][i];
			m[j][i] = temp;
		}

	for(i=0;i<3;i++)
	{
		printf("\n");
		for(j=0;j<3;j++)
			printf("%d ",m[i][j]);
	}
	return 0;
}
