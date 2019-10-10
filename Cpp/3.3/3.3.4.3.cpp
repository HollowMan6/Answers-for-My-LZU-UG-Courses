#include <iostream>
using namespace std;
int a[15];
//冒泡排序
void bubble_sort(int *a, int n)
{
    int i, j, temp;
    for (j = 0; j < n - 1; j++)
        for (i = 0; i < n - 1 - j; i++)
        {
            if(*(a+i) > *(a+i+1))
            {
                temp = *(a+i);
                *(a+i) = *(a+i+1);
                *(a+i+1) = temp;
            }
        }
}
//选择排序
int binsearch(int x,int *a,int n) 
{ 
	int low = 0; //最小数组元素下标 
	int high = n; //最大数组元素下标 
	int mid; //中间数组元素下标
	while (low <= high)
	{
		mid = (low + high) / 2;

		if (x < *(a+mid))
			high = mid - 1;
		else if (x >*(a+mid))
			low = mid + 1;
		else           //找到了匹配的值
			return mid;
	}
	return -1;        //没有找到匹配的值
}

int main()
{
	int find;
	for(int i=0;i<15;i++)
		cin>>a[i];
	bubble_sort(a,15);
	cout<<"输入要查找的数:";
	cin>>find;
	int result=binsearch(find,a,15);
	if(result==-1)
		cout<<"无此数";
	else
		cout<<result<<endl;
}
/*
[思考与扩展]
1、数组名即为数组首地址
2、指针函数是指带指针的函数，即本质是一个函数，函数返回类型是某一类型的指针。
函数指针是指向函数的指针变量，即本质是一个指针变量。
*/