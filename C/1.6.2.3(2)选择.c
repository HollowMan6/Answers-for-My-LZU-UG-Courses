#include<stdio.h>
int main(void)
{
	int j, i, t, m, jhc, bjc;
	i=0;
	bjc = 0;
	jhc = 0;
	char a[12] = { 'q','w','e','r','t','y','u','I','o','p','e' };
	for (i = 0; i < 11; i++)
	{
		 for(j=i+1;j<11;j++)
		 {
		 	bjc++;
		 	if(a[i]>a[j])
		 	{
		 		t=a[i];
		 		jhc++;
		 		a[i]=a[j];
		 		jhc++;
		 		a[j]=t;
		 		jhc++;
		 	}
		 }
	}
	for (i = 0; i < 11; i++)
		printf("%c ", a[i]);
	printf("\n比较次数是：%d , 交换次数是：%d", bjc, jhc);

	return 0;
	}
