#include<iostream>
using namespace std;
double fn(int n){
	if(n==0||n==1)
		return 1;
	else
		return fn(n-1);
		
}
double Cnr(int n, int r){
	if(r==0)
		return 1;
	else if(r==1)
		return n;
	else
		return Cnr(n, r-1)*(n-r+1)/r;
}
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