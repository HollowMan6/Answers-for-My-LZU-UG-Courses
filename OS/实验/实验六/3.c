#include<stdio.h>
main()
{
    FILE * fp;
    char buffer[80];
    fp=popen("cat /etc/passwd","r");    //子进程执行cat /etc/passwd，建立管道I/O连接到子进程标准输出设备
    fgets(buffer,sizeof(buffer),fp);    //读取一行内容
    printf("%s",buffer);    //打印buffer的内容
    pclose(fp); //关闭管道I/O
}
