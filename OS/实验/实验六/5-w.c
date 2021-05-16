#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>

int main()
{
    int pid, fd;
    pid = getpid(); //获得当前进程的pid
    fd = open("FIFO", O_WRONLY);    //以只写方式打开FIFO命名管道
    while(write(fd, &pid, sizeof(pid))) //持续写数据
    {
        printf("pid: %d\n", pid);
        sleep(3);   //等待3秒
    }
    close(fd);
    return 0;
}
