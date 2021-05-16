#include<stdio.h>
struct data
{
	int n;
	float score;
};

void main()
{
	struct data a[3]={1001,87,1002,72,1003,90},*p=a;
	printf("%d\n",(p++)->n);
	printf("%d\n",(p++)->n);
	printf("%d\n",p->n++);
	printf("%d\n",(*p).n++);
}
