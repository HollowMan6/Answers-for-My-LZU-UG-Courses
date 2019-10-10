#include<iostream>
#include"3.2.4.2.h"
using namespace std;
int main()
{
	int n,r;
	while(true){
		cin>>n>>r;
		if(n==0&&r==0)
			break;
		if(n<1||n<r)
			cout<<"Wrong input"<<endl;
		else
			cout<<Cnr(n,r)<<endl;
	}
}