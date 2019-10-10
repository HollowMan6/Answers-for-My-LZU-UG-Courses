#include <stdio.h>
#include <stdlib.h>
#define SIZE 3
struct Person
{
    char nam[6];
    char dep[6];
    int base;
    int allo;
    int total;
} per[SIZE];
int main()
{
    FILE *fp;
    int i, m;
    if ((fp = fopen("paydata.txt", "wb")) == NULL)
    {
        printf("无法打开文件\n");
        exit(0);
    }
    printf("请输入人数:");
    scanf("%d", &m);
    printf("请输入人员信息:\n");
    printf("name department  base-pay allowance\n");
    for (i = 0; i < m; i++)
        scanf("%s %s %d %d", per[i].nam, per[i].dep, &per[i].base, &per[i].allo);
    for (i = 0; i < m; i++)
        if (fwrite(&per[i], sizeof(struct Person), 1, fp) != 1)
            printf("写入文件错误\n");
    fclose(fp);
    printf("新的：\nname department  base-pay allowance\n");
    fp = fopen("paydata.txt", "rb");
    for (i = 0; i < m; i++)
    {
        fread(&per[i], sizeof(struct Person), 1, fp);
        printf("%s %s %d %d %d\n", per[i].nam, per[i].dep, per[i].base + 100, per[i].allo, per[i].base + per[i].allo + 100);
    }
    fclose(fp);
    system("pause");
    return 0;
}