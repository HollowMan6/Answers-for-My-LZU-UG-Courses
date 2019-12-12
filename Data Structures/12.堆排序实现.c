#include <stdio.h>
#define MAX 255
int A[MAX];
// 建初始堆过程函数
void heap(int l, int m) 
{
    int i, j, x;
    i = l;
    j = 2 * i; //R[j] 是 R[i] 的左孩子
    x = A[i];
    while (j <= m)
    {
        // 若左孩子较大，则把 j 修改为右孩子的下标
        if (j < m && A[j] < A[j + 1])
            j++; 
        // 将 A[j] 调到父亲的位置上
        if (x < A[j])
        {
            A[i] = A[j]; 
            // 修改 i 和 j 的值，以便继续向下筛选
            i = j;       
            j = 2 * i;
        }
        else
            // 起结束作用
            j = m + 1; 
    }
    // 被筛结点的值存入最终的位置
    A[i] = x; 
}
void heapsort(int n)
{
    int i;
    int m;
    // 建立初始堆，遍布所有的根结点
    for (i = n / 2; i >= 1; i--)
        heap(i, n); 
    // 筛选 A[1] 结点，得到 i-1 个结点的堆，因为 A[1] 违反堆的性质
    for (i = n; i >= 2; i--)
    { //进行 n-1 次循环完成堆排序
        m = A[i];
        // 将第一个元素同当前区域的最后一个元素对换
        A[i] = A[1]; 
        A[1] = m;
        heap(1, i - 1);
    } 
}
int main()
{
    int i, n;
    printf(" 输入整型一维数组 A 中数字总数 :");
    scanf("%d", &n);
    if (n <= 0 || n > MAX)
    {
        printf("\n 输入的数据有误 !!!");
        return 0;
    }
    printf("\n 请输入整型无序序列 :\n");
    for (i = 1; i <= n; i++)
    {
        scanf("%d", &A[i]);
    }
    printf("\n 该序列为 :\n");
    for (i = 1; i <= n; i++)
    {
        printf("%d ", A[i]);
    }
    heapsort(n);
    printf("\n 堆排序后的序列为 :\n");
    for (i = 1; i <= n; i++)
    {
        printf("%d ", A[i]);
    }
    printf("\n");
    system("pause");
}