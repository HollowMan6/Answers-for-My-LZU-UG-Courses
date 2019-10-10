#include <iostream>
#include <string>
#define N 100
using namespace std;
class Date
{
private:
    int year;
    int month;
    int day;

public:
    void SetYear(int x) { year = x; }
    void SetMonth(int x) { month = x; }
    void SetDay(int x) { day = x; }
    int GetYear() { return year; }
    int GetMonth() { return month; }
    int GetDay() { return day; }
    Date() : year(2000), month(1), day(1) {}
    Date(int x, int y, int z)
    {
        year = x;
        month = y;
        day = z;
    }
};
class employee
{
private:
    int no;
    string name;
    string sex;
    Date birth;
    string pos;

public:
    employee() : no(0), birth(2000, 1, 1)
    {
        name = "Andy";
        sex = "male";
        pos = "intern";
    }
    employee(int n, string str, string y, Date day, string p)
    {
        no = n;
        name = str;
        sex = y;
        birth = day;
        pos = p;
    }
    friend string Getname(employee x)
    {
        string name1;
        name1 = x.name;
        return name1;
    }
    friend int Getno(employee x)
    {
        return x.no;
    }
    friend string Getsex(employee x)
    {
        string sex1;
        sex1 = x.sex;
        return sex1;
    }
    friend Date Getbirth(employee x)
    {
        return x.birth;
    }
    friend string Getpos(employee x)
    {
        string pos1;
        pos1 = x.pos;
        return pos1;
    }
};
int main()
{
    employee *persons[N];
    employee temp;
    Date day;
    int num = 0;
    int i, choose = 1, choose1 = 0;
    int n, y, m, d;
    string str;
    string s;
    string p;
    cout << "\n\t\t=======================================" << endl;
    cout << "\t\t1： 录入 2： 显示 3： 修改 4： 查找 0： 退出" << endl;
    cout << "\t\t=======================================" << endl;
    cout << "\n输入您要进行的操作 :";
    cin >> choose;
    while (choose != 0)
    {
        switch (choose)
        {
        case 1:
            if (num == 100)
            {
                cout << "full!\n";
                continue;
            }
            cout << "输入员工编号： ";
            cin >> n;
            cout << "输入员工生日： ";
            cin >> y >> m >> d;
            cout << "输入员工姓名： ";
            cin >> str;
            cout << "输入员工性别： ";
            cin >> s;
            cout << "输入员工职位： ";
            cin >> p;
            persons[num++] = new employee(n, str, s, Date(y, m, d), p);
            break;
        case 2:
            cout << "编号\t"
                 << " 姓名\t"
                 << " 性别\t"
                 << " 生日\t\t"
                 << " 职务"<<endl;for(i=0;i<num;i++)
            {
                cout << Getno(*persons[i]) << "\t" << Getname(*persons[i]) << "\t" << Getsex(*persons[i]) << "\t" << Getbirth(*persons[i]).GetYear()
                     << " 年" << Getbirth(*persons[i]).GetMonth() << " 月"<<Getbirth(*persons[i]).GetDay()<<" 日 "<<"\t "<<Getpos(*persons[i])<<endl;
            }
            break;
        case 3:
            cout << "输入员工编号 :";
            cin >> n;
            for (i = 0; i <= num - 1; i++)
            {
                if (n == Getno(*persons[i]))
                {
                    cout << "\n\t\t=================================="<<endl;
                            cout
                         << "\t\t1 ：名字修改 2：职位修改"<<endl;cout<<"\t\t ==== == == == == == == == == == == == == == == == == "<<endl
                        ;
                    cout << "选择您要进行的修改方式 :" << endl;
                    cin >> choose1;
                    switch (choose1)
                    {
                    case 1:
                        cout << "输入员工姓名 :";
                        cin >> str;
                        n = Getno(*persons[i]);
                        s = Getsex(*persons[i]);
                        y = Getbirth(*persons[i]).GetYear();
                        m = Getbirth(*persons[i]).GetMonth();
                        d = Getbirth(*persons[i]).GetDay();
                        p = Getpos(*persons[i]);
                        delete persons[i];
                        persons[i] = new employee(n, str, s, Date(y, m, d), p);
                        break;
                    case 2:
                        cout << "输入员工职位 :";
                        cin >> p;
                        n = Getno(*persons[i]);
                        str = Getname(*persons[i]);
                        s = Getsex(*persons[i]);
                        y = Getbirth(*persons[i]).GetYear();
                        m = Getbirth(*persons[i]).GetMonth();
                        d = Getbirth(*persons[i]).GetDay();
                        delete persons[i];
                        persons[i] = new employee(n, str, s, Date(y, m, d), p);
                        break;
                    }
                }
            }
            break;
        case 4:
            cout << "\n\t\t===============================" << endl;
            cout << "\t\t1：按编号 2：按姓名 " << endl;
            cout << "\t\t=================================" << endl;
            cout << "\n输入您要进行的操作 :";
            cin >> n;
            switch (n)
            {
            case 1:
                cout << "输入员工编号 :";
                cin >> n;
                for (i = 0; i <= num; i++)
                    if (n == Getno(*persons[i]))
                    {
                        cout << "编号 \t"
                             << " 姓名\t"
                             << " 性别\t"
                             << " 生日\t\t"
                             << " 职务 "<<endl;
                            cout<< Getno(*persons[i]) << "\t" << Getname(*persons[i]) << "\t" << Getsex(*persons[i]) << "\t" << Getbirth(*persons[i]).GetYear()
                             << " 年" << Getbirth(*persons[i]).GetMonth() << " 月"<<Getbirth(*persons[i]).GetDay()<<" 日 "<<"\t "<<Getpos(*persons[i])<<endl;
                            break;
                    }
                if (i == num)
                    cout << "该员工不存在 " << endl;
                break;
            case 2:
                cout << "输入员工姓名： ";
                cin >> str;
                for (i = 0; i <= num; i++)
                    if (str == Getname(*persons[i]))
                    {
                        cout << "编号 \t"
                             << " 姓名\t"
                             << " 性别\t"
                             << " 生日\t\t"
                             << " 职务 "<<endl;
                            cout << Getno(*persons[i]) << "\t" << Getname(*persons[i]) << "\t" << Getsex(*persons[i]) << "\t" << Getbirth(*persons[i]).GetYear()
                             << " 年" << Getbirth(*persons[i]).GetMonth() << " 月"<<Getbirth(*persons[i]).GetDay()<<" 日 "<<"\t "<<Getpos(*persons[i])<<endl;
                            break;
                    }
                if (i == num)
                    cout << "该员工不存在 " << endl;
                break;
            }
            break;
        }
        cout << "\n\t\t=======================================" << endl;
        cout << "\t\t1： 录入 2： 显示 3： 修改 4： 查找 0： 退出" << endl;
        cout << "\t\t=======================================" << endl;
        cout << "\n输入您要进行的操作 :";
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