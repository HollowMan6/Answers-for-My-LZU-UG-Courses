#include <stdio.h>
#include <unistd.h>
#include <math.h>
#include <sys/wait.h>
#include <stdlib.h>
int main()
{
    int fda[2], fdb[2];
    int pid, i;
    if(pipe(fda) < 0 || pipe(fdb) < 0)   //建立无名管道
        printf("pipe error");
    while((pid = fork()) == -1) ;   //创建子进程
    
    if(pid > 0) //父进程
    {
        FILE *fpread = fopen("data.txt", "r");  //打开文件data.txt
        float data, result;
        if(fpread == NULL)  //打开文件失败
        {
            printf("open file error!\n");
            exit(1);
        }
        close(fda[0]);  //关闭父进程管道a读取端
        close(fdb[1]);  //关闭父进程管道b写入端
        for(i = 0; i < 10; i++)
        {
            fscanf(fpread, "%f", &data);    //读入一个数据存入data
            write(fda[1], &data, sizeof(data)); //data通过管道a发送给子进程
            read(fdb[0], &result, sizeof(result));  //通过管道b读计算结果到result
            printf("sin(%f)=%f\n", data, result);
        }
        close(fdb[0]);  //关闭父进程管道b读取端
        close(fda[1]);  //关闭父进程管道a写入端
        fclose(fpread); //关闭文件data.txt
    }
    else    //子进程
    {
        float data, result;
        close(fda[1]);  //关闭子进程管道a写入端
        close(fdb[0]);  //关闭子进程管道b读取端
        for(i = 0; i < 10; i++)
        {
            read(fda[0], &data, sizeof(data));
            result = sinf(data);
            write(fdb[1], &result, sizeof(result));
        }
        close(fdb[1]);  //关闭子进程管道b写入端
        close(fda[0]);  //关闭子进程管道a读取端
    }
    return 0;
}
