#include <stdio.h>
#include <stdlib.h>
#define M 100
// 定义二叉树结点
typedef struct node 
{
    char data;
    struct node *lchild, *rchild;
} BTNode;
// 创建二叉树 (先序递归 )
BTNode *CreatBTree() 
{
    char ch;
    BTNode *b;
    scanf("%c", &ch);
    // 递归结束控制符
    if (ch == '#') 
        b = NULL;
    else
    {
        b = (BTNode *)malloc(sizeof(BTNode));
        b->data = ch;
        // 递归先序建立左子树
        b->lchild = CreatBTree(); 
        // 递归先序建立右子树
        b->rchild = CreatBTree(); 
    }
    return b;
}
// 递归先序遍历二叉树函数
void PreOrder(BTNode *b) 
{
    if (b != NULL)
    {
        printf("%c ", b->data);
        PreOrder(b->lchild);
        PreOrder(b->rchild);
    }
}
// 非递归中序遍历二叉树函数
void InOrder(BTNode *b) 
{
    BTNode *stack[M], *p;
    int top = -1;
    if (b != NULL)
    {
        p = b;
        while (top > -1 || p != NULL)
        {
            // 扫描 p 的所有左结点并入栈
            while (p != NULL) 
            {
                top++;
                stack[top] = p;
                p = p->lchild;
            }
            if (top > -1)
            {
                // 出栈访问结点
                p = stack[top]; 
                top--;
                printf("%c ", p->data);
                // 扫描 p 的右结点
                p = p->rchild; 
            }
        }
        printf("\n");
    }
}
// 非递归后序遍历二叉树函数
void PostOrder(BTNode *b) 
{
    BTNode *stack[M], *p;
    int sign, top = -1;
    if (b != NULL)
    {
        do
        {
            // b 所有左结点入栈
            while (b != NULL) 
            {
                top++;
                stack[top] = b;
                b = b->lchild;
            }
            // p 指向栈顶前一个已访问结点
            p = NULL; 
            //置 b 为已访问
            sign = 1; 
            while (top != -1 && sign)
            {
                // 取出栈顶结点
                b = stack[top];     
                // 右孩子不存在或右孩子已访问则访问 b
                if (b->rchild == p) 
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
//左右子树交换 (递归 )
void change(BTNode *b) 
{
    BTNode *r;
    r = (BTNode *)malloc(sizeof(BTNode));
    int f1 = 0, f2 = 0;
    //树为空时，跳出
    if (b == 0)
        return; 
    //有左子树，符号位不为空
    if (b->lchild)
    {
        change(b->lchild);
        r->lchild = b->lchild;
        f1++; 
    }
    //有右子树，符号位不为空
    if (b->rchild)
    {
        change(b->rchild);
        r->rchild = b->rchild;
        f2++; 
    }
    //否则，给中间变量赋空值
    if (f1 == 0)
        r->lchild = NULL; 
    if (f2 == 0)
        r->rchild = NULL;
    //左右子树交换
    if (f1 || f2)
    {
        b->rchild = r->lchild; 
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
//计算树高 (递归 )
int count(BTNode *b) 
{
    if (b == NULL)
        return 0;
    else
        return (1 + maxn(count(b->lchild), count(b->rchild)));
}
int main()
{
    BTNode *root = NULL;
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