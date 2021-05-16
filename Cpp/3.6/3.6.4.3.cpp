#include <iostream>
#include <string>
#include "3.6.4.3.h"
using namespace std;
void Date::SetYear(int x) { year = x; }
void Date::SetMonth(int x) { month = x; }
void Date::SetDay(int x) { day = x; }
int Date::GetYear() { return year; }
int Date::GetMonth() { return month; }
int Date::GetDay() { return day; }
void employee::display()
{
    cout << " 编 号 :" << num << " 姓 名 :" << nam << " 性 别 :" << sex << " 出 生 日 期 :" << birth.GetYear() << "-" << birth.GetMonth() << "-" << birth.GetDay() << " 职位 :" << position;
}
void manager::set_manager(int n, string na, string se, int y, int m, int d, string pos)
{
    num = n;
    nam = na;
    sex = se;
    birth.SetYear(y);
    birth.SetMonth(m);
    birth.SetDay(d);
    position = pos;
}
void manager::display()
{
    employee::display();
}
void manager::total_salary()
{
    salary = 8000;
    cout << " 经理月薪： ";
    cout << salary << " 元";
}
void technician::set_technician(int n, string na, string se, int y, int m, int d, string pos, float t)
{
    num = n;
    nam = na;
    sex = se;
    birth.SetYear(y);
    birth.SetMonth(m);
    birth.SetDay(d);
    position = pos;
    Time = t;
}
void technician::display()
{
    employee::display();
    cout << " 工作时间 :" << Time << endl;
}
void technician::total_salary()
{
    salary = 25 * Time;
    cout << " 技术人员月薪： ";
    cout << salary << " 元";
}
void saleman::set_saleman(int n, string na, string se, int y, int m, int d, string pos, float sale1, string de)
{
    num = n;
    nam = na;
    sex = se;
    birth.SetYear(y);
    birth.SetMonth(m);
    birth.SetDay(d);
    position = pos;
    sale = sale1;
    dep = de;
}
void saleman::display()
{
    employee::display();
    cout << position << " 销售额 :" << sale << " 所属部门 :" << dep << endl;
}
void saleman::total_salary()
{
    salary = sale * 0.04;
    cout << " 销售员月薪 :" << salary << " 元 ";
}
void salesmanager::total_salary()
{
    salary = sale * 0.05 + 5000;
    cout << " 销售经理月薪 :" << salary << " 元";
}
int main()
{
    manager m1;
    technician tec;
    saleman s;
    salesmanager sg;
    int n, y, m, d, choose, choose1;
    string str, se, p;
    cout << "\n\t\t=================================================" << endl;
    cout << "\t\t1: 输入信息与显示 2:个人月薪 0：退出 " << endl;
    cout << "\t\t==================================================" << endl;
    cout << "\n 输入您要进行的操作 :";
    cin >> choose;
    while (choose != 0)
    {
        switch (choose)
        {
        case 1:
            cout << " 输入员工编号： ";
            cin >> n;
            cout << " 输入员工出生日期： ";
            cin >> y >> m >> d;
            cout << " 输入员工姓名： ";
            cin >> str;
            cout << " 输入员工性别： ";
            cin >> se;
            cout << " 输入员工职位： ";
            cin >> p;
            if (p == "总经理")
            {
                m1.set_manager(n, str, se, y, m, d, p);
                m1.display();
            }
            else if (p == "技术人员")
            {
                float t;
                cout << " 输入时间： ";
                cin >> t;
                tec.set_technician(n, str, se, y, m, d, p, t);
                tec.display();
            }
            else if (p == "销售员")
            {
                float sale2;
                string de;
                cout << " 销售额： ";
                cin >> sale2;
                cout << " 所属部门： ";
                cin >> de;
                s.set_saleman(n, str, se, y, m, d, p, sale2, de);
                s.display();
            }
            else
            {
                float sale1;
                string de;
                cout << " 销售额： ";
                cin >> sale1;
                cout << " 所属部门： ";
                cin >> de;
                sg.set_saleman(n, str, se, y, m, d, p, sale1, de);
                s.display();
            }
            break;
        case 2:
            cout << "\n\t\t====================================================" << endl;
            cout << "\t\t11: 总经理 12: 技术人员 13：销售员 14:销售经理 " << endl;
            cout << "\t\t============================================================" << endl;
            cout << "\n 输入您要进行的操作 :";
            cin >> choose1;
            while (choose1 != 0)
            {
                switch (choose1)
                {
                case 11:
                    m1.total_salary();
                    break;
                case 12:
                    tec.total_salary();
                    break;
                case 13:
                    s.total_salary();
                    break;
                case 14:
                    sg.total_salary();
                    break;
                }
                choose1 = 0;
            }
            break;
        }
        cout << "\n\t\t==================================================" << endl;
        cout << "\t\t1: 输入信息与显示 2:个人月薪 0：退出 " << endl;
        cout << "\t\t==================================================" << endl;
        cout << "\n 输入您要进行的操作 :";
        cin >> choose;
    }
    return 0;
}
/*
创建结构体
struct node{
    employee data;
    employee *next;
    }
存储已输入职工对象.
遍历链表进行查询
*/