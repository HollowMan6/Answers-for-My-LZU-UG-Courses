#include <stdio.h>
#include <stdlib.h>
#define M 100
typedef struct node // 定义二叉树结点
{
    char data;
    struct node *lchild, *rchild;
} BTNode;
BTNode *CreatBTree() // 创建二叉树 (先序递归 )
{
    char ch;
    BTNode *b;
    scanf("%c", &ch);
    if (ch == '#') // 递归结束控制符
        b = NULL;
    else
    {
        b = (BTNode *)malloc(sizeof(BTNode));
        b->data = ch;
        b->lchild = CreatBTree(); // 递归先序建立左子树
        b->rchild = CreatBTree(); // 递归先序建立右子树
    }
    return b;
}
void PreOrder(BTNode *b) // 递归先序遍历二叉树函数
{
    if (b != NULL)
    {
        printf("%c ", b->data);
        PreOrder(b->lchild);
        PreOrder(b->rchild);
    }
}
void InOrder(BTNode *b) // 非递归中序遍历二叉树函数
{
    BTNode *stack[M], *p;
    int top = -1;
    if (b != NULL)
    {
        p = b;
        while (top > -1 || p != NULL)
        {
            while (p != NULL) // 扫描 p 的所有左结点并入栈
            {
                top++;
                stack[top] = p;
                p = p->lchild;
            }
            if (top > -1)
            {
                p = stack[top]; // 出栈访问结点
                top--;
                printf("%c ", p->data);
                p = p->rchild; // 扫描 p 的右结点
            }
        }
        printf("\n");
    }
}
void PostOrder(BTNode *b) // 非递归后序遍历二叉树函数
{
    BTNode *stack[M], *p;
    int sign, top = -1;
    if (b != NULL)
    {
        do
        {
            while (b != NULL) // b 所有左结点入栈
            {
                top++;
                stack[top] = b;
                b = b->lchild;
            }
            p = NULL; // p 指向栈顶前一个已访问结点
            sign = 1; //置 b 为已访问
            while (top != -1 && sign)
            {
                b = stack[top];     // 取出栈顶结点
                if (b->rchild == p) // 右孩子不存在或右孩子已访问则访问 b
                {
                    printf("%c ", b->data);
                    top--;
                    p = b; //p 指向被访问的结点
                }
                else
                {
                    b = b->rchild; //b 指向右孩子结点
                    sign = 0;      // 置未访问标记
                }
            }
        } while (top != -1);
        printf("\n");
    }
}
void change(BTNode *b) //左右子树交换 (递归 )
{
    BTNode *r;
    r = (BTNode *)malloc(sizeof(BTNode));
    int f1 = 0, f2 = 0;
    if (b == 0)
        return; //树为空时，跳出
    if (b->lchild)
    {
        change(b->lchild);
        r->lchild = b->lchild;
        f1++; //有左子树，符号位不为空
    }
    if (b->rchild)
    {
        change(b->rchild);
        r->rchild = b->rchild;
        f2++; //有右子树，符号位不为空
    }
    if (f1 == 0)
        r->lchild = NULL; //否则，给中间变量赋空值
    if (f2 == 0)
        r->rchild = NULL;
    if (f1 || f2)
    {
        b->rchild = r->lchild; //左右子树交换
        b->lchild = r->rchild;
    }
}
int maxn(int m, int n)
{
    if (m > n)
        return m;
    else
        return n;
}
int count(BTNode *b) //计算树高 (递归 )
{
    if (b == NULL)
        return 0;
    else
        return (1 + maxn(count(b->lchild), count(b->rchild)));
}
int main()
{
    BTNode *root = NULL;
    printf("----------------- 二叉树的遍历 -----------------\n\n");
    printf(" 请按先序递归顺序创建二叉树 (结束符 #):\n");
    root = CreatBTree();
    printf("\n 递归先序遍历结果 :\n");
    PreOrder(root);
    printf("\n 非递归中序遍历结果 :\n");
    InOrder(root);
    printf(" 非递归后序遍历结果 :\n");
    PostOrder(root);
    printf("\n 树高 : %d\n", count(root));
    printf("\n 左右子树交换位置 :");
    change(root);
    printf("\n 递归先序遍历结果 :\n");
    PreOrder(root);
    printf("\n 非递归中序遍历结果 :\n");
    InOrder(root);
    printf(" 非递归后序遍历结果 :\n");
    PostOrder(root);
    system("pause");
    return 0;
}