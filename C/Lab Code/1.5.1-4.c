#include<stdio.h>
int main(void)
{
	double s=0;
	int a=1;
	double t=1;
	int i,n;
	
	printf("input total itemnumber:");
	scanf(" %d",&n);
	for(i=1;i<=n;i++)
	{
		t=i;
		s+=a/t;
		a=-a;
	}
	printf("sum = %lf\n",s);
	
	return 0;
}
