#include <stdio.h>
#include <unistd.h>
#include <wait.h>
#include <sys/resource.h>

int main(void)
{
    int p;
    while((p = fork()) == -1);  //创建一个子进程
    if(p > 0)                   //父进程
    {
        setpriority(PRIO_PROCESS, p, 1);
        printf("child process priority: %d.\n", getpriority(PRIO_PROCESS, p));
        wait(0);                //等待子进程结束
        printf("child process terminated.\n");
    }
    else                        //子进程
    {
        execlp("find", "find", "/", "-name", "hda*", NULL);
    }
    return 0;
}
