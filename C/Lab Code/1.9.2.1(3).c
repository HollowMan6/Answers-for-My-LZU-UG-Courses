#include<stdio.h>
void testf(int **p)
{
	*p += 1;
}
int main(void)
{
	int *n,m[2];
	n = m;
	m[0]=1;
	m[1]=8;
	testf(&n);
	printf("Data value is %d\n",*n);
}
