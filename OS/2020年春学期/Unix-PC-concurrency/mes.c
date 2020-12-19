#include <sys/types.h>
#include <unistd.h>
#include <signal.h>
#include <stdio.h>
#include <string.h>
#include <sys/ipc.h>
#include <sys/msg.h>
 
#define MY_KEY 10071140           // need to change

#define SERVER_ID 2

#define MAX_BUF 200

 
void sigend(int); 
struct mymsg {

    long mtype;

    long pid;

    char buf[MAX_BUF];

};

 
int msgid;

int main(void)

{

    struct mymsg msgbuf;//定义一结构变量

        //建立一个共享存储区；

    if((msgid=msgget(MY_KEY, IPC_CREAT|IPC_EXCL|0666)) < 0 )

    {            
     
        msgid=msgget(MY_KEY, 0666);

 
        printf("Act as client, ask question and wait answer!\n");

        printf("To end this process, enter end as input question.\n\n");

 
               //I/O中断

        printf("Input question in one line:\n");

        fgets(msgbuf.buf, sizeof(struct mymsg)-2*sizeof(long)-1, stdin);

 
        while(strcmp(msgbuf.buf,"end\n"))

        {

                      //获得服务进程号

            msgbuf.mtype=SERVER_ID;

 
                      //获得当前进程的进程号

            msgbuf.pid=getpid();

 
                      //发送消息；

            msgsnd(msgid, &msgbuf, sizeof(struct mymsg)-sizeof(long), 0);

           
                         //接收消息；

msgrcv(msgid, &msgbuf, sizeof(struct mymsg), getpid(), 0);

 
            printf("Answer from server: \n%s", msgbuf.buf);

            printf("Input question in one line:\n");

            fgets(msgbuf.buf, sizeof(struct mymsg)-2*sizeof(long)-1, stdin);

        }

    }

    else         
    {

        signal(SIGINT, sigend);

        signal(SIGTERM, sigend);

        printf("ACT SERVER!!! Wait question and give answer.\n");

        printf("To end this process, try Ctrl+C or use kill.\n\n");

      
        while(1)

        {

                      //接收消息；

            msgrcv(msgid, &msgbuf, sizeof(struct mymsg), SERVER_ID, 0);

            printf("Question from %d: \n%sGive answer:\n", (int)msgbuf.pid, msgbuf.buf);

            fgets(msgbuf.buf, sizeof(struct mymsg)-2*sizeof(long)-1, stdin);

                     
            msgbuf.mtype=msgbuf.pid;
 
                      //发送消息；

            msgsnd(msgid, &msgbuf, sizeof(struct mymsg)-sizeof(long), 0);

        }

    }

}

 
void sigend(int sig)

{

              //操纵一个消息队列；

    msgctl(msgid, IPC_RMID, 0);

    exit(0);

}

