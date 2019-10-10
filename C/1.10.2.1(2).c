#include<stdio.h>
struct Node
{
	char *s;
	struct Node *q;
};
int main()
{
	struct Node a[]={{"Clinton",a+1},{"Bush",a+2},{"Obama",a}};
	struct Node *p = a;
	printf("%s\n",p->s);
	printf("%s\n",p->q->s);
	printf("%s\n",p->q->q->s);
	return 0;
}
