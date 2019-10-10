#include<iostream>
using namespace std;
class CTime
{
    private:
        int hour;
        int minute;
        int second;

    public:
        CTime(int hour,int minute,int second)
        {
            this->hour = hour;
            this->minute = minute;
            this->second = second;
        }
        CTime()
        {
            hour = 0;
            minute = 0;
            second = 0;
        }

        void SetHour(int mhour)
        {
            hour = mhour;
        }

        void SetMinute(int min)
        {
            minute = min;
        }

        void SetSecond(int sec)
        {
            second = sec;
        }

        void Display()
        {
            cout<<hour<<":"<<minute<<":"<<second<<"\n";
        }

        CTime operator++();
};
CTime CTime::operator++()
{
    second++;
    if (second == 60)
    {
        minute++;
        second = 0;
    }
    if (minute == 60)
    {
        hour++;
        minute = 0;
    }
    if (hour == 24)
        hour = 0;
    return CTime(*this);
}
int main()
{
    int hour;
    int minute;
    int second;
    char f;
    cout<<"请输入时间(格式：13:48:39):\n";
    cin>>hour>>f>>minute>>f>>second;
    while(second>60||second<0||hour>24||hour<0||minute>60||minute<0)
    {
        cout<<"时间输入错误，请输入正确的时间:\n";
        cin>>hour>>f>>minute>>f>>second;
    }
    CTime t(hour,minute,second);
    ++t;
    cout<<"下一秒:\n";
    t.Display();
}