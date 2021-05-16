#include <stdio.h>
#include <stdlib.h>
#define LEN sizeof(struct String)
struct String
{
    struct String *last;
    char s;
};
int main()
{
    struct String *str, *end;
    char c;
    printf("请输入一个字符串:\n");
    scanf("%c", &c); //输入字符串， 并读取第一个字符
    str = (struct String *)malloc(LEN);
    str->last = NULL;
    while (c != '\n') //将字符串存入链表中， 终止条件，读到回车的时候停止
    {
        str->s = c;                         //将该字符存入节点
        end = (struct String *)malloc(LEN); // 开辟一个新结点
        end->last = str;                    //每一位字符 .last 指向上一位
        str = end;
        scanf("%c", &c); //读取字符串的下一个字符
    }                    //此时 end 指针指向最后一位
    printf(" 逆序排列为： \n");
    for (; end->last != NULL;) //逆序输出
    {
        end = end->last;
        printf("%c", end->s);
    }
    printf("\n");
    system("pause");
}