//向量实现方式
#include <stdio.h>
#include <stdlib.h>
int getcount(int *p, int m, int n)
{
    int i, temp, flag, count;
    temp = 0;
    count = m; //记下队伍中剩余猴子的数目

    //简化算法，直接返回特殊情况
    if (n == 1)
        return m;

    while (count != 1)
    {

        //确认前n-1只猴子的位置
        flag = 1; //记录将要数到的猴子数
        while (flag < n)
        {                  //依次递增查找要删除的猴子的编号前n个
            if (temp >= m) //越界清零重新开始数
                temp = 0;
            if (p[temp] != 0) //找到一只在队伍中的猴子
                flag++;
            temp++;
        }
        if (temp >= m) //越界清零重新开始数
            temp = 0;

        //删除第n只猴子
        //跳过已删除猴子
        while (temp < m && p[temp] == 0)
        {
            temp++;
            if (temp >= m)
                temp = 0;
        }
        p[temp] = 0;
        count -= 1; //剩下猴子的个数-1
    }

    //返回最后一只猴子标号
    for (i = 0; i < m; i++)
        if (p[i] != 0)
            return p[i];
}
int main()
{
    int *p;
    int m, n;
    scanf("%d %d", &m, &n);
    if (m > 0 && n > 0)
    {
        p = (int *)malloc(sizeof(int) * m);
        for (int i = 0; i < m; i++)
            p[i] = i + 1;
        printf("最后一只猴子的编号为:%d\n", getcount(p, m, n));
        free(p);
    }
    else
        printf("m或n输入错误，程序退出！");
    system("pause");
    return 0;
}