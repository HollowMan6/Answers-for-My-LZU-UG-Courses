#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>

main(int argc,char *argv[])
{
int f_des[2];
static char message[BUFSIZ];
if(argc!=2) 
{printf("error\n");
exit(1);}

if (pipe(f_des)==-1)
{printf("error\n");
 exit(2);}

switch (fork()){
case -1:printf("error,fork\n");exit(3);
case 0: close(f_des[1]);
             
       if (read(f_des[0],message,BUFSIZ)!=-1)

           {printf("message received by child:[%s]\n",message); 
             
             fflush(stdout);}
       else {printf("error,read\n");exit(4);} 
       break;
default: close(f_des[0]) ;
             if (write(f_des[1],argv[1],strlen(argv[1]))!=-1)
             {printf("message send by parent:[%s]\n",argv[1]); 
             fflush(stdout);}
             else {printf("error,write\n");exit(5);}
}
exit(0);
}
