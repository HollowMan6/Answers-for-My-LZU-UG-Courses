#include <fstream>
using namespace std;
int main(int argc, char *argv[])
{
	char *f1 = "new.txt";
	ofstream outFile(f1);
	while(argc-- > 1){
		ifstream inFile1(*++argv);
		outFile << inFile1.rdbuf();
		inFile1.close();
	}
	outFile.close();
}
/*
[思考与扩展]
每次使用参数指定类型
*/