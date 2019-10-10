#include <stdio.h>
#include <stdlib.h>
#define LEN sizeof(struct Link)
struct Link
{
    int c;
    int e;
    struct Link *next;
};
void main()
{
    void start();                                                //用户选择界面函数声明
    struct Link *creat(char ch);                                 //创造有序链表函数声明
    struct Link *addLink(struct Link * head, struct Link * pre); //链表相加函数声明
    struct Link *cutLink(struct Link * head, struct Link * pre); //链表相减函数声明
    void print(struct Link * p);                                 //输出函数声明
    struct Link *a, *b;
    int sign = -1; //设置标志start();
    while (sign != 0)
    {
        scanf("%d", &sign);
        switch (sign)
        {
        case 0:
            break; //退出
        case 1:
        {
            printf(" 你选择的操作是多项式相加 :\n");
            a = creat('A'); //输入多项式 f(x)
            printf("A(x)=");
            print(a);
            b = creat('B'); //输入多项式 g(x)
            printf("B(x)=");
            print(b);
            printf("C(x)=A(x)+B(x)=");
            a = addLink(a, b); //两个多项式相加
            print(a);
            sign = -1; //复位标志
            start();   //回复用户选择界面
            break;
        }
        case 2:
        {
            printf(" 你选择的操作是多项式相减 :\n");
            a = creat('A'); //输入多项式 A(x)
            printf("A(x)=");
            print(a);
            b = creat('B'); //输入多项式 B(x)
            printf("B(x)=");
            print(b);
            printf("C(x)=A(x)-B(x)=");
            a = cutLink(a, b); //两个多项式相减
            print(a);
            sign = -1; //复位标志
            start();   //回复用户选择界面
            break;
        }
        default:
        {
            printf(" 输入有误 !请重新选择操作 !\n"); // 选择错误 ,返回选择界
            面start();
            break;
        }
        }
    }
}
void start() //用户选择界面
{
    printf("************************************\n");
    printf(" 两个一元多项式的相加 /相减 :\n");
    printf("************************************\n");
    printf(" 请选择操作 :\n");
    printf("0. 退出\n");
    printf("1. 两个一元多项式相加 \n");
    printf("2. 两个一元多项式相减 \n");
}
struct Link *creat(char ch)
{
    void insert(struct Link * head, struct Link * inpt);
    struct Link *head, *inpt;
    int x;
    int y;
    head = (struct Link *)malloc(LEN); // 创建链表头
    head->next = NULL;
    printf("Please input the %c(x):(The end: 0 0)\n", ch);
    scanf("%d %d", &x, &y);
    while (x != 0)
    {
        inpt = (struct Link *)malloc(LEN);
        inpt->c = x;
        inpt->e = y;
        inpt->next = NULL;
        insert(head, inpt); //将这个结点插到有序链表中
        printf(" 请输入一元多项式 %c(x)的下一项 :(The end: 0 0)\n", ch);
        scanf("%d %d", &x, &y);
    }
    return head;
}
void insert(struct Link *head, struct Link *inpt)
{
    struct Link *pre, *now;
    int signal = 0;
    pre = head; //pre定义为现在的前一个结点
    if (pre->next == NULL)
        pre->next = inpt;
    else
    {
        now = pre->next;
        while (signal == 0)
        {
            if ((inpt->e) < (now->e)) // 当新链节小于现在的连接时向后移一个链节
            {
                if (now->next == NULL)
                {
                    now->next = inpt;
                    signal = -1;
                }
                else
                {
                    pre = now;
                    now = pre->next;
                }
            }
            else if ((inpt->e) > (now->e)) // 如果发现比现在的链节大了就插入到这个连接的前面
                {
                    inpt->next = now;
                    pre->next = inpt;
                    signal = 1;
                }
            else
            {
                now->c = now->c + inpt->c;
                signal = 1;
                free(inpt);
                if (now->c == 0)
                {
                    pre->next = now->next;
                    free(now);
                }
            }
        }
    }
}
struct Link *addLink(struct Link *head, struct Link *pre) // 多项式相加
{
    struct Link *inpt;
    int flag = 0;
    while (flag == 0)
    {
        if (pre->next == NULL)
            flag = 1;
        else
        {
            pre = pre->next;
            inpt = (struct Link *)malloc(LEN);
            inpt->c = pre->c;
            inpt->e = pre->e;
            inpt->next = NULL;
            insert(head, inpt);
        } //将 B(x)中的结点依次插入 A(x) 中相加
    }
    return head;
}
struct Link *cutLink(struct Link *head, struct Link *pre) // 多项式相减
{
    struct Link *inpt;
    int flag = 0;
    while (flag == 0)
    {
        if (pre->next == NULL)
            flag = 1;
        else
        {
            pre = pre->next;
            inpt = (struct Link *)malloc(LEN);
            inpt->c = 0 - pre->c;
            inpt->e = pre->e;
            inpt->next = NULL;
            insert(head, inpt);
        } //将 B(x)中的结点依次插入 A(x) 中相减
    }
    return head;
}
void print(struct Link *p) // 输出多项式
{
    struct Link *now;
int flag = 0;
now = p->next; //now 表示输出的信息，从头开始
if (p->next == NULL)
{
    printf("\n");
    return;
}
while (flag == 0)
{
    if ((now->c) > 0 && (p->next) != now)
        printf("+");
    if (now->c == 1)
        ;                  //如果系数是 1，就不用输出该系数了
    else if (now->c == -1) //如果是 -1，就只需输出 "-" 号
        printf("-");
    else
        printf("%d", now->c); // 其他情况都做输出
    if (now->e != 0)
        printf("x^%d", now->e);
    else if ((now->c == 1) || (now->c == -1))
        printf("1");
    if (now->next == NULL)
        flag = 1;
    else
        now = now->next;
}
printf("\n");
system("pause");
}