#include <iostream>
using namespace std;
int a[15];
//冒泡排序
void bubble_sort(int a[], int n)
{
    int i, j, temp;
    for (j = 0; j < n - 1; j++)
        for (i = 0; i < n - 1 - j; i++)
        {
            if(a[i] > a[i + 1])
            {
                temp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = temp;
            }
        }
}
//选择排序
void select_sort(int a[],int n)
{
    int i,j,min,t;
    for(i=0;i<n-1;i++)
    {
        min=i;//查找最小值
        for(j=i+1;j<n;j++)
            if(a[min]>a[j])
                min=j;//交换
        if(min!=i)
        {
            t=a[min];
            a[min]=a[i];
            a[i]=t;
        }
    }
}
int binsearch(int x,int a[],int n) 
{ 
	int low = 0; //最小数组元素下标 
	int high = n; //最大数组元素下标 
	int mid; //中间数组元素下标
	while (low <= high)
	{
		mid = (low + high) / 2;

		if (x < a[mid])
			high = mid - 1;
		else if (x >a[mid])
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
	{
		cin>>a[i];
		if(a[i]>100||a[i]<0)
		{
			cout<<"输入错误！"<<endl;
			i-=1;
		}
	}
	bubble_sort(a,15);
	cout<<"输入要查找的成绩:";
	cin>>find;
	int result=binsearch(find,a,15);
	if(result==-1)
		cout<<"无此成绩";
	else
		cout<<result<<endl;
}
/*
[思考与扩展]
1、选择排序法效率较高，冒泡排序法效率最低，若数组中的大部分元素已排好序，此时冒泡排序法效率反而会比较高。
2、折半查找法
	非递归函数，执行速度快。
3、因为数组名为地址，所以形参直接对实参值进行操作
*/