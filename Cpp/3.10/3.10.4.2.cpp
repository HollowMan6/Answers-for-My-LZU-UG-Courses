#include<cstdio>
#include<string.h>
using namespace std;
#define M 100
typedef struct  student 
{
    int sno;		//学号
	char sname[10];     //姓名
    float score[3];//语文、数学、英语
	float sum;//总分
	float avg;//平均分
}Stu;

int menu();//菜单函数
void input(Stu s[],int n);//输入学生信息
void output(Stu s[],int n);//输出学生信息
void query(Stu s[],int n);//从键盘输入一个学号，按学号查询学生信息
void add(Stu s[],int *n);//插入若干学生到学生数组后面，学生人数和学生信息由你从键盘输入
void update(Stu s[],int n);//按学号修改
void del(Stu s[],int *n);//按学号删除
void max(Stu s[],int n);//统计最高分
void read(Stu s[],int *p);//从外部文件读取
void write(Stu s[],int n);//写入到外部文件
void queryname(Stu s[],int n);//从键盘输入一个姓名，按姓名查询学生信息
void querystage(Stu s[],int n);//输入任意的一个课程名（如数学）和一个分数段（如60--70），统计出在此分数段的学生情况

int main()
{
	Stu s[M];
	int n=0;
	while(1)
	{
		switch(menu())
		{
		case 1:
			printf("请输入创建学生信息人数：");
			scanf("%d",&n);
			input(s,n);
			break;
		case 2:
			query(s,n);
			break;
		case 3:
			update(s,n);
			break;
		case 4:
			add(s,&n);
			break;
		case 5:
			del(s,&n);
			break;
		case 6:
			max(s,n);
			break;
		case 7:
			read(s,&n);
			break;
		case 8:
			write(s,n);
			break;
		case 9:
			output(s,n);
			break;
		case 10:
			querystage(s,n);
			break;
		case 11:
			queryname(s,n);
			break;
		case 12:
			return 0;
        default:
			printf("输入有误，请重新选择！\n");
		}
	}
	return 0;
}

int menu()//菜单函数
{
	printf("      --------------------------------------------------------\n");
	printf("      |                                                      |\n");
	printf("      |           欢迎使用本学生信息管理系统                 |\n");
	printf("      |       1.创建学生信息         2.以学号查询学生信息    |\n");
    	printf("      |       3.修改学生信息         4.添加学生信息          |\n");
    	printf("      |       5.删除学生信息         6.统计最高分            |\n");
	printf("      |       7.从文件读取           8.保存到文件            |\n");
	printf("      |       9.输出学生信息         10.查询分数段学生信息   |\n");
	printf("      |       11.以姓名查询学生信息  12.退出                 |\n");
	printf("      |                                                      |\n");
	printf("      --------------------------------------------------------\n");
	int m;
	printf("请选择操作编号:");
	scanf("%d",&m);
	return m;
}

void read(Stu s[],int *p)  //读取文件 
{
	int i;
	FILE *fp;
	fp=fopen("E:\\stu.dat","rb"); 
	if(fp==NULL)
	{
		printf("文件打开失败！\n");
		return ; 
	}
	rewind(fp);
	for(i=0;fread(&s[i],sizeof(Stu),1,fp)==1;i++); 
	*p=i;
	fclose(fp);
	printf("文件读取成功！\n");
}
void write(Stu s[],int n)  //保存文件 
{
	FILE *fp;
	fp=fopen("E:\\stu.dat","wb"); 
	if(fp==NULL)
	{
		printf("文件打开失败！\n");
		return ;
	}
	for(int i=0;i<n;i++)
	{
	fwrite(&s[i],sizeof(Stu),1,fp);
	}
	fclose(fp); 
	printf("文件保存成功！"); 
}
void input(Stu s[],int n)//输入学生信息
{
	int i;
	float sum;
	for(i=0;i<n;i++)
	{
		printf("请输入第%d个学生的信息：\n",i+1);
		printf("请输入学号："); 
		scanf("%d",&s[i].sno);
		printf("请输入姓名：");
		scanf("%s",&s[i].sname);
		printf("请输入三门成绩：");
		scanf("%f%f%f",&s[i].score[0],&s[i].score[1],&s[i].score[2]);
		s[i].sum=s[i].score[0]+s[i].score[1]+s[i].score[2];
		s[i].avg=s[i].sum/3;
	}
	for(i=0;i<n;i++)
	printf("学号:%d,姓名:%s,数学:%.2f,英语:%.2f,语文:%.2f,总分:%.2f,平均分:%.2f\n",s[i].sno,s[i].sname,s[i].score[0],s[i].score[1],s[i].score[2],s[i].sum,s[i].avg);
}
void output(Stu s[],int n)//输出学生信息
{
	int i;
	for(i=0;i<n;i++)
	{
		printf("学号:%d,姓名:%s,数学:%.2f,英语:%.2f,语文:%.2f,总分:%.2f,平均分:%.2f\n",s[i].sno,s[i].sname,s[i].score[0],s[i].score[1],s[i].score[2],s[i].sum,s[i].avg);
	}
	printf("学生信息显示完毕！\n"); 
}
void query(Stu s[],int n)//从键盘输入一个学号，按学号查询学生信息
{
	int i,m;
	printf("请输入要查询的学号：\n");
	scanf("%d",&m);
	for(i=0;i<n;i++)
	{
		if(s[i].sno==m)
		printf("学号:%d,姓名:%s,数学:%.2f,英语:%.2f,语文:%.2f,总分:%.2f,平均分:%.2f\n",s[i].sno,s[i].sname,s[i].score[0],s[i].score[1],s[i].score[2],s[i].sum,s[i].avg);
	}
	printf("查询完毕！\n");
}
void queryname(Stu s[],int n)//从键盘输入一个姓名，按姓名查询学生信息
{
	int i;
	char name[20];
	printf("请输入要查询的姓名：\n");
	scanf("%s",name);
	for(i=0;i<n;i++)
	{
		if(strcmp(s[i].sname,name)==0)
		printf("学号:%d,姓名:%s,数学:%.2f,英语:%.2f,语文:%.2f,总分:%.2f,平均分:%.2f\n",s[i].sno,s[i].sname,s[i].score[0],s[i].score[1],s[i].score[2],s[i].sum,s[i].avg);
	}
	printf("查询完毕！\n");
}
void querystage(Stu s[],int n)//按科目和分数段查找学生 
{
	int a,b,i,count;
	int select=0;
	int total=0;
	printf("请选择科目\n1.数学2.英语3.语文：");
	scanf("%d",&select);
	printf("请输入分数段：");
	scanf("%d%d",&a,&b);
	switch(select)
	{
		case 1:
			for(i=0;i<count;i++)
			{
				if(s[i].score[0]>=a&&s[i].score[0]<=b)
				total++;
			}
			break;
		case 2:
			for(i=0;i<count;i++)
			{
				if(s[i].score[1]>=a&&s[i].score[1]<=b)
				total++;
			}
			break;
		case 3:
			for(i=0;i<count;i++)
			{
				if(s[i].score[2]>=a&&s[i].score[2]<=b)
				total++;
			}
			break;
	}
	printf("该科目分数段有%d个人\n",total); 
}
void add(Stu s[],int *n)//添加若干学生信息 ，由键盘录入 
{
	int m;
	printf("请输入添加的人数:");
	scanf("%d",&m);
	for(int i=0;i<m;i++)
	{
		printf("请依次输入添加的第%d个学生的学号，姓名和三门成绩:",i+1);
        scanf("%d%s%f%f%f",&s[i+*n].sno,s[i+*n].sname,&s[i+*n].score[0],&s[i+*n].score[1],&s[i+*n].score[2]);
        s[i+*n].sum=s[i+*n].score[0]+s[i+*n].score[1]+s[i+*n].score[2];
		s[i+*n].avg=s[i+*n].sum/3;
		printf("添加成功！\n") ;
	}
	*n=*n+m;
}
void update(Stu s[],int n)//按学号修改
{
	int i,h;
	char ch;
    printf("请输入学号：");
	scanf("%d",&h);	
    for(i=0;;i++)	
    {	
        if(s[i].sno==h)	
        {	
         printf("学号：%d，姓名：%s,语文：%.2f,数学：%.2f，英语：%.2f,总分：%.2f，平均分：%.2f\n",s[i].sno,s[i].sname,s[i].score[0],s[i].score[1],s[i].score[2],s[i].sum,s[i].avg);
	     printf("是否进行修改?(y/n)");	
         scanf("%s",&ch); 	
         if(ch=='y')	
           {  		
              printf("请输入学号："); 
		      scanf("%d",&s[i].sno);
		      printf("请输入姓名：");
		      scanf("%s",&s[i].sname);
		      printf("请输入三门成绩：");
		      scanf("%f%f%f",&s[i].score[0],&s[i].score[1],&s[i].score[2]);
		      s[i].sum=s[i].score[0]+s[i].score[1]+s[i].score[2];
	       	  s[i].avg=s[i].sum/3;
              printf("修改成功！\n");	
	       }	
        break;
        } 
    }
}
void del(Stu s[],int *n)//按学号删除
{
	int i,h;
	char ch;
    printf("请输入学号：");
	scanf("%d",&h);	
    for(i=0;n!=NULL;i++)	
    {	
        if(s[i].sno==h)	
        {	
         printf("学号：%d，姓名：%s,语文：%.2f,数学：%.2f，英语：%.2f,总分：%.2f，平均分：%.2f\n",s[i].sno,s[i].sname,s[i].score[0],s[i].score[1],s[i].score[2],s[i].sum,s[i].avg);
	     printf("是否进行删除?(y/n)");	
         scanf("%s",&ch); 	
         if(ch=='y')	
           {  		
              s[i]=s[i+1];		
              *n-=1;	
              printf("删除成功！\n");	
	       }	
        break;
        } 
    }
}
void max(Stu s[],int n)//统计最高分
{
	int i,j,t=0;
	for(i=0;i<n-1;i++)
	{
		for(j=i+1;j<n;j++)
		{
			if(s[i].sum<s[j].sum)
			{
				t=j;
			}
		}
	}	
	printf("学号:%d 姓名:%s 成绩:%.2f %.2f %.2f 总分:%.2f 平均分:%.2f \n",s[t].sno,s[t].sname,s[t].score[0],s[t].score[1],s[t].score[2],s[t].sum,s[t].avg);
}