#include <iostream>
#include <string.h>
using namespace std;
class String
{
private:
    char *p;

public:
    String()
    {
        p = NULL;
    }
    String(char *pp)
    {
        p = pp;
    }
    friend bool operator==(String &a, String &b);
    friend bool operator>(String &a, String &b);
    friend bool operator<(String &a, String &b);
    void display()
    {
        cout << p;
    }
};
bool operator==(String &a, String &b)
{
    if (strcmp(a.p, b.p) == 0)
    {
        return true;
    }
    else
    {
        return false;
    }
}
bool operator>(String &a, String &b)
{
    if (strcmp(a.p, b.p) > 0)
    {
        return true;
    }
    else
    {
        return false;
    }
}
bool operator<(String &a, String &b)
{
    if (strcmp(a.p, b.p) < 0)
    {
        return true;
    }
    else
    {
        return false;
    }
}
void compare(String a, String b)
{
    if (operator>(a, b))
    {
        a.display();
        cout << ">";
        b.display();
    }
    else if (operator<(a, b))
    {
        a.display();
        cout << "<";
        b.display();
    }
    else
    {
        a.display();
        cout << "==";
        b.display();
    }
}
int main()
{
    char p1[101];
    char p2[101];
    cout<<"请输入两个字符串来进行比较,用空格分开：\n";
    cin>>p1>>p2;
    String string(p1);
    String string1(p2);
    compare(string, string1);
    cout<<endl;
}
/*
[思考与扩展]
char* operator<<(String &a, String &b)
{
    return strcat(a.p,b.p)
}
char* operator>>(String &a, String &b)
{
    return strcat(b.p,a.p)
}
*/