#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
main()
{
    char buffer[80];
    int fd;
    unlink("FIFO"); //如果已经存在FIFO文件则先删除
    mkfifo("FIFO", 0666); //创建权限为0666的名称为FIFO的命名管道
    if (fork() > 0) //父进程
    {
        char s[] = "hello !\n";
        fd = open("FIFO", O_WRONLY);    //以只写方式打开FIFO命名管道
        write(fd, s, sizeof(s));    //写入数据s
        close(fd);  //关闭命名管道
    }
    else    //子进程
    {
        fd = open("FIFO", O_RDONLY);    //以只读方式打开FIFO命名管道
        read(fd, buffer, 80);   //读出数据存入buffer
        printf("%s", buffer);   //打印buffer
        close(fd);  //关闭命名管道
    }
}
