#include <stdio.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/mman.h>
#include <string.h>
#include <memory.h>
#include <stdlib.h>
#include <stdio.h>
int lastrow(char *s, int d);
int nextrow(char *s, int d);
int onepage(char *s, int d);
int main(int argc, char *argv[])
{
    if( argc == 2 ) // 判断参数是否只有一个
    {
        int fd, play = 0;
        char lab;
        char *start;
        struct stat sb;
        fd = open(argv[1], O_RDONLY); // 以只读方式打开文件
        fstat(fd, &sb); // 获取文件的大小
        start = mmap(NULL, sb.st_size, PROT_READ, MAP_PRIVATE, fd, 0);
        if (start == MAP_FAILED) // MAP_FAILED表示映射失败
            return (1);
        play = onepage(start, play) + 1;
        lab = getchar();
        while (lab != 'q')    // 输入的字符为q，退出
        {
            if (play > sb.st_size)  // 如果onepage返回的字节数大于文件的大小，输入任意字符退出
            {
                lab = getchar();
                break;
            }
            else if (lab == 'p')    // 输入p，继续读10行
                play += onepage(start, play) + 1;
            else if (lab == 'n')    // 输入n，显示下一行
                play += nextrow(start, play) + 1;
            else if (lab == 'l')    // 输入l，显示上一行
                play = lastrow(start, play) + 1;
            lab = getchar();
        }
        munmap(start, sb.st_size);  // 解除映射
        close(fd);  // 关闭文件fd
        return 0;
    }
    else if( argc > 2 )
    {
        printf("Too many arguments supplied.\n");
        return 1;
    }
    else
    {
        printf("One argument expected.\n");
        return 1;
    }
}
int onepage(char *s, int d)
{
    int i, count = 0;   // count在这里表示文件中行的数量
    char *buffer = malloc(2048);    // 配置内存空间，由buffer指向该空间

    s += d; // 每10行作为一页输出
    for (i = 0; i < 2048; i++)
    {
        if (s[i] == '\n')
            count++;
        if (count == 10)
            break;
    }
    memcpy(buffer, s, i);   // 从s处开始的地方拷贝i个字节到buffer
    buffer[i] = '\0';   // 添加结束标识
    printf("%s\n", buffer);
    return i;
}
int nextrow(char *s, int d) // 下一行
{
    int i;
    char *buffer = malloc(100);
    s += d;
    for (i = 0; i < 100; i++)
        if (s[i] == '\n')
            break;
    memcpy(buffer, s, i);
    buffer[i] = '\0';
    printf("%s\n", buffer);
    return i;
}
int lastrow(char *s, int d) // 上一行
{
    int i, count = 0;
    char *buffer = malloc(100);
    int py = d;
    for (; d > 0; d--)
    {
        if (s[d] == '\n')
            count++;
        if (count == 2)
            break;
    }
    memcpy(buffer, s + d + 1, py - d - 2);
    buffer[py - d - 2] = '\0';
    printf("%s\n", buffer);
    return d;
}
