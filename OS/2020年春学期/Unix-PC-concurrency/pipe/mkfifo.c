#include<sys/types.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<sys/types.h>
#include <sys/stat.h>
#define FIFO "myfifo"
main()
{
char buffer[80];
int fd;
unlink(FIFO); /* delete FIFO */
mkfifo(FIFO,0666);  /*create FIFO */
if(fork()>0)
{
char s[]="hello.\n";
fd = open(FIFO,O_WRONLY);   /*open FIFO */
write(fd,s,sizeof(s));   /*write into */
close(fd);
}
//else{
//fd = open(FIFO,O_RDONLY); /*open FIFO */
//read(fd,buffer,80);    /* read string from FIFO  */
//printf("%s",buffer);
//close(fd);
//}
}

