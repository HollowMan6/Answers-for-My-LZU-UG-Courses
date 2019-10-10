#include<stdio.h>
#include<string.h>
typedef union{long i;int k[5];char c;} DATE;
struct data{int cat;DATE cow;double dog;};
int main(void)
{
	printf("%d",sizeof(struct data));
}
