#include <sys/stat.h>
#define FIFO "myfifo"
main()
{
char buffer[80];
int fd;
fd = open(FIFO,"r"); /*open FIFO */
read(fd,buffer,80);    /*read string from FIFO */
printf("%s\n",buffer);
close(fd);
unlink(FIFO);
}

