#include<iostream>
using namespace std;
int main()
{
	double n1=1,n2=1,ans;
    bool flag=true;
	char op='+';
	while(true)
	{
        flag=true;
        //若输入0 0 0退出
        if(n1==0&&n2==0&&op=='0')
            break;
		cout<<"please input arithmatic expression:";
		cin>>n1>>op>>n2;
        //识别运算符
        switch(op)
        {
            case '+':
                ans=n1+n2;break;
            case '-':
                ans=n1*n2;break;
            case '*':
                ans=n1*n2;break;
            case '/':
                if(n2==0)
                {
                    flag=false;
                    cout<<"can't divide by 0!"<<endl;
                    break;
                }
                else
                    ans=n1/n2;break;
            default:
                flag=false;
                cout<<"wrong operator!"<<endl;
                break;
        }
        //输出结果
        if(flag)
            cout<<n1<<op<<n2<<"="<<ans<<endl;
	}
    return 0;
}
/*
[测试数据]
2+3
4-3
4*5
5/7
[思考与扩展]
1.防止输入0+0等特殊数据
2.阻止程序继续执行下一个case的语句
*/