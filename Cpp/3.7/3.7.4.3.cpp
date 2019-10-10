#include <iostream>
#include <string.h>
using namespace std;
class Student
{
private:
    char name[10];
    int deg1, deg2, deg3;

public:
    Student(char na[], int d1, int d2, int d3)
    {
        for (int i = 0; i < (strlen(na) < strlen(name) ? strlen(na) : strlen(name)); i++)
        {
            name[i] = na[i];
        }
        this->deg1 = d1;
        this->deg2 = d2;
        this->deg3 = d3;
    }
    Student(int deg1 = 0, int deg2 = 0, int deg3 = 0)
    {
        name[10] = {'\0'};
        this->deg1 = deg1;
        this->deg2 = deg2;
        this->deg3 = deg3;
    }
    friend Student operator+(Student s1, Student s2)
    {
        Student tmp;
        tmp.deg1 = s1.deg1 + s2.deg1;
        tmp.deg2 = s1.deg2 + s2.deg2;
        tmp.deg3 = s1.deg3 + s2.deg3;
        return tmp;
    }
    friend void avg(Student s[], int n)
    {
        for (int i = 0; i < n; i++)
        {
            cout << i + 1 << ". 姓名：" << s[i].name << " 高数：" << s[i].deg1 << " 英语：" << s[i].deg2 << " 计算机："
                 << s[i].deg3 << endl;
        }
        Student tmp = Student();
        for (int i = 0; i < n; i++)
            tmp = s[i] + tmp;
        cout << " 班级平均分："
             << "\n 高数：" << tmp.deg1 / n << " 英语：" << tmp.deg2 / n << " 计算机："
             << tmp.deg3 / n << endl;
    }
};
int main(void)
{
    int d1, d2, d3, n, count = 0;
    char na[10];
    cout << "请输入要存储的学生数：";
    cin >> n;
    Student stu[n];
    while (count < n)
    {
        cout << "请输入第" << count + 1 << "个学生的信息:";
        cout << "姓名：";
        cin >> na;
        cout << "高数：";
        cin >> d1;
        while (d1 < 0)
        {
            cout << "请输入正数！" << endl;
            cout << "高数：";
            cin >> d1;
        }
        cout << "英语：";
        cin >> d2;
        while (d2 < 0)
        {
            cout << "请输入正数！" << endl;
            cout << "英语：";
            cin >> d2;
        }
        cout << "计算机：";
        cin >> d3;
        while (d3 < 0)
        {
            cout << "请输入正数！" << endl;
            cout << "计算机：";
            cin >> d3;
        }
        stu[count] = {na, d1, d2, d3};
        cout << endl;
        count += 1;
    }
    avg(stu, n);
}