#include<stdio.h>
int main(void)
{
	char a[20] = "0123456789abcdefg";
	int *ptr = (int*)a;
	ptr++;
	printf("%c\n",*ptr);
}
