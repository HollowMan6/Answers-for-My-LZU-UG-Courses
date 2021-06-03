#include <stdio.h>
#include <unistd.h>
int main() {
    printf("page-size:%d\n", getpagesize());
}