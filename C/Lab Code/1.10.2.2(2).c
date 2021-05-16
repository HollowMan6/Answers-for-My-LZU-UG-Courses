#include<stdio.h>
#include<stdlib.h>

typedef struct Node
{
	char data;
	struct Node *next;
}node;

node *createlist()
{
	node *p,*s;
	char letter;
	for(letter='a';letter<='z';letter++)
	{
		s =(node *)malloc(sizeof(node));
		s->data=letter;
		s->next=NULL;
		
		if(letter=='a')
			p=s;
		else
		{
			s->next=p;
			p=s;
		}
	}
	
	return s;
}

void showlist(node *p)
{
	node *q;
	q=p;
	while(q)
	{
		printf("%c",q->data);
		q=q->next;
	}
	printf("\n");
}

void deletelist(node *head)
{
	node *p;
	while(head!=NULL)
	{
		p=head;
		head=head->next;
		free(p);
	}
	
	printf("释放空间成功！\n");
}

void main()
{
	node *head;
	head=createlist();
	showlist(head);
	deletelist(head);
} 
