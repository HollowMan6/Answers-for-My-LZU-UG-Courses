#include <stdlib.h>
#include <stdio.h>
#define pagesize 8   //页面尺寸大小
typedef struct BLOCK //声明一种新类型--物理块类型
{
    int pagenum;  //页号
    int accessed; //访问量，其值表示多久未被访问
} BLOCK;
int pc;                     //程序计数器，用来记录对应的页号
int n;                      //缺页计数器，用来记录缺页的次数
static int num[320];        //用来存储320条随机数
BLOCK block[pagesize];      //定义一大小为8的物理块数组
void init();                //程序初始化函数
int findExist(int curpage); //查找物理块中是否有该页面
int findSpace();            //查找是否有空闲物理块
int findReplace();          //查找应予置换的页面
void display();             //显示
void randam();              //产生320条随机数,显示并存储到 num[320]
void pagestring();          //显示调用的页面队列
void OPT();
void LRU();
void FIFO();
void main()
{
    int select;
    printf("请输入第一个随机数(0~320):");
    randam();
    printf("*****对应的调用页面队列*******\n");
    pagestring();
    do
    {
        printf("****************************************\n");
        printf("------1:OPT 2:LRU 3:FIFO 4:退出-----\n");
        printf("****************************************\n");
        printf("请选择一种页面置换算法:");
        scanf("%d", &select);
        printf("*******************************\n");
        init();
        switch (select)
        {
        case 1:
            printf("最佳置换算法OPT:\n");
            printf("*****************\n");
            OPT();
            break;
        case 2:
            printf("最近最久未使用置换算法LRU:\n");
            printf("*************************\n*");
            LRU();
            break;
        case 3:
            printf("先进先出置换算法FIFO:\n");
            printf("*********************\n");
            FIFO();
            break;
        }
    } while (select != 4);
}
void init()
{
    for (int i = 0; i < pagesize; i++)
    {
        block[i].pagenum = -1;
        block[i].accessed = 0;
        pc = n = 0;
    }
}
int findExist(int curpage)
{
    for (int i = 0; i < pagesize; i++)
    {
        if (block[i].pagenum == curpage)
            return i; //检测到内存中有该页面，返回block中的位置
    }
    return -1;
}
int findSpace()
{
    for (int i = 0; i < pagesize; i++)
    {
        if (block[i].pagenum == -1)
            return i; //找到空闲的block，返回block中的位置
    }
    return -1;
}
int findReplace()
{
    int pos = 0;
    for (int i = 0; i < pagesize; i++)
    {
        if (block[i].accessed > block[pos].accessed)
            pos = i; //找到应该置换页面，返回BLOCK中位置
    }
    return pos;
}
void display()
{
    for (int i = 0; i < pagesize; i++)
    {
        if (block[i].pagenum != -1)
        {
            printf(" %02d", block[i].pagenum);
        }
    }
    printf("\n");
}
void randam()
{
    int flag = 0;
    scanf("%d", &pc);
    printf("******按照要求产生的320个随机数：*******\n");
    for (int i = 0; i < 320; i++)
    {
        num[i] = pc;
        if (flag % 2 == 0)
            pc = ++pc % 320;
        if (flag == 1)
            pc = rand() % (pc - 1);
        if (flag == 3)
            pc = pc + 1 + (rand() % (320 - (pc + 1)));
        flag = ++flag % 4;
        printf(" %03d", num[i]);
        if ((i + 1) % 10 == 0)
            printf("\n");
    }
}
void pagestring() //显示调用的页面队列,页面号取法为随机数除10取整
{
    for (int i = 0; i < 320; i++)
    {
        printf(" %02d", num[i] / 10);
        if ((i + 1) % 10 == 0)
            printf("\n");
    }
}
void OPT() //最佳替换算法
{
    int exist,
        space, position;
    int curpage;
    for (int i = 0; i < 320; i++)
    {
        pc = num[i];
        curpage = pc / 10;
        exist = findExist(curpage);
        if (exist == -1)
        {
            space = findSpace();
            if (space != -1)
            {
                block[space].pagenum = curpage;
                display();
                n = n + 1;
            }
            else
            {
                for (int k = 0; k < pagesize; k++)
                {
                    for (int j = i; j < 320; j++)
                    {
                        if (block[k].pagenum != num[j] / 10)
                        {
                            block[k].accessed = 1000;
                        } //将来不会用，设置为一个很大数
                        else
                        {
                            block[k].accessed = j;
                            break;
                        }
                    }
                }
                position = findReplace();
                block[position].pagenum = curpage;
                display();
                n++;
            }
        }
    }
    printf("缺页次数:%d\n", n);
    printf("缺页率:%f%%\n", (n / 320.0) * 100);
}
void LRU() //最近最久未使用算法
{
    int exist, space, position;
    int curpage;
    for (int i = 0; i < 320; i++)
    {
        pc = num[i];
        curpage = pc / 10;
        exist = findExist(curpage);
        if (exist == -1)
        {
            space = findSpace();
            if (space != -1)
            {
                block[space].pagenum = curpage;
                display();
                n = n + 1;
            }
            else
            {
                position = findReplace();
                block[position].pagenum = curpage;
                display();
                n++;
            }
        }
        else
            block[exist].accessed = -1; //恢复存在的并刚访问过的BLOCK中页面accessed为-1
        for (int j = 0; j < pagesize; j++)
        {
            block[j].accessed++;
        }
    }
    printf("缺页次数:%d\n", n);
    printf("缺页率:%f%%\n", (n / 320.0) * 100);
}
void FIFO() //先进先出算法
{
    int exist, space, position;
    int curpage;
    for (int i = 0; i < 320; i++)
    {
        pc = num[i];
        curpage = pc / 10;          //转换为页面号
        exist = findExist(curpage); //查找物理块中是否有该页面,没有的话，置为-1
        if (exist == -1)
        {
            space = findSpace();
            //查找是否有空的物理块，没有的话，置为-1；有的话，把位置返回
            if (space != -1)
            {
                block[space].pagenum = curpage;
                display();
                n = n + 1;
            }
            else
            {
                position = findReplace(); //没有空闲物理块，进行置换
                block[position].pagenum = curpage;
                display();
                n++;
                block[position].accessed--;
            }
        }
        for (int j = 0; j < pagesize; j++) //把所有在页面里的页面号的访问次数加1
            block[j].accessed++;
    }
    printf("缺页次数:%d\n", n);
    printf("缺页率:%f%%\n", (n / 320.0) * 100);
}