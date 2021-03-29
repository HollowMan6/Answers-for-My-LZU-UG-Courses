////本程序的功能是把一个文件映射到
//内存然后再把内存中的东西利用write函数给输出到标准输出
//最后利用munmap解除内存映射
#include <stdio.h>
#include <sys/mman.h>
#include <fcntl.h>
#include <unistd.h>
int main(int argc, char *argv[])
{
    if (argc != 4)
    {
        write(STDOUT_FILENO, "hello\n", 6);
        printf("usage:%s <filename> <length>\n", argv[0]);
        return 1;
    }
    char *filename = argv[1]; //第二个参数表示文件名
    printf("the file name to be mapped is :%s\n", filename);
    int fd = open(filename, O_RDONLY);
    int offset = atoi(argv[2]); //第三个参数标识要映射时的开始偏移量
    printf("start offset of file to be mapped is :%d\n", offset);
    printf("page size is %ld\n", sysconf(_SC_PAGE_SIZE));
    int realOffset = offset & ~(sysconf(_SC_PAGE_SIZE) - 1);
    printf("real start offset of file to be mapped is %d\n", realOffset);
    int length = atoi(argv[3]); //要映射的文件的长度
    printf("the length to be map is :%d\n", length);
    int reallen = length + offset - realOffset; //实际映射的长度
    printf("the real length to be map is %d\n", reallen);
    char *addr = mmap(NULL, reallen, PROT_READ, MAP_PRIVATE, fd, realOffset); //有关该函数我们可以使用 //man命令去查看
    close(fd);
    write(STDOUT_FILENO, addr, reallen); /*输出到标准输出*/
    munmap(addr, reallen);               //解除内存映射
    printf("\n");
    return 0;
}
