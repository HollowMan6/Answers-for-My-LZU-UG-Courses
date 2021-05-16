#include <iostream>
#include <string>
using namespace std;
class Student{
    private:
        int num,bthd,bthm;
        string name,grade,cla,coll,maj;
    public:
    //带参数的构造函数、带默认参数的构造函数 合二为一
        Student(int num,string name,int bthd,int bthm,string cla,string coll,string maj,string grade="05"){
            this->num=num;
            this->name=name;
            this->bthd=bthd;
            this->bthm=bthm;
            this->grade=grade;
            this->cla=cla;
            this->coll=coll;
            this->maj=maj;
        }
        Student(){
            this->num=0;
            this->name="";
            this->bthd=0;
            this->bthm=0;
            this->grade="";
            this->cla="";
            this->coll="";
            this->maj="";
        }
        void SetInfo(string cla,string coll,string maj){
            this->cla=cla;
            this->coll=coll;
            this->maj=maj;
        }
        void Show(){
            cout<<"学号："<<num<<" 姓名："<<name<<" 出生日期："<<bthd<<"年 "<<bthm<<"月 "<<" 年级："<<grade<<"级 班级："<<cla<<" 院系："<<coll<<" 专业："<<maj<<endl;
        }
};
int main(void){
    int num,bthd,bthm,n,count=0;
    string name,grade,cla,coll,maj;
    cout<<"请输入要存储的学生数：";
    cin>>n;
    Student stu[n];
    while(count<n){
        cout<<"请输入第"<<count+1<<"个学生的信息:";
        cout<<"学号：";
        cin>>num;
        while(num<0){
            cout<<"请输入正数！"<<endl;
            cout<<"学号：";
            cin>>num;
        }
        cout<<"姓名：";
        cin>>name;
        cout<<"出生日期"<<endl;
        cout<<" 年：";
        cin>>bthd;
        while(bthd<0){
            cout<<"请输入正数！"<<endl;
            cout<<" 年：";
            cin>>bthd;
        }
        cout<<" 月：";
        cin>>bthm;
        while(bthm<1||bthm>12){
            cout<<"请输入正确的月份！"<<endl;
            cout<<" 月：";
            cin>>bthm;
        }
        cout<<"年级：";
        cin>>grade;
        cout<<"班级：";
        cin>>cla;
        cout<<"院系：";
        cin>>coll;
        cout<<"专业：";
        cin>>maj;
        stu[count]={num,name,bthd,bthm,grade,cla,coll,maj};
        cout<<endl;
        count+=1;
    }
    for(int i=0;i<n;i++){
        cout<<i+1<<"：";
        stu[i].Show();
    }
}
/*
[思考与扩展]
设置类中的静态变量，每次创建新对象输入学号后自动加一。
*/