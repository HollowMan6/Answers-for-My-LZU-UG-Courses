#include <cstdio>
#include <stdlib.h>
#include <string.h>
using namespace std;
typedef struct PersonalInfo
{
    char name[50];
    char address[30];
    char telno[30];
    char postcode[30];
    char mail[30];
    struct PersonalInfo *next;
} INFO;
INFO *head;
void Initiate();
void Menu();
void Create(); //的功能是：创建新的通讯录。
void Add();    // 在通讯录的末尾，写入新的信息，并返回选单
void Find();   //查找记录
void Alter();  //修改记录如果未找到要修改的人，则提示通讯录中没有此人的信息，并返回选单。
void Delete(); //删除某人的信息，如果未找到要删的人，提示通讯录中没有此人的信息，并返回选单。
void List();   //的功能是：显示通讯录中的所有记录。
//初始化
void Initiate()
{
    if ((head = (INFO *)malloc(sizeof(INFO))) == NULL)
        return;
    head->next = NULL;
}
//显示菜单
void Menu()
{
    printf("**************欢迎使用通讯录系统**************");
    printf("\n");
    printf("\n");
    printf(" 1.创建通讯录。\n");
    printf(" 2.插入信息。\n");
    printf(" 3.查询记录\n");
    printf(" 4.修改记录\n");
    printf(" 5.删除记录！\n");
    printf(" 6.显示所有记录\n");
    printf(" 0.退出通讯录\n");
    printf(" 请输入0~6 \n");
}
//创建通讯录
void Create()
{
    INFO *p1[100], *p2;
    int m, i;
    printf("请输入创建个数:");
    scanf("%d", &m);
    for (i = 1; i <= m; i++)
    {
        p1[i] = (INFO *)malloc(sizeof(INFO));
        printf("请输入第%d条信息!\n", i);
        printf("姓名:\n");
        scanf("%s", &p1[i]->name);
        printf("地址:\n");
        scanf("%s", &p1[i]->address);
        printf("电话:\n");
        scanf("%s", &p1[i]->telno);
        printf("邮编:\n");
        scanf("%s", &p1[i]->postcode);
        printf("电子邮件:\n");
        scanf("%s", &p1[i]->mail);
        p1[i]->next = NULL;
        if (head->next == NULL)
            head->next = p1[i];
        else
        {
            for (p2 = head; p2->next != NULL; p2 = p2->next)
                ; //找到结点尾
            p2->next = p1[i];
        }
    }
    printf("信息已添加！\n");
    return; //保存到链表
}
//添加通讯录信息
void Add()
{
    INFO *p, *q;
    if ((q = (INFO *)malloc(sizeof(INFO))) == NULL)
        exit(1);
    printf("请输入要添加的信息!\n");
    printf("姓名:\n"); //添加信息
    scanf("%s", &q->name);
    printf("地址:\n");
    scanf("%s", q->address);
    printf("电话:\n");
    scanf("%s", q->telno);
    printf("邮编:\n");
    scanf("%s", q->postcode);
    printf("电子邮件:\n");
    scanf("%s", q->mail);
    for (p = head; p->next != NULL; p = p->next)
        ;
    p->next = q;
    q->next = NULL;
    printf("此信息已添加!");
    return;
}
//查找通讯录信息
void Find()
{
    INFO *p;
    char name[50];
    if (head->next == NULL)
    {
        printf("此通讯录为空!\n");
        return;
    }
    printf("请输入要查找的姓名:\n");
    scanf("%s", &name);
    for (p = head->next; p != NULL; p = p->next)
    {
        if (strcmp(p->name, name) == 0)
        {
            printf("姓名\t地址\t邮编\t电话\t电子邮件\n");
            printf("%s\t%s\t%s\t%s\t%s\n", p->name, p->address, p->postcode, p->telno,p->mail);
        }
        else if (p->next == NULL)
            printf("无此信息!\n");
    }
}
//修改通讯录信息
void Alter()
{
    char name[50]; //先查找 后删除
    INFO *p, *p1;
    if (head->next == NULL)
    {
        printf("此通讯录为空!\n");
        return;
    }
    printf("请输入要修改的姓名：\n");
    scanf("%s", name);
    for (p = head->next; p != NULL; p = p->next)
    {
        if (strcmp(p->name, name) == 0)
            break;
        else if (p->next == NULL)
        {
            printf("无此信息!\n");
            return;
        }
    }
    p1 = (INFO *)malloc(sizeof(INFO));
    printf("姓名:\n"); //添加信息
    scanf("%s", p1->name);
    strcpy(p->name, p1->name);
    printf("性别:\n");
    scanf("%s", p1->address);
    strcpy(p->address, p1->address);
    printf("电话:\n");
    scanf("%s", p1->telno);
    strcpy(p->telno, p1->telno);
    printf("邮编:\n");
    scanf("%s", p1->postcode);
    strcpy(p->postcode, p1->postcode);
    printf("电子邮件:\n");
    scanf("%s", p1->mail);
    strcpy(p->mail, p1->mail);
    printf("此信息已修改！\n");
    //显示修改的信息
    printf("姓名\t地址\t邮编\t电话\t电子邮件\n");
    printf("%s\t%s\t%s\t%s\t%s\n", p->name, p->address, p->postcode, p->telno,p->mail);
    free(p1);
}
//删除通讯录信息
void Delete()
{
    char name[50]; //先查找 后删除
    INFO *p = head->next, *p1 = head->next, *p2;
    if (head->next == NULL)
    {
        printf("此通讯录为空！\n");
        return;
    }
    printf("请输入要删除的姓名：\n");
    scanf(" %s", name);
    while ((strcmp(p->name, name) != 0) && p->next != NULL)
    {
        p1 = p;
        p = p->next;
    }
    if (strcmp(name, p->name) == 0) //输出删除信息
    {
        if (p == head->next && p->next != NULL)
            head->next = p->next;
        else if (p == head->next && p->next == NULL)
        {
            head->next = p->next;
            printf("信息已删除，先此通讯录为空！！\n");
            return;
        }
        else
            p1->next = p->next;
    }
    else
    {
        printf("此信息不存在！！！\n");
        return;
    }
    printf("此信息已删除！");
    printf("姓名\t地址\t邮编\t电话\t电子邮件\n");
    for (p2 = head->next; p2 != NULL; p2 = p2->next)
        printf("%s\t%s\t%s\t%s\t%s\n", p2->name, p2->address, p2->postcode, p2->telno,p2->mail);
}
//显示所有记录
void List()
{
    INFO *p;
    if (head->next == NULL)
    {
        printf("此通讯录中无记录!\n");
        return;
    }
    printf("姓名\t地址\t邮编\t电话\t电子邮件\n");
    for (p = head->next; p != NULL; p = p->next)
        printf("%s\t%s\t%s\t%s\t%s\n", p->name, p->address, p->postcode, p->telno,p->mail);
}
int main(void)
{
    int choice;
    char yes_no;
    Initiate();
    do
    {
        Menu();
        printf("请选择0-6的数字\n");
        scanf("%d", &choice);
        printf("\n");
        switch (choice)
        {
        case 1:
            Create();
            break;
        case 2:
            Add();
            break;
        case 3:
            Find();
            break;
        case 4:
            Alter();
            break;
        case 5:
            Delete();
            break;
        case 6:
            List();
            break;
        case 0:
            printf("************感谢您的使用************\n");
            return 0;
            break;
        default:
            printf("输入有误!请重新输入\n");
            break;
        }
        printf("是否继续 Y or N? \n");
        do
        {
            scanf("%c", &yes_no);
        } while (yes_no != 'Y' && yes_no != 'y' && yes_no != 'N' && yes_no != 'n');
    } while (yes_no == 'Y' || yes_no == 'y');
}