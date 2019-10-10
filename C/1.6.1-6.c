#include<stdio.h>
#define M 3
#define N 4
int main(void)
{
	int max,r,c,row,col;
	static int a[M][N]={{123,94,-10,218},{3,9,10,-83},{45,16,44,-99}};
	max=a[0][0];
	for(row=0;row<M;row++)
		for(col=0;col<N;col++)
			if(a[row][col]>max)
			{
				max=a[row][col];
				r=row;
				c=col;
			}
	printf("max = %d, row = %d, colum = %d\n",max,r,c);
	
	return 0;
}
