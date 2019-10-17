//链表实现
#include <stdio.h>
#include <stdlib.h>

//链表节点结构
struct num
{
    int number;
    struct num *next;
};

//循环链表实现，这种方法更简单
int getcount(int m, int n)
{
    struct num *p, *p1, *head, *temp;
    int i = 0, flag;
    p = (struct num *)malloc(sizeof(struct num));
    head = p;

    //简化算法，直接返回特殊情况
    if (n == 1)
        return m;

    while (i < m)
    {
        //构建链表
        p1 = p;
        p->number = i + 1;
        p = (struct num *)malloc(sizeof(struct num));
        p1->next = p;
        i++;
    }
    p1->next = head; //形成环状列表
    free(p);
    i = 1; //记录将要删除节点数
    while (i < m)
    {
        flag = 1; //记录将要数到的猴子数

        //依次查找要删除的猴子的编号
        while (flag < n)
        {
            temp = head; //temp为当前指针的前驱
            head = head->next;
            flag++;
        }
        //删除该head节点
        temp->next = head->next;
        free(head);
        head = temp->next;
        i++;
    }
    return head->number;
}

//单向链表实现
int getcount1(int m, int n)
{
    struct num *p, *p1, *head, *temp;
    int i = 0, flag;
    p = (struct num *)malloc(sizeof(struct num));
    head = p;

    //简化算法，直接返回特殊情况
    if (n == 1)
        return m;

    while (i < m)
    {
        //构建链表
        p1 = p;
        p->number = i + 1;
        p = (struct num *)malloc(sizeof(struct num));
        p1->next = p;
        i++;
    }
    //处理尾节点，形成单向链表
    p1->next = NULL;
    p = head;
    i = 1;    //记录将要删除节点个数
    flag = 1; //记录将要数到的猴子数
    while (i < m)
    {
        //删除节点
        if (flag == n)
        {
            if (p == head)
            { //当删除的节点位于头节点时
                head = head->next;
                p = head;
            }
            else if (p->next == NULL)
            { //当删除节点位于尾节点时
                temp->next = NULL;
                p = head;
            }
            else
            { //当删除节点位于中间
                temp->next = p->next;
                p = p->next;
            }
            i++;
            flag = 1;
        }

        //当指针到达链表尾部时，返回使其指向头指针。
        if (p->next == NULL)
            p = head;
        else
        {
            temp = p; //temp为当前指针的前驱
            p = p->next;
        }
        flag++;
    }
    return head->number;
}

int main()
{
    int m, n;
    scanf("%d %d", &m, &n);
    if (m > 0 && n > 0)
    {
        printf("循环链表实现：最后一只猴子的编号为:%d\n", getcount(m, n));
        printf("单向链表实现：最后一只猴子的编号为:%d\n", getcount1(m, n));
    }
    else
        printf("m或n输入错误，程序退出！");
    system("pause");
    return 0;
}