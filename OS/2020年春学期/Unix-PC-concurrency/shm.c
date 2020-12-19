#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/ipc.h>
#include <sys/shm.h>

 
int main(void)

{

    int x, shmid;

    int *shmptr;

    if((shmid=shmget(IPC_PRIVATE, sizeof(int), IPC_CREAT|0666)) < 0)

        printf("shmget error"), exit(1);

    if((shmptr=(int *)shmat(shmid, 0, 0)) == (int *)-1)

        printf("shmat error"), exit(1);

    printf("Input a initial value for *shmptr: ");

    scanf("%d", shmptr);

    while((x=fork())==-1);

    if(x==0)         
    {

        printf("When child runs, *shmptr=%d\n", *shmptr);

        printf("Input a value in child: ");

        scanf("%d", shmptr);

        printf("*shmptr=%d\n", *shmptr);

    }

    else         
    {

        wait();

        printf("After child runs, in parent, *shmptr=%d\n", *shmptr);

    if ( shmctl(shmid, IPC_RMID, 0) < 0 )

        printf("shmctl error"), exit(1);

    }

        return 0;

}

