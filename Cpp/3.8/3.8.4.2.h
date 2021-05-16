//蒋嵩林 2018计算机基地班 320180901941
#ifndef _3_8_4_2_H
#define _3_8_4_2_H
#include <string>
using std::string;
class Date
{
private:
    int year;
    int month;
    int day;

public:
    void SetYear(int x);
    void SetMonth(int x);
    void SetDay(int x);
    int GetYear();
    int GetMonth();
    int GetDay();
};
class employee
{
protected:
    int num;
    string nam;
    string sex;
    Date birth;
    string position;
    float salary;

public:
    virtual void display()=0;
    virtual void pay()=0;
    void promote();
};
class manager : virtual public employee
{
public:
    void set_manager(int n, string na, string se, int y, int m, int d, string pos);
    void display();
    void pay();
};
class technician : virtual public employee
{
protected:
    float Time;

public:
    float t;
    void set_technician(int n, string na, string se, int y, int m, int d, string pos, float t);
    void display();
    void pay();
};
class saleman : virtual public employee
{
protected:
    float sale;
    string dep;

public:
    void set_saleman(int n, string na, string se, int y, int m, int d, string pos, float sale1, string de);
    void display();
    void pay();
};
class salesmanager : public manager, public saleman
{
public:
    void display();
    void pay();
};
#endif