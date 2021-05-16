#include "stdio.h" 
//#include <thread.h>
#include "stdafx.h"
#include "windows.h" 
#include "iostream.h"
#include "winbase.h" 


//全局变量
int N=0;

DWORD SubThreadA(void * pD)
{
	int iID=(int)pD;
	
    for(int i=0;i<100;i++)
	{
		Sleep(100);
		N=N+1;
		cout << i <<"--"<< " Thread " << iID <<" : " << N << endl;
	}
	return 0;
}
DWORD SubThreadB(void * pD)
{
	int iID=(int)pD;
	
	for(int i=0;i<30;i++)
	{
	
		int iCopy=N;
		cout << i <<"--"<<" Thread " << iID <<" : " << iCopy << endl;
		N=0;
		Sleep(100);
	}
	return 0;
}
void main(void)
{   int pD;
    pD=0;
	SubThreadA((void *)pD);
    pD=1;
	SubThreadB((void *)pD);
	
		cout << "It is over " << endl;
		char ch;
		cin >> ch;
return ;
}

