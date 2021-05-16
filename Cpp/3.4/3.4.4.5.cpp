#include <iostream>
#include <math.h>
using namespace std;
int main(void)
{
    double a, b, c;
    char ch;
    cout<<"请输入表达式如 5+6 然后按回车键,若求sin输入如0s3，\ns后的数字为所要求的值（请输入弧度），cos为c，tan为t，,开方输入0q，高次方用^:";
    cin>>a>>ch>>b;
    switch (ch)
    {
    case '+':
        c = a + b;
        cout<<endl<<c;
        break;
    case '-':
        c = a - b;
        cout<<endl<<c;
        break;
    case '*':
        c = a * b;
        cout<<endl<<c;
        break;
    case '/':
        c = a / b;
        cout<<endl<<c;
        break;
    case '^':
        c = pow(a,b);
        cout<<endl<<c;
        break;
    case 'q':
        c = sqrt(b);
        cout<<endl<<c;
        break;
    case 's':
        c = sin(b);
        cout<<endl<<c;
        break;
    case 't':
        c = tan(b);
        cout<<endl<<c;
        break;
    case 'c':
        c = cos(b);
        cout<<endl<<c;
        break;
    default:
        printf("输入表达式错误或该计算器不具备 %ch 功能\n", ch);
    }
}