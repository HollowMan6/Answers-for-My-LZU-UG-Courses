#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define N 250
//定义结构体 (字符长串）
typedef struct Cstring 
{
    char string[N];
} Cstring;
// 定义结构体（文件）
typedef struct File 
{
    Cstring filename[N];  // 文件数组
    FILE *in, *out;       //文件指针（入 /出）
    int line[N], filenum; //行编号，文件编号
} File;
//结构体（行）
typedef struct Line 
{
    int pos[N], counter; // 所在行号，计数器
} Line;
// 函数（判断单词是否结束）返回单词长度
int Length(char *a) 
{
    int i = 0;
    while (a[i] != '\0' && a[i] != '\n')
        i++;
    return i;
}
// 索引（字符总串，单词，要查找的字符总串第几行）
int Index(Cstring a, Cstring b, Line *l) 
{
    int i, k, j, p = 1, num = 0;
    l->counter = 0;
    Cstring temp;
    //sring[0] 用于存字符串大小
    while (p <= a.string[0]) 
    {
        i = 1;
        // 将字符总串的一个单词放到 temp 中，并没进行一次， num++
        while (a.string[p] != ' ' && a.string[p] != '\0' && a.string[p] != ',' && a.string[p] != '.')
        {
            temp.string[i] = a.string[p];
            i++;
            p++;
        }
        temp.string[i] = '\0';
        temp.string[0] = Length(&(temp.string[1]));
        num++;
        k = 1;
        j = 1;
        if (temp.string[0] == b.string[0])
        {
            while (j <= b.string[0])
            {
                if (temp.string[k] == b.string[j])
                {
                    k++;
                    j++;
                }
                else
                    break;
            }
        }
        // 说明 2 个单词相同
        if (j > b.string[0]) 
        {
            // 该行第 l->counter 的单词所在位置 (即该单词为该行第几个单词 )
            l->pos[l->counter] = num; 
            // 行计数器加 1
            l->counter++;             
        }
        while (a.string[p] == ' ' || a.string[p] == ',' || a.string[p] == '.')
            p++;
    }
    return 1;
}
// 是否相同，相同返回 1
int Equal(Cstring a, Cstring b) 
{
    int i = 1, j = 1;
    if (a.string[0] != b.string[0])
        return 0;
    while (i <= a.string[0] && j <= b.string[0])
    {
        if (a.string[i] == b.string[j])
        {
            i++;
            j++;
        }
        else
            return 0;
    }
    return 1;
}
// 创建文件
int CreateFile(File *F) 
{
    Cstring s;
    char c;
    printf(" 请输入文件名 \n");
    // 名字放置区
    scanf("%s", &(F->filename[F->filenum].string[1]));      
    // 文件名的字符串长度                          
    F->filename[F->filenum].string[0] = Length(&(F->filename[F->filenum].string[1])); 
    // 以写的方式打开文件，文件指针 F->in
    if (!(F->in = fopen(&(F->filename[F->filenum].string[1]), "w")))                  
    {
        printf(" 不能打开文件 \n");
        exit(0);
    }
    // 该文件行数初始为 0
    F->line[F->filenum] = 0; 
    printf(" 请输入文件内容 (结束符为 '#')\n");
    // 每次输一行时就会执行一次循环
    while (gets(&(s.string[1]))) 
    {
        s.string[0] = Length(&s.string[1]);
        c = s.string[s.string[0]];
        if (s.string[0] != 0)
        {
            if (c == '#')
                s.string[s.string[0]] = '\n';
            // 将所有字符存到文件中
            fputs(&(s.string[1]), F->in); 
            // 存入一个换行符
            fputc('\n', F->in);  
            // 该文件行数加 1         
            F->line[F->filenum]++;
        }
        if (c == '#')
            break;
    }
    // 关闭文件
    fclose(F->in); 
    // 文件数组下标加 1，指向下一个文件编号
    F->filenum++;  
    return 1;
}
// 计数
int CountString(File *F) 
{
    Cstring b, a, s;
    int k, counter = 0, line = 0, i;
    Line l[N];
    printf(" 请输入检索的文件名 :");
    scanf("%s", &(b.string[1]));
    b.string[0] = Length(&(b.string[1]));
    //找到该文件名
    for (k = 0; k < F->filenum; k++) 
    {
        if (Equal(F->filename[k], b))
            break;
    }
    if (k == F->filenum)
    {
        printf(" 文件不存在 \n");
        return 0;
    }
    if (!(F->out = fopen(&F->filename[k].string[1], "r")))
    {
        printf(" 不能打开文件 \n");
        exit(0);
    }
    //找到了并能正常打开文件编号为 k
    printf(" 请输入搜索的单词： ");
    scanf("%s", &(s.string[1]));
    s.string[0] = Length(&(s.string[1]));
    // 行数小于 k 文件的总行数
    while (line < F->line[k]) 
    {
        // 从文件中将内容读取到 a 地址处
        fgets(&(a.string[1]), N, F->out); 
        a.string[0] = Length(&(a.string[1]));
        Index(a, s, &l[line]); // 索引
        line++;                // 进入下一行
    }
    // 通过每行的计数器，算出单词出现的总次数，计入 counter
    for (i = 0; i < line; i++) 
    {
        if (l[i].counter != 0)
            counter += l[i].counter;
    }
    printf(" 文件中出现该单词的个数为： ");
    printf("%d\n", counter);
    fclose(F->out); // 关闭文件
    return 1;
}
// 单词定位
int PotString(File *F) 
{
    Cstring b, s, a;
    Line l[N];
    int k, line = 0, i, j;
    printf(" 请输入检索的文件名 :");
    scanf("%s", &(b.string[1]));
    b.string[0] = Length(&(b.string[1]));
    //找到该文件编号
    for (k = 0; k < F->filenum; k++) 
    {
        if (Equal(F->filename[k], b))
            break;
    }
    if (k == F->filenum)
    {
        printf(" 文件不存在 \n");
        return 0;
    }
    if (!(F->out = fopen(&(F->filename[k].string[1]), "r")))
    {
        printf(" 不能打开文件 \n");
        exit(0);
    }
    //找到了并能正常打开文件编号为 k
    printf(" 请输入要定位的单词： ");
    scanf("%s", &(s.string[1]));
    s.string[0] = Length(&(s.string[1]));
    // 行数小于 k 文件的总行数
    while (line < F->line[k]) 
    {
        // 从文件中将内容读取到 a 地址处
        fgets(&(a.string[1]), N, F->out); 
        a.string[0] = Length(&(a.string[1]));
        Index(a, s, &l[line]); // 索引
        line++;
    }
    printf(" 该单词首次出现的行号以及位置： \n");
    for (i = 0; i < line; i++)
    {
        if (l[i].counter > 0)
        {
            printf(" 行号：");
            printf(" %-3d ", i + 1);
            printf(" 所在位置： ");
            for (j = 0; j < l[i].counter; j++)
            {
                printf(" 第 ");
                printf("%d", l[i].pos[j]);
                printf(" 个单词 ");
            }
            printf("\n");
            break;
        }
    }
    return 1;
}
int main()
{
    int i;
    File F;
    F.filenum = 0;
    printf("\n 1. 创建文件 \n 2. 单词计数 \n 3. 单词定位 \n 4. 退出 \n");
    printf(" 请选择操作 :\n");
    while (scanf("%d", &i) && i != 4)
    {
        switch (i)
        {
        case 1:
            CreateFile(&F);
            printf("\n 请选择操作 :\n");
            break;
        case 2:
        {
            CountString(&F);
            printf("\n 请选择操作 :\n");
            break;
        }
        case 3:
        {
            PotString(&F);
            printf("\n 请选择操作 :\n");
            break;
        }
        default:
            printf("\n 操作错误 \n"), i = 4;
        }
    }
    system("pause");
}