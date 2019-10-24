#include <stdio.h>
#include <stdlib.h>
#define LEN sizeof(struct Link)
struct Link
{
    int c;//常数
    int e;//指数
    struct Link *next;
};
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
            // 当新结点小于现在的连接时向后移一个结点
            if ((inpt->e) < (now->e)) 
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
            // 如果发现比现在的结点大了就插入到这个结点的前面
            else if ((inpt->e) > (now->e)) 
            {
                inpt->next = now;
                pre->next = inpt;
                signal = 1;
            }
            // 如果发现和现在的结点相等则合并
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
// 多项式相加
struct Link *addLink(struct Link *head, struct Link *pre)
{
    struct Link *inpt;
    int flag = 0;
    while (flag == 0)
    {
        if (pre->next == NULL)
            flag = 1;
        //将 B(x)中的结点依次插入 A(x) 中相加
        else
        {
            pre = pre->next;
            inpt = (struct Link *)malloc(LEN);
            inpt->c = pre->c;
            inpt->e = pre->e;
            inpt->next = NULL;
            insert(head, inpt);
        } 
    }
    return head;
}

// 多项式相减
struct Link *cutLink(struct Link *head, struct Link *pre) 
{
    struct Link *inpt;
    int flag = 0;
    while (flag == 0)
    {
        if (pre->next == NULL)
            flag = 1;
        //将 B(x)中的结点依次插入 A(x) 中相减
        else
        {
            pre = pre->next;
            inpt = (struct Link *)malloc(LEN);
            inpt->c = 0 - pre->c;
            inpt->e = pre->e;
            inpt->next = NULL;
            insert(head, inpt);
        } 
    }
    return head;
}
// 输出多项式
void print(struct Link *p) 
{
    struct Link *now;
    int flag = 0;
    //now 表示输出的信息，从头开始
    now = p->next; 
    if (p->next == NULL)
    {
        printf("\n");
        return;
    }
    while (flag == 0)
    {
        if ((now->c) > 0 && (p->next) != now)
            printf("+");
        //如果系数是 1，就不用输出该系数了
        if (now->c == 1)
            ;             
        //如果是 -1，就只需输出 "-" 号     
        else if (now->c == -1) 
            printf("-");
        // 其他情况都做输出
        else
            printf("%d", now->c); 
        if (now->e != 0)
            printf("X^%d", now->e);
        else if ((now->c == 1) || (now->c == -1))
            printf("1");
        if (now->next == NULL)
            //标志结束
            flag = 1;
        else
            now = now->next;
    }
    printf("\n");
}

struct Link *creat(char ch)
{
    struct Link *head, *inpt;
    int x;
    int y;
    head = (struct Link *)malloc(LEN); // 创建链表头
    head->next = NULL;
    printf(" 请输入一元多项式 %c(X)的第一项:(以 0 0 结束)\n", ch);
    scanf("%d %d", &x, &y);
    while (x != 0)
    {
        inpt = (struct Link *)malloc(LEN);
        inpt->c = x;
        inpt->e = y;
        inpt->next = NULL;
        //将这个结点插到有序链表中
        insert(head, inpt); 
        printf(" 请输入一元多项式 %c(X)的下一项 :(以 0 0 结束)\n", ch);
        scanf("%d %d", &x, &y);
    }
    return head;
}
int main()
{
    struct Link *a, *b;
    //输入多项式 A(X)
    a = creat('A'); 
    printf("A(X) = ");
    print(a);
    //输入多项式 B(X)
    b = creat('B'); 
    printf("B(X) = ");
    print(b);
    //两个多项式相加
    printf("C(X) = A(X) + B(X) = ");
    a = addLink(a, b); 
    print(a);
    printf("D(X) = A(X) - B(X) =");
    //两个多项式相减
    a = cutLink(a, b); 
    a = cutLink(a, b); 
    print(a);
    system("pause");
}