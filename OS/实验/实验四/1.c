#include<unistd.h>
#include<stdio.h>
int main(){
    printf("This is the process.");
    printf("pid=%d\n",getpid());
    printf("ppid=%d\n",getppid());
    printf("uid=%d\n",getuid());
    printf("euid=%d\n",geteuid());
    printf("gid=%d\n",getgid());
    printf("egid=%d\n",getegid());
}