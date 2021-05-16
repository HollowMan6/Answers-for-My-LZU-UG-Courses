#include <iostream>
using namespace std;
int a[3][3],b[3][3],c[3][3];
void matrixmul(int (*a)[3],int (*b)[3])
{
	for(int i=0;i<3;i++)
		for(int j=0;j<3;j++)
			for(int k=0;k<3;k++)
				c[i][j] += a[i][k]*b[k][j];
}
int main()
{
	for(int i=0;i<3;i++)
		for(int j=0;j<3;j++)
			cin>>a[i][j];
	for(int i=0;i<3;i++)
		for(int j=0;j<3;j++)
			cin>>b[i][j];
	matrixmul(a,b);
	cout<<endl;
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
			cout<<b[i][j]<<" ";
		cout<<endl;
	}
}
/*
[思考与扩展]
1.
#include <iostream>
using namespace std;
int a[4][3],b[3][2],c[4][4];
void matrixmul(int (*a)[3],int (*b)[2])
{
	for(int i=0;i<4;i++)
		for(int j=0;j<2;j++)
			for(int k=0;k<3;k++)
				c[i][j] += a[i][k]*b[k][j];
}
int main()
{
	for(int i=0;i<4;i++)
		for(int j=0;j<3;j++)
			cin>>a[i][j];
	for(int i=0;i<3;i++)
		for(int j=0;j<2;j++)
			cin>>b[i][j];
	matrixmul(a,b);
	cout<<endl;
	for(int i=0;i<4;i++)
	{
		for(int j=0;j<2;j++)
			cout<<b[i][j]<<" ";
		cout<<endl;
	}
}
2、因为数组名为地址，所以形参直接对实参值进行操作
*/