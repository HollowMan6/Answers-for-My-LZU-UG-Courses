/* 利用mmap()来读取/etc/passwd 文件内容*/
#include<sys/types.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<unistd.h>
#include<sys/mman.h>
main()
{
    int fd;
    void *start;
    struct stat sb;
    fd=open("/etc/passwd",O_RDONLY); /*打开/etc/passwd*/
    fstat(fd,&sb); /*取得文件大小*/
    /* 利用man fstat 可以看到struct stat 的定义*/
    start=mmap(NULL,sb.st_size,PROT_READ,MAP_PRIVATE,fd,0);
    if(start== MAP_FAILED) /*判断是否映射成功*/
    return;
    printf("%s",start);
    munmap(start,sb.st_size); /*解除映射*/
    close (fd);
}
