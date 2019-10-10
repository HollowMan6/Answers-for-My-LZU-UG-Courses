#include <string>
#include <iostream>
using namespace std;
class Teacher
{
public:
    Teacher(string nam, int a, string s, string tit, string ad, string t);
    void display();

protected:
    string name;
    int age;
    string sex;
    string title;
    string addr;
    string tel;
};

Teacher::Teacher(string nam, int a, string s, string tit, string ad, string t) : name(nam), age(a), sex(s), title(tit), addr(ad), tel(t) {}
void Teacher::display()
{
    cout << "姓名:" << name << ends;
    cout << "年龄:" << age << ends;
    cout << "性别:" << sex << ends;
    cout << "地址:" << addr << ends;
    cout << "电话:" << tel << ends;
    cout << "职称:" << title << ends;
}

class Cadre
{
public:
    Cadre(string nam, int a, string s, string p, string ad, string t);
    void display();

protected:
    string name;
    int age;
    string sex;
    string post;
    string addr;
    string tel;
};

Cadre::Cadre(string nam, int a, string s, string p, string ad, string t) : name(nam), age(a), sex(s), post(p), addr(ad), tel(t) {}

void Cadre::display()
{
    cout << "姓名:" << name << ends;
    cout << "年龄:" << age << ends;
    cout << "性别:" << sex << ends;
    cout << "地址:" << addr << ends;
    cout << "电话:" << tel << ends;
    cout << "职务:" << post << ends;
}

class Teacher_Cadre : public Teacher, public Cadre
{
public:
    Teacher_Cadre(string nam, int a, string s, string tit, string p, string ad, string t, float w);
    void show();

private:
    float wage;
};

Teacher_Cadre::Teacher_Cadre(string nam, int a, string s, string t, string p, string ad, string tel, float w) : Teacher(nam, a, s, t, ad, tel), Cadre(nam, a, s, p, ad, tel), wage(w) {}
void Teacher_Cadre::show()
{
    Teacher::display();
    cout << "职务:" << Cadre::post << ends;
    cout << "工资:" << wage << endl;
}

int main()
{
    Teacher_Cadre te_ca1("zhang", 20, "male", "tutor（助教）", "普通职工", "Build 305-503", "6081202", 3538.7);
    te_ca1.show();
    Teacher_Cadre te_ca2("chen", 38, "male", "associate professor（副教授）", "副院长", "Build 3A-302", "6088132", 6534.5);
    te_ca2.show();
    Teacher_Cadre te_ca3("fan", 30, "female", "lectuer（讲师）", "教研室主任", "Build 2A-202", "6088099", 5305.3);
    te_ca3.show();
    return 0;
}