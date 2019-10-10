#include <stdio.h>
#include <string.h>
void sort(char a[], int len)
{
    int i, j;
    char temp;
    for (j = 0; j < len - 1; j++)
        for (i = 0; i < len - 1; i++)
            if (a[i] > a[i + 1])
            {
                temp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = temp;
            }
}
int main()
{
    void sort(char a[], int len);
    char a[100], b[100], c[100], d[100], same[100];
    int i, j, k, l1, l2;
    printf("请输入有序表 A:\n");
    gets(a);
    sort(a, strlen(a));
    printf("\n请输入有序表 B:\n");
    gets(b);
    sort(b, strlen(b));
    printf("\n请输入有序表 C:\n");
    gets(c);
    sort(c, strlen(c));
    printf("\n有序表 A,B,C 依次为:\n");
    puts(a);
    puts(b);
    puts(c);
    for (i = j = k = 0; (i < (l1 = strlen(b))) && (j < (l2 = strlen(c)));)
    {
        if (b[i] == c[j])
        {
            same[k] = b[i];
            i++;
            j++;
            k++;
        }
        else if (b[i] < c[j])
            i++;
        else
            j++;
    }
    same[k] = '\0';
    if (same[0] == '\0')
        strcpy(d, a);
    else
    {
        for (i = j = k = 0; (i < (l1 = strlen(a))) && (j < (l2 = strlen(same)));)
        {
            if (a[i] == same[j])
                i++;
            else if (a[i] < same[j])
            {
                d[k] = a[i];
                i++;
                k++;
            }
            else
                j++;
        }
        while (a[i] != '\0')
        {
            d[k] = a[i];
            k++;
            i++;
        }
        d[k] = '\0';
    }
    printf("\n有序表 D 是:%s\n", d);
    system("pause");
}