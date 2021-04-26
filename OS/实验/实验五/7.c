#include <stdio.h>
#include <unistd.h>
#include <sys/resource.h>
#include <time.h>
#include <stdlib.h>
#include <sys/times.h>
void time_print(char *str,clock_t time){
    long tps=sysconf(_SC_CLK_TCK);
    printf("%s:%6.2f secs",str,(float)time/tps);
}
int main(){
    pid_t pid;
    clock_t start,end;
    struct tms t_start,t_end;
    pid=fork();
    start=times(&t_start);
    if(pid>0){
        //在父进程中设置子进程优先级
        setpriority(PRIO_PROCESS,pid,20);
        //输出修改后的子进程的优先级
        printf("the priority of son process is%d",getpriority(PRIO_PROCESS,pid)); }
        //子进程执行代码
    else{
        int i,j,shu[50][50];
        for(i=0;i<50;i++)
        for(j=0;j<50;j++)
            shu[i][j]=i+j;
        system("grep the /usr/*/*/* >/dev/null 2>/dev/null");
    }
    end=times(&t_end);
    time_print("\nelapsed",end-start);
    printf("\nparent time");
    time_print("\tuser CPU",t_end.tms_utime);
    time_print("\tsys CPU",t_end.tms_stime);
    printf("\nchild time");
    time_print("\tuser CPU",t_end.tms_cutime);
    time_print("\tsys CPU",t_end.tms_cstime);
    printf("\n");
    exit(0);
}
