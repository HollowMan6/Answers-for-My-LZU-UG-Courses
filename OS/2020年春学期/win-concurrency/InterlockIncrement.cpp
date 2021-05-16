#include "thread.h"
#include "stdafx.h"
#include <stdio.h> 
#include <windows.h> 
#include <iostream.h>
#include <winbase.h> 
#define THREAD_INSTANCE_NUMBER	3

//全局变量
LONG lCounter=0;

DWORD SubThread(void * pD)
{
	int iTemp=(int)pD;
		cout << "SubThread"  << iTemp << endl;
	for(long i=0;i<6;i++)
	{
		InterlockedIncrement(&lCounter);
		long lCopy=lCounter;
		Sleep(100);
		cout << "thread " << iTemp <<":" << lCopy << endl;
	}
	return 0;
}

void main(void)
{
    cout << "CreateThread" << endl;

	// Create a thread;
	DWORD IDThread[THREAD_INSTANCE_NUMBER]; 
   	HANDLE hThread[THREAD_INSTANCE_NUMBER];
	int i;

	//创建线程
	for (i=0;i<THREAD_INSTANCE_NUMBER;i++)
	{
		hThread[i] = CreateThread(NULL, // no security attributes 
	         0,                           // use default stack size 
	         (LPTHREAD_START_ROUTINE) SubThread, // thread function 
	         (void *)i,                    // no thread function argument 
	         0,                       // use default creation flags 
	         &(IDThread[i]));              // returns thread identifier 
		
		// Check the return value for success. 
		if (hThread[i] == NULL)
			cout << "CreateThread error" << i << endl;
			else
			cout << "CreateThread: " << i << endl;
	
}
		//等待线程结束
		WaitForMultipleObjects(THREAD_INSTANCE_NUMBER,hThread,TRUE,INFINITE);
		printf("\nover\n");

return ;
}

