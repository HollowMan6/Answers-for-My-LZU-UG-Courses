#include <iostream>
using namespace std;
void swap(int &p1,int &p2);
int main()
{
	cout<<"------------------------------------"<<endl;
	int iv1 =888;
	int *pointer_1;
	//pointer_1指向iv1
	pointer_1 = &iv1;
	cout<<"iv1        = "<<iv1<<endl;
	//输出pointer_1的地址
	cout<<"*pointer_1 = "<<*pointer_1<<endl;
	//输出iv1的地址
	cout<<"&iv1       = "<<&iv1<<endl;
	//输出pointer_1的值（iv1的地址）
	cout<<"pointer_1  = "<<pointer_1<<endl;

	cout<<"------------------------------------"<<endl;
	int iv2 = 666;
	int *pointer_2;
	pointer_2 =&iv2;
	//同上
	cout<<"iv2        = "<<iv2<<endl;
	cout<<"*pointer_2 = "<<*pointer_2<<endl;
	cout<<"&iv2       = "<<&iv2<<endl;
	cout<<"pointer_2  = "<<pointer_2<<endl;

	cout<<"------------------------------------"<<endl;
	//交换pointer_1,pointer_2
	swap(pointer_1,pointer_2);
	cout<<"iv1        = "<<iv1<<endl;
	cout<<"iv2        = "<<iv2<<endl;
	cout<<"*pointer_1 = "<<*pointer_1<<endl;
	cout<<"*pointer_2 = "<<*pointer_2<<endl;
	cout<<"&iv1       = "<<&iv1<<endl;
	cout<<"&iv2       = "<<&iv2<<endl;
	cout<<"pointer_1  = "<<pointer_1<<endl;
	cout<<"pointer_2  = "<<pointer_2<<endl;
	system("pause");
	return 0;
}
void swap(int &p1,int &p2)
{
	int temp;
	temp = p1;
	p1 = p2;
	p2 = temp;
}

/*
要求：
1.
------------------------------------
iv1        = 888
*pointer_1 = 888
&iv1       = 0x61ff04
pointer_1  = 0x61ff04
------------------------------------
iv2        = 666
*pointer_2 = 666
&iv2       = 0x61ff00
pointer_2  = 0x61ff00
------------------------------------
iv1        = 666
iv2        = 888
*pointer_1 = 666
*pointer_2 = 888
&iv1       = 0x61ff04
&iv2       = 0x61ff00
pointer_1  = 0x61ff04
pointer_2  = 0x61ff00

2.
------------------------------------
iv1        = 888
*pointer_1 = 888
&iv1       = 0x61ff04
pointer_1  = 0x61ff04
------------------------------------
iv2        = 666
*pointer_2 = 666
&iv2       = 0x61ff00
pointer_2  = 0x61ff00
------------------------------------
iv1        = 888
iv2        = 666
*pointer_1 = 888
*pointer_2 = 666
&iv1       = 0x61ff04
&iv2       = 0x61ff00
pointer_1  = 0x61ff04
pointer_2  = 0x61ff00
此时交换的为指针的指向，并未改变iv1 iv2变量的值

3.
------------------------------------
iv1        = 888
*pointer_1 = 888
&iv1       = 0x61ff0c
pointer_1  = 0x61ff0c
------------------------------------
iv2        = 666
*pointer_2 = 666
&iv2       = 0x61ff04
pointer_2  = 0x61ff04
------------------------------------
iv1        = 888
iv2        = 666
*pointer_1 = 666
*pointer_2 = 888
&iv1       = 0x61ff0c
&iv2       = 0x61ff04
pointer_1  = 0x61ff04
pointer_2  = 0x61ff0c
因为此时引用的是指针变量，所以此时交换的为指针的指向，并未改变iv1 iv2变量的值
*/