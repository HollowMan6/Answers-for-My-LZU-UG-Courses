#include<stdio.h>
int gy(int m,int n,int a);
int main(void)
{
	int m,n,a,y; 
	scanf("%d %d", &m, &n);
	gy(m,n,a);
	y=(m*n)/a;
	printf("最小公倍数：%d\n",y);
}
int gy(int m,int n,int a)
{
	int r;
	if (m < n)
		r = n;
	n = m;
	m = r;
	r = m % n;
	while (r > 1)
	{
		m = n;
		n = r;
		r = m % n;
	}
	a=n;
	printf("最大公约数：%d\n", a);
}
