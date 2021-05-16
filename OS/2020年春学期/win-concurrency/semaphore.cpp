//#include "thread.h"
//#include "stdafx.h"
#include "stdio.h" 
#include "windows.h" 
#include "iostream.h"
#include "winbase.h" 


#define THREAD_INSTANCE_NUMBER	3

//全局变量
int iCounter=0;

DWORD SubThread(void * pD)
{
	int iID=(int)pD;
	
	//在内部重新打开
	HANDLE hCounterIn=OpenSemaphore(SEMAPHORE_ALL_ACCESS,FALSE,"sam sp 44");

	for(int i=0;i<3;i++)
	{
		cout << "  Thread " << iID << " waits for object." << endl;
		WaitForSingleObject(hCounterIn,INFINITE);
		cout << "     Thread " << iID << " : do database access call." << endl;
		Sleep(10);
		cout << "            Thread " << iID << " : do database access call end." << endl;
		ReleaseSemaphore(hCounterIn,1,NULL);
	}
	CloseHandle(hCounterIn);
	return 0;

}

void main(void)
{
	//创建信号灯
	HANDLE hCounter=NULL;
	if( (hCounter=OpenSemaphore(SEMAPHORE_ALL_ACCESS,FALSE,"sam sp 44"))==NULL)
	{
		//如果没有其他进程创建这个信号灯，则重新创建
		hCounter = CreateSemaphore(NULL,2/*initial count*/,2/*maximum count*/,"sam sp 44");
	}

	// Create a thread;
    cout << "CreateThread" << endl;

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

		//关闭句柄
		CloseHandle(hCounter);

		printf("\nover\n");

return ;
}

