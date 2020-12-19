#include "thread.h"
#include "stdafx.h"
#include <stdio.h> 
#include <windows.h> 
#include <iostream.h>
#include <winbase.h> 
#define THREAD_INSTANCE_NUMBER	3

//全局变量
int iCounter=0;

DWORD SubThreadA(void * pD)
{
	int iID=(int)pD;
	
	//在内部重新打开
	HANDLE hCounterIn=OpenEvent(EVENT_ALL_ACCESS,FALSE,"sam sp 44");

	cout << "Thread " << iID << " begin." << endl;

	//设置成为有信号状态
	Sleep(1000);
	cout << "Thread " << iID << " set event." << endl;
	SetEvent(hCounterIn);
	Sleep(1000);
	cout << "Thread " << iID << " end." << endl;
	CloseHandle(hCounterIn);
	return 0;
}

DWORD SubThreadB(void* pD)
{//等待threadA结束后在继续执行
	int iID=(int)pD;
	//在内部重新打开
	HANDLE hCounterIn=OpenEvent(EVENT_ALL_ACCESS,FALSE,"sam sp 44");

	if(WAIT_TIMEOUT == WaitForSingleObject(hCounterIn,10*1000))
	{
		cout << "Thread " << iID << " wait time out." << endl;
	}
	else
	{
		cout << "Thread " << iID << " wait ok." << endl;
	}
	CloseHandle(hCounterIn);
	return 0;
}


void main(void)
{
	HANDLE hCounter=NULL;
	if( (hCounter=OpenEvent(EVENT_ALL_ACCESS,FALSE,"sam sp 44"))==NULL)
	{
		//如果没有其他进程创建这个事件，则重新创建，该事件为人工重置事件
		hCounter = CreateEvent(NULL,TRUE/*人工重置*/,FALSE,"sam sp 44");
	}

	DWORD IDThread[THREAD_INSTANCE_NUMBER]; 
   	HANDLE hThread[THREAD_INSTANCE_NUMBER];
	int i;

    cout << "Test of manual rest event." << endl;

	//创建线程
    cout << "CreateThread" << endl;
	for (i=0;i<THREAD_INSTANCE_NUMBER;i++)
	{
		if (i==0)
		{	// Create one SubThreadA
			hThread[i] = CreateThread(NULL, // no security attributes 
	    	     0,                           // use default stack size 
	        	 (LPTHREAD_START_ROUTINE) SubThreadA, // thread function 
	        	 (void *)i,                    // no thread function argument 
	        	 0,                       // use default creation flags 
	        	 &(IDThread[i]));              // returns thread identifier 
		
			// Check the return value for success. 
			if (hThread[i] == NULL)
				cout << "CreateThread error" << i << endl;
				else
				cout << "CreateThread: " << i << endl;
		}
		else
		{	// Create two SubThreadB
			hThread[i] = CreateThread(NULL, // no security attributes 
	    	     0,                           // use default stack size 
	        	 (LPTHREAD_START_ROUTINE) SubThreadB, // thread function 
	        	 (void *)i,                    // no thread function argument 
	        	 0,                       // use default creation flags 
	        	 &(IDThread[i]));              // returns thread identifier 
		
			// Check the return value for success. 
			if (hThread[i] == NULL)
				cout << "CreateThread error" << i << endl;
				else
				cout << "CreateThread: " << i << endl;
		}
		
	}
	//等待线程结束
	WaitForMultipleObjects(THREAD_INSTANCE_NUMBER,hThread,TRUE,INFINITE);

	//关闭句柄
	CloseHandle(hCounter);

    cout << endl << "Test of auto rest event." << endl;

	if( (hCounter=OpenEvent(EVENT_ALL_ACCESS,FALSE,"sam sp 44"))==NULL)
	{
		//如果没有其他进程创建这个事件，则重新创建，该事件为自动重置事件
		hCounter = CreateEvent(NULL,FALSE/*自动重置*/,FALSE,"sam sp 44");
	}

	//创建线程
    cout << "CreateThread" << endl;
	for (i=0;i<THREAD_INSTANCE_NUMBER;i++)
	{
		if (i==0)
		{	// Create one SubThreadA
			hThread[i] = CreateThread(NULL, // no security attributes 
	    	     0,                           // use default stack size 
	        	 (LPTHREAD_START_ROUTINE) SubThreadA, // thread function 
	        	 (void *)i,                    // no thread function argument 
	        	 0,                       // use default creation flags 
	        	 &(IDThread[i]));              // returns thread identifier 
		
			// Check the return value for success. 
			if (hThread[i] == NULL)
				cout << "CreateThread error" << i << endl;
				else
				cout << "CreateThread: " << i << endl;
		}
		else
		{	// Create two SubThreadB
			hThread[i] = CreateThread(NULL, // no security attributes 
	    	     0,                           // use default stack size 
	        	 (LPTHREAD_START_ROUTINE) SubThreadB, // thread function 
	        	 (void *)i,                    // no thread function argument 
	        	 0,                       // use default creation flags 
	        	 &(IDThread[i]));              // returns thread identifier 
		
			// Check the return value for success. 
			if (hThread[i] == NULL)
				cout << "CreateThread error" << i << endl;
				else
				cout << "CreateThread: " << i << endl;
		}
		
	}

	//等待线程结束
	WaitForMultipleObjects(THREAD_INSTANCE_NUMBER,hThread,TRUE,INFINITE);
	//关闭句柄
	CloseHandle(hCounter);

	cout << "over" << endl;
   
return ;
}


