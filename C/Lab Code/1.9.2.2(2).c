#include<stdio.h>
#define N 10
int main(void)
{
	int i,t,*p_max,*p,a[N];
	printf("ÇëÊäÈë%d¸öÊı:",N);
	for(i = 0;i < N;i++)
		scanf("%d",&a[i]);
	p = a;
	p_max = a;
	
	for(i=1;i<N;i++)
	{
		if(*(p+i) > *p_max)
			p_max = p+i;
	}
	printf("max = %d",*p_max);
	return 0;
}
