#include<iostream>
using namespace std;

int main()
{
	int iv1, iv2;
	char cv1, cv2;
	//输出 int 和 char占用字节的大小 
	cout<<" iv1 = "<< iv1 << " iv2 = " << iv2 <<endl;
	cout<<" size of iv1: " << sizeof(iv1) <<endl;
	cout<<" size of cv1: " << sizeof(cv1) <<endl;
	//输入iv1 iv2的值 
	cout<<"please enter two number: ";
	cin >> iv1 >> iv2;
	
	cv1 = ++iv1;
	cv2 = iv2++;
	
	cout << "cv1 = "<<cv1<<", cv2 = "<<cv2<<endl;
	
	cv2 =125;
	cout<<cv2<<endl;
	return 0; 
}
//2.不会 
/*[思考与拓展]
1.	iv1 = 36 iv2 = 0
	未对变量初始化，输出为内存中随机值。 
2.	8
3.	输出为数字对应ASCII码，对应测试数据，结果分别是：
	cv1 = Q, cv2 = P
	} 
	cv1 = b, cv2 = a
	} 
	cv1 = g, cv2 = f 
	}
	对于第三次数据，因为870远超127，发生了溢出，对应（870%128）的值 
*/
