#include <stdio.h>
#include <unistd.h>
#include <wait.h>

int main(void)
{
    int p;
    while((p = fork()) == -1);  //创建一个子进程
    if(p > 0)                   //父进程
    {
        wait(0);
        printf("parent process:\n");
        printf("\tpid: %d\n", p);
    }
    else                        //子进程
    {
        printf("child process:\n");
        printf("\tpid: %d\n", getpid());
        printf("\tppid: %d\n", getppid());
    }
    return 0;
}
