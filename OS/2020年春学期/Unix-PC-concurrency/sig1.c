#include <signal.h>
#include <stdio.h>
#include <stdlib.h>

void catchint();

main()
{

int i;
signal(SIGINT,catchint);
for(i=0;i<8;i++)
{printf("sleep call #%d\n",i);
 sleep(1);
}
printf("Exiting\n");
exit(0);
}

void catchint(signo)
int signo;
{printf("\nCatchint: signo=%d;\n",signo);
sleep(2);
signal(SIGINT,SIG_IGN);
printf("Catchint Returning.\n");
//signal(SIGINT,catchint);
//signal(SIGINT,SIG_DFL);

}
