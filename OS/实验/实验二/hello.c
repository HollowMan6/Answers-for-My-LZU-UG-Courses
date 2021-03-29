#include <stdio.h>
int main()
{
    int n, a[200], carry, temp, i, j, digit = 1;
    printf("Please input n:");
    scanf("%d", &n);
    a[0] = 1;
    for (i = 2; i <= n; ++i)
    {
        for (j = 1, carry = 0; j <= digit; ++j)
        {
            temp = a[j - 1] * i + carry;
            a[j - 1] = temp % 10;
            carry = temp / 10;
        }
        while (carry)
        {
            a[++digit - 1] = carry % 10;
            carry /= 10;
        }
    }
    printf("Result is:\n%d ! = ", n);
    for (i = digit; i >= 1; --i)
    {
        printf("%d", a[i - 1]);
    }
    printf("\n");
}
