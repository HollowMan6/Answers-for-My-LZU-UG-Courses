#include<stdio.h>
int prime(int number);
int main(void)
{
	int number;
	printf("请输入一个正数:\n");
	scanf("%d",&number);
	if(prime(number))
		printf("\n %d是素数.",number);
	else
		printf("\n %d不是素数.",number);
}

int prime(int number)
{
	int flag = 1,n;
	for(n=2;n<number/2&&flag==1;n++)
		if(number%n==0)
			flag=0;
	return (flag);
}
