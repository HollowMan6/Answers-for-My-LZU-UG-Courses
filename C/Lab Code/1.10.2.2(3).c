#include<stdio.h>
#include<stdlib.h>
#include<string.h>
typedef struct Student
{
	char ID[20];
	char name[20];
	int score1;
	int score2;
	int score3;
	double average;
	struct Student *next;
}Student;
Student *AppendNode(Student *head);
Student *InsertNode(Student *head);
Student *DeleteNode(Student *head);
Student *Start(Student *head);
Student *Sort(Student *head);
void DisplayLink(Student *head);
void FreeMemory(Student *head);

int main()
{
	int a;
	int k;
	
	Student *head = NULL;
	if(head==NULL)
	{
		head=Start(head);
	}
	
	while(k)
	{
		DisplayLink(head);
		printf("1 增加数据\n");
		printf("2 插入数据\n");
		printf("3 删除数据\n");
		printf("4 数据排序\n");
		printf("5 显示数据\n");
		printf("6 退出\n");
		printf("选择\n");
		scanf(" %d",&a);
		switch(a)
		{
			case 1:
				head=AppendNode(head);
				break;
			case 2:
				head=InsertNode(head);
				break;
			case 3:
				head=DeleteNode(head);
				break;
			case 4:
				head=Sort(head);
				break;
			case 5:
				break;	
			case 6:
				k=0;
				break;
			default:
				printf("请输入有效选择！\n");
		} 
	}
	
	FreeMemory(head);
	return 0; 
}

Student *Start(Student *head)
{
	Student *p=NULL;
	Student *pr = head;
	
	p=(Student * )malloc(sizeof(Student));
	head=p;
	pr=p;
	
	strcpy(pr->ID,"201300015");
	strcpy(pr->name,"金娃");
	pr->score1=98;
	pr->score2=99;
	pr->score3=100;
	
	p=(Student *)malloc(sizeof(Student));
	pr->next=p;
	pr=pr->next; 
	strcpy(pr->ID,"201302103");
	strcpy(pr->name,"木娃");
	pr->score1=67;
	pr->score2=83;
	pr->score3=56;
	
	p=(Student *)malloc(sizeof(Student));
	pr->next=p;
	pr=pr->next; 
	strcpy(pr->ID,"201201823");
	strcpy(pr->name,"水娃");
	pr->score1=74;
	pr->score2=69;
	pr->score3=85;
	
	p=(Student *)malloc(sizeof(Student));
	pr->next=p;
	pr=pr->next; 
	strcpy(pr->ID,"201102568");
	strcpy(pr->name,"火娃");
	pr->score1=76;
	pr->score2=91;
	pr->score3=89;
	
	p=(Student *)malloc(sizeof(Student));
	pr->next=p;
	pr=pr->next; 
	strcpy(pr->ID,"200901639");
	strcpy(pr->name,"土娃");
	pr->score1=98;
	pr->score2=99;
	pr->score3=100;
	
	pr->next=NULL;
	
	return head; 
}

void DisplayLink(Student *head)
{
	Student *p=head;
	int j=1;
	printf("序号   学号      名字    语文   数学   外语  平均分\n");
	while(p!=NULL)
	{
		p->average=(p->score1+p->score2+p->score3)/3.0;
		printf("%3d %10s %6s %6d %6d %6d  %6.1lf\n",j,p->ID,p->name,p->score1,p->score2,p->score3,p->average);
		p=p->next;
		j++;
	}
}

Student *AppendNode(Student *head)
{
	
	Student *p=NULL;
	Student *pr=head;
	char ID[20];
	char name[20];
	int score1;
	int score2;
	int score3;
	p=(Student *)malloc(sizeof(Student));
	if(p==NULL)
	{
		printf("内存不足！");
		exit(0);
	}
	if(head==NULL)
	{
		head=p;
	}
	else
	{
		while(pr->next!=NULL)
		{
			pr=pr->next;
		}
		pr->next=p;
	}
	pr=p;
	printf("请输入学号：");
	scanf(" %s",&ID);
	printf("请输入名字：");
	scanf(" %s",&name);
	printf("请依次输入语文，数学，外语成绩：");
	scanf(" %d%d%d",&score1,&score2,&score3);
	strcpy(pr->ID,ID);
	strcpy(pr->name,name);
	pr->score1=score1;
	pr->score2=score2;
	pr->score3=score3;
	pr->next=NULL;
	return head;
}

void FreeMemory(Student *head)
{
	Student *p=head,*pr=NULL;
	while(p!=NULL)
	{
		pr=p;
		p=p->next;
		free(pr);
	}
}

Student *DeleteNode(Student *head)
{
	Student *p=head,*pr=head;
	int i,j;
	printf("删除第几个数据？");
	scanf(" %d",&i);
	
	for(j=1;j<i;j++)
	{
		pr=p;
		p=p->next;
	}
	if(p==head)
	{
		head=p->next;
	}
	else
	{
		pr->next=p->next;
	}
	free(p);
	
	return head;
}

Student *InsertNode(Student *head)
{
	Student *pr=head,*p=head,*temp=NULL;
	int i,j;
	char ID[20];
	char name[20];
	int score1;
	int score2;
	int score3;
	p=(Student *)malloc(sizeof(Student));
	printf("请输入学号：");
	scanf(" %s",&ID);
	printf("请输入名字：");
	scanf(" %s",&name);
	printf("请依次输入语文、数学、外语成绩：");
	scanf(" %d%d%d",&score1,&score2,&score3);
	p->next=NULL;
	strcpy(p->ID,ID);
	strcpy(p->name,name);
	p->score1=score1;
	p->score2=score2;
	p->score3=score3;
	printf("插入到第几个？");
	scanf(" %d",&i);
	
	for(j=1;j<i;j++)
	{
		temp=pr;
		pr=pr->next;
	} 
	if(pr==head)
	{
		p->next=head;
		head=p;
	}
	else
	{
		pr=temp;
		p->next=pr->next;
		p->next=p;
	}
	
	return head;
}

Student *Sort(Student *head)
{
	Student *pr=head,*p=head,temp1;
	int i=1,j,k;
	
	while(p->next!=NULL)
	{
		p=p->next;
		i++;
	}
	p=head;
	for(j=1;j<i;j++)
	{
		p=p->next;
		for(k=0;k<(i-j);k++)
		{
			if(pr->average<p->average)
			{
				temp1=*pr;
				temp1.next=p->next;
				*pr=*p;
				*p=temp1;
				pr->next=p;
			}
			p=p->next;
			pr=pr->next;
		}
		p=head;
		pr=head;
	}
	return head;
}