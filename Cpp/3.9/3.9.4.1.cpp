#include <iostream>
#include <fstream>
using namespace std;
int main()
{
	ofstream oufile("file.txt", ios::binary);
	char ab[1000];
	cout<<"请输入要加密的字符,#结束："<<endl;
	cin.get(ab, 1000, '#');				//参数=>数组地址，地址长度，输入结束符号
	for (int i = 0; ab[i] != '\0'; i++) //加密
	{
		if (ab[i] <= 'Z' && ab[i] >= 'A')
			ab[i] = (ab[i] - 'A' + 4) % ('Z' - 'A') + 'A';
		else if (ab[i] <= 'z' && ab[i] >= 'a')
			ab[i] = (ab[i] - 'a' + 4) % ('z' - 'a') + 'a';
	}
	oufile << ab;
	oufile.close();
	ifstream xx("file.txt");
	char ch;
	while (xx >> noskipws >> ch) //解密
	{
		if (ch <= 'Z' && ch >= 'A')
			ch = (ch + 'Z' - 'A' - 4 - 'A') % ('Z' - 'A') + 'A';
		else if (ch <= 'z' && ch >= 'a')
			ch = (ch + 'z' - 'a' - 4 - 'a') % ('z' - 'a') + 'a';
		cout << ch;
	}
	return 0;
}