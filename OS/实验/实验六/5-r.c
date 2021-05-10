#include <stdio.h>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
int main()
{
    int fd, pid;
    unlink("FIFO"); //如果已经存在FIFO文件则先删除
    mkfifo("FIFO", 0666);   //创建权限为0666的名称为FIFO的命名管道
    fd = open("FIFO", O_RDONLY);    //以只读方式打开FIFO命名管道
    while(read(fd, &pid, sizeof(pid)))
    {
        printf("pid: %d\n", pid);
    }
    close(fd);
    return 0;
}
