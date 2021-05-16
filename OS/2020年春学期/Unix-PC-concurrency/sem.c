#include<sys/types.h>
#include<linux/sem.h>
#include<linux/shm.h>
#include<unistd.h>
#include<stdio.h>
#include<stdlib.h>
#include<errno.h>
#include<time.h>

#define MAXSHM 10  //定义缓冲区数组的下标变量个数

/*        定义3个信号量的内部标识  */
int fullid;
int emptyid;
int mutexid;

/* 主函数  */
int main()
{
/*  定义2个共享内存的ID  */
int arrayid;
int getid;
/*  定义共享内存虚拟地址  */
int *array;
int *get;
/* 创建共享内存  */
arrayid=shmget(IPC_PRIVATE,sizeof(int)*MAXSHM,IPC_CREAT|0666);
getid=shmget(IPC_PRIVATE,sizeof(int),IPC_CREAT|0666);
/*  初始化共享内存  */
array=(int *) shmat(arrayid,0,0);
get=(int *) shmat(getid,0,0);
*get=0;
/* 定义信号量数据结构 */
struct sembuf  P,V;
union semun arg;
/* 创建信号量  */
fullid=semget(IPC_PRIVATE,1,IPC_CREAT|0666);
emptyid=semget(IPC_PRIVATE,1,IPC_CREAT|0666);
mutexid=semget(IPC_PRIVATE,1,IPC_CREAT|0666);
/*初始化信号量 */
arg.val=0;            //初始时缓冲区中无数据
if(semctl(fullid,0,SETVAL,arg)==-1)
perror("semctl setval error");
arg.val=MAXSHM;       //初始时缓冲区中有10个空闲的数组元素
if(semctl(emptyid,0,SETVAL,arg)==-1)
perror("semctl setval error");
arg.val=1;            //初始时互斥信号为1，允许一个进程进入
if(semctl(mutexid,0,SETVAL,arg)==-1)
perror("semctl setval error");

/* 初始化 P  V操作  */
P.sem_num=0;
P.sem_op=-1;
P.sem_flg=SEM_UNDO;
V.sem_num=0;
V.sem_op=1;
V.sem_flg=SEM_UNDO;

/*   生产者进程  */
if(fork()==0)
{
int i=0;
int set=0;
while(i<2*MAXSHM)
{
semop(emptyid,&P,1);         //对 emptyid执行P操作
semop(mutexid,&P,1);         //对 mutexid执行 P操作
array[set%MAXSHM]=i+1;
printf("Producer put number %d to No.%d\n",array[set%MAXSHM],set%MAXSHM);
set++;                       //写计数加1
semop(mutexid,&V,1);         //对mutexid执行 V 操作
semop(fullid,&V,1);          //对fullid执行 V 操作
i++;
}
sleep(3);                    //SLEEP 3秒，等待消费者进程执行完毕
printf("Poducer is over\n");
//exit(0);
}
else
{
/*  消费者A进程  */
if(fork()==0)
{
int getnum;
while(1)
{
if(*get==2*MAXSHM)
break;
semop(fullid,&P,1);        //对fullid执行 P 操作
semop(mutexid,&P,1);       //对mutexid执行 P 操作
getnum=array[(*get)%MAXSHM];
printf("The Consumer A get number %d from No.%d\n",getnum,(*get)%MAXSHM);
(*get)++;                   //读计数加1
semop(mutexid,&V,1);        //对mutexid执行 V 操作
semop(emptyid,&V,1);        //对fullid执行 V 操作
sleep(1);
}
printf("Consuner A is over\n");
exit(0);
}
else
{
/*消费者B进程  */
if(fork()==0)
{
int getnum;
while(1)
{
if(*get==2*MAXSHM)
break;
semop(fullid,&P,1);       //对fullid执行 P 操作
semop(mutexid,&P,1);      //对mutexid执行 P 操作
getnum=array[(*get)%MAXSHM];
printf("The Consumer B get number %d from No.%d\n",getnum,(*get)%MAXSHM);
(*get)++;                 //读计数加1
semop(mutexid,&V,1);      //对mutexid执行 V 操作
semop(emptyid,&V,1);      //对emptyid执行 V 操作
sleep(1);
}
printf("Consuner B is over\n");
exit(0);
}
}
}

/*   父进程返回后回收3个子进程  */
wait(0);
wait(0);
wait(0);

/*  断开并撤消2个共享内存  */
shmdt(array);
shmctl(arrayid,IPC_RMID,0);
shmdt(get);
shmctl(getid,IPC_RMID,0);
/*   撤消3个信号量集  */
semctl(emptyid,IPC_RMID,0);
semctl(fullid,IPC_RMID,0);
semctl(mutexid,IPC_RMID,0);
exit(0);
}

