#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
main()
{
    int filedes[2]; //filedes[0]为管道里的读取端，filedes[1]为管道的写入端
    char buffer[80];
    if (pipe(filedes) < 0)  //成功返回0，失败返回-1
        printf("pipe error");
    if (fork() > 0) //父进程
    {
        char s[] = "hello!\n";
        close(filedes[0]);  //关闭父进程读取端
        write(filedes[1], s, sizeof(s));    //写入数据
        close(filedes[1]);  //关闭父进程写入端
    }
    else    //子进程
    {
        close(filedes[1]);  //关闭子进程写入端
        read(filedes[0], buffer, 80);   //读取数据
        printf("%s", buffer);   //输出读取的内容
        close(filedes[0]);  //关闭子进程读取端
    }
}
