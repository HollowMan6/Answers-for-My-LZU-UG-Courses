#include <stdio.h>
#include <stdlib.h>
typedef struct node // 定义二叉排序树结点
{
    char data;
    struct node *lson, *rson;
} BTNode;
void Ins_Tree(BTNode *p, BTNode *d)
{
    if (p->data < d->data)
    {
        if (p->rson == NULL)
            p->rson = d;
        else
            Ins_Tree(p->rson, d);
    }
    else
    {
        if (p->lson == NULL)
            p->lson = d;
        else
            Ins_Tree(p->lson, d);
    }
}
void PreOrder(BTNode *p) // 递归中序遍历二叉排序树函数
{
    if (p != NULL)
    {
        PreOrder(p->lson);
        printf("%c ", p->data);
        PreOrder(p->rson);
    }
}
int main()
{
    BTNode *head, *p, *b;
    char ch;
    head = (BTNode *)malloc(sizeof(BTNode));
    p = head;
    printf("------------------------ 创建二叉排序树 ----------------------\n\n\n");
    printf(" 请逐个输入所需字符 (以#结束 ):\n");
    scanf("%c", &ch);
    if (ch != '#')
    {
        p->data = ch;
        p->lson = NULL;
        p->rson = NULL;
    }
    else
        return;
    scanf("%c", &ch);
    while (ch != '#')
    {
        b = (BTNode *)malloc(sizeof(BTNode));
        b->data = ch;
        b->lson = NULL;
        b->rson = NULL;
        Ins_Tree(p, b);
        scanf("%c", &ch);
    }
    printf("\n 二叉排序树中序序列为 :\n");
    PreOrder(head);
    printf("\n");
    system("pause");
}