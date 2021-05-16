/*  fork usage  */
#include<unistd.h>
#include<stdio.h>
#include<stdlib.h>

int main(void)
{
		pid_t child;
		if((child=fork())==-1) {
			perror("fork");
			exit(EXIT_FAILURE);
		}else if(child==0){//若为子进程
			puts("in child");
			printf("\tchild pid = %d\n",getpid());//打印pid
			printf("\tchild ppid = %d\n",getppid());//打印ppid
			exit(EXIT_SUCCESS);
		}else{//若为父进程
			puts("in parent");
			printf("\tparent pid = %d\n",getpid());//打印pid
			printf("\tparent ppid = %d\n",getppid());//打印ppid
		}
		exit(EXIT_SUCCESS);
}
