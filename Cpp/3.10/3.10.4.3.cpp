///////////////////////手机通讯录系统////////////////////////////////////////
#include <windows.h> //调用Sleep延长时间函数库文件
#include <ctime>	 //调用系统当前日期时间
#include <fstream>
#include <string>
#include <cstdlib> //清屏库文件
#define max 1000   //通讯录最大容量1000人
#include <iostream>
using namespace std;
class addresslist //定义addresslist类
{
private:
	char name[20];		  //定义私有变量：姓名
	char phone[20];		  //定义私有变量：电话
	char homephone[20];   //定义私有变量：家庭电话
	char officephone[20]; //定义私有变量：办公电话
	char email[20];		  //定义私有变量：Email
	char group[20];		  //定义私有变量：分组
public:
	addresslist();			//声明构造函数
	virtual ~addresslist(); //声明析构函数
	void welcome_display(); //声明欢迎界面函数
	void menu_display();	//声明主菜单显示函数
	void add();				//声明增加信息函数
	void skim();			//声明浏览信息函数
	void dial();			//声明拨打电话信息函数
	void modify();			//声明修改信息函数
	void deletepeople();	//声明删除信息函数
	void search();			//声明查询信息函数
	void help();			//声明帮助信息函数
};
/////////////////////////////函数等的定义开始////////////////////////////////
addresslist people[max];
int count = 0; //初始化当前通讯录的信息数

addresslist::addresslist() //默认全为0
{
	strcpy(people[0].name, "0");
	strcpy(people[0].phone, "0");
	strcpy(people[0].homephone, "0");
	strcpy(people[0].officephone, "0");
	strcpy(people[0].email, "0");
	strcpy(people[0].group, "0");
}
addresslist::~addresslist() //析构函数
{
}
void addresslist::welcome_display() //欢迎界面函数的定义
{
	cout << "\n";
	cout << "          ********************欢迎界面********************        \n\n";
	cout << "*******************欢迎进入手机通讯录管理系统*********************\n\n";
	cout << "          *******   请按任意键进入主菜单……… ***********        \n\n";
	cout << "******************************************************************\n   ";
	while (getchar() == 0)
		;
	system("cls");
}
void addresslist::menu_display() //主菜单界面函数的定义
{
	time_t t;
	time(&t); //当前登录系统时间显示
	cout << "                              当前登陆系统时间是： " << ctime(&t) << endl;
	cout << "         ************************************************\n";
	cout << "*******************手机通讯录管理系统主菜单***********************\n";
	cout << "          *******    >> 1.添加新通讯信息<<        ********        \n";
	cout << "          *******       2.查看通讯录信息          ********        \n";
	cout << "          *******       3.拨号                    ********        \n";
	cout << "          *******       4.修改通讯录信息          ********        \n";
	cout << "          *******       5.删除通讯录信息         ********        \n";
	cout << "          *******       6.查找通讯录信息          ********        \n";
	cout << "          *******       7.帮助                    ********        \n";
	cout << "          *******       0.退出                    ********        \n";
	cout << "          *******请输入数字：(0~8)进行选择操作   ********        \n";
	cout << "******************************************************************\n";
}
void addresslist::add() //添加信息函数的定义，其中添加函数最为复杂，需要全面考虑
{
	system("cls");									 //清屏
	ofstream outfile("addresslist.txt", ios::binary); //本程序均采用二进制存档，文件为addresslist.txt
	if (!outfile)
	{
		cerr << "打开错误！" << endl;
		abort();
	}
	ifstream infile("addresslist.txt", ios::binary);
	if (!infile)
	{
		cerr << "打开错误！" << endl;
		abort();
	}
	for (int i = 0; i < count; i++)
	{
		infile.read((char *)&people[i], sizeof(people[i]));
		infile.read((char *)&count, sizeof(count));
	}
	int i = count; //从文件中读取count，并赋给i，以便存档
	cout << "\n************************新信息开始录入*********************\n请依次按下面顺序输入新通讯录的信息";
	cout << "（注:手机号，家庭电话，办公电话不能同时为空\n";
	cout << "且不填信息时以'0'结束进行下一个信息输入(以空格隔开信息，分组在之后操作)\n";
	cout << "姓名 \t手机号  \t家庭电话\t办公电话\t电子邮件\n";
	cin >> people[i].name >> people[i].phone;
	cin >> people[i].homephone >> people[i].officephone;
	cin >> people[i].email;
	int c,l,g,k,m,j;
	if (i == 0) //如果当前通讯录时空的话，则i=count==0,就直接存档;
	{
		cout << "现在开始进行分组\n(注：在分组中有1.未知 2.同事3.亲戚 4.朋友5.家人 6.同学7.默认 8.自己创建分组)\n";
		cout << "请输入你的选项(1~8)进行分组\n"; //实现客户对分组的选择输入
		cin >> c;
		switch (c) //分组选择在这里目的是：如果客户输入重复数据时，就不用对数据进行分组了
		{
		case1:
			strcpy(people[i].group, "未知");
			break;
		case2:
			strcpy(people[i].group, "同事");
			break;
		case3:
			strcpy(people[i].group, "亲戚");
			break;
		case4:
			strcpy(people[i].group, "朋友");
			break;
		case5:
			strcpy(people[i].group, "家人");
			break;
		case6:
			strcpy(people[i].group, "同学");
			break;
		case7:
			strcpy(people[i].group, "未知");
			break;
		case 8:
			cout << "请输入你要新建分组的名字：\n";
			cin >> people[i].group;
			break;
		default:
			cout << "你输入的操作选项错误！\n";
		}
		count++; //当存完档后count+1；
		for (j = 0; j < count; j++)
		{
			outfile.write((char *)&people[j], sizeof(people[j]));
			outfile.write((char *)&count, sizeof(count));
		}
		outfile.close();
	}
	else
	{
		for (g = 0; g < i; g++) //当当前通讯录人数不为零时，判断用户输入的姓名是否重复
		{
			if (!(strcmp(people[g].name, people[i].name))) //判断名字是否相等
			{
				cout << "你存储的姓名已存在！此项信息录入取消\n";
				for (j = 0; j < count; j++)
				{
					outfile.write((char *)&people[j], sizeof(people[j]));
					outfile.write((char *)&count, sizeof(count));
				}
				outfile.close();
				break;
			}
		}
		for (k = 0; k < i; k++) //判断用户输入的手机号是否重复
		{
			if (!(strcmp(people[k].phone, people[i].phone)))
			{
				cout << "你存储的手机号已存在！此项信息录入取消\n";
				for (j = 0; j < count; j++)
				{
					outfile.write((char *)&people[j], sizeof(people[j]));
					outfile.write((char *)&count, sizeof(count));
				}
				outfile.close();
				break;
			}
		}
		for (l = 0; l < i; l++) //判断用户输入的家庭电话是否重复
		{
			if (!(strcmp(people[l].homephone, people[i].homephone)))
			{
				cout << "你存储的家庭电话已存在！此项信息录入取消\n";
				for (j = 0; j < count; j++)
				{
					outfile.write((char *)&people[j], sizeof(people[j]));
					outfile.write((char *)&count, sizeof(count));
				}
				outfile.close();
				break;
			}
		}
		for (m = 0; m < i; m++) //判断用户输入的办公电话是否重复
		{
			if (!(strcmp(people[m].officephone, people[i].officephone)))
			{
				cout << "你存储的办公电话已存在！此项信息录入取消\n";
				for (j = 0; j < count; j++)
				{
					outfile.write((char *)&people[j], sizeof(people[j]));
					outfile.write((char *)&count, sizeof(count));
				}
				outfile.close();
				break;
			}
		}
		if (g == i && k == i && l == i && m == i) //当客户输入的姓名，手机号，家庭电话，办公电话不重复时才可以
		//把数据输入文件
		{
			cout << "现在开始进行分组\n(注：在分组中有1.未知 2.同事3.亲戚 4.朋友5.家人 6.同学7.默认 8.自己创建分组)\n";
			cout << "请输入你的选项(1~8)进行分组\n"; //实现客户对分组的选择输入
			int c;
			cin >> c;
			switch (c) //分组选择在这里目的是：如果客户输入重复数据时，就不用对数据分组了，同上
			{
			case 1:
				strcpy(people[i].group, "未知");
				break;
			case 2:
				strcpy(people[i].group, "同事");
				break;
			case 3:
				strcpy(people[i].group, "亲戚");
				break;
			case 4:
				strcpy(people[i].group, "朋友");
				break;
			case 5:
				strcpy(people[i].group, "家人");
				break;
			case 6:
				strcpy(people[i].group, "同学");
				break;
			case 7:
				strcpy(people[i].group, "未知");
				break;
			case 8:
				cout << "请输入你要新建分组的名字：\n";
				cin >> people[i].group;
				break;
			default:
				cout << "你输入的操作选项错误！\n";
			}
			count++; //当存完档后count+1；
			for (j = 0; j < count; j++)
			{
				outfile.write((char *)&people[j], sizeof(people[j]));
				outfile.write((char *)&count, sizeof(count));
			}
			cout << "数据保存成功！";
			outfile.close(); //关闭文件
		}
	}
	infile.close(); //关闭文件
} //添加函数结束
void addresslist::skim() //浏览函数的定义
{
	ifstream infile("addresslist.txt", ios::binary);
	if (!infile)
	{
		cerr << "打开错误！" << endl;
		abort();
	}
	for (int i = 0; i <= count; i++)
	{
		infile.read((char *)&people[i], sizeof(people[i]));
		infile.read((char *)&count, sizeof(count));
	}
	system("cls");
	cout << "***********************通讯录所有人的信息(姓名与电话)***********************\n";
	cout << "\t姓名 \t\t手机号\t\t\t家庭电话 \t\t办公电话\n";
	for (int i = 0; i < count; i++)
	{
		cout << "\t" << people[i].name << "\t\t" << people[i].phone << "\t\t" << people[i].homephone << "\t\t" << people[i].officephone << "\n";
	}
	cout << "****************************************************************************\n";
	infile.close();
}
void addresslist::dial() //模拟打电话函数的定义
{
	char a[20]; //定义临时变量
	ifstream infile("addresslist.txt", ios::binary);
	int i;
	if (!infile)
	{
		cerr << "打开错误！" << endl;
		abort();
	}
	for (i = 0; i <= count; i++)
	{
		infile.read((char *)&people[i], sizeof(people[i]));
		infile.read((char *)&count, sizeof(count));
	}
	system("cls");
	cout << "\n******************通讯录所有人的姓名信息(5人一行罗列)**********************\n";
	for (i = 0; i < count; i++)
	{
		cout << "\t" << people[i].name;
		if ((i + 1) % 5 == 0 || i == count - 1) //5个人一行进行罗列
			cout << "\n";
	}
	cout << "**************************************************************\n";
	cout << "请选择输入上面通讯录中的一个姓名来拨打电话：\n";
	cin >> a; //客户输入要拨打电话的姓名
	for (i = 0; i <= count; i++)
	{
		if (!(strcmp(a, people[i].name))) //判断客户输入的姓名是否在通讯录中
		{
			cout << "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n";
			cout << "\t你正在向" << people[i].name << "拨打电话(默认为手机号)\n     \t\t";
			for (int j = 0; j < strlen(people[i].phone); j++)
			{
				Sleep(500); //延长500毫秒即0.5秒的时间
				cout << people[i].phone[j];
			}
			cout << "\n\t电话正在拨通中请稍候………\n";
			cout << "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n";
		}
	}
	infile.close();
}
void addresslist::modify() //修改函数的定义
{
	char a[20]; //定义临时变量，同上
	ifstream infile("addresslist.txt", ios::binary);
	if (!infile)
	{
		cerr << "打开错误！" << endl;
		abort();
	}
	for (int i = 0; i <= count; i++)
	{
		infile.read((char *)&people[i], sizeof(people[i]));
		infile.read((char *)&count, sizeof(count));
	}
	ofstream outfile("addresslist.txt", ios::binary);
	if (!outfile)
	{
		cerr << "打开错误！" << endl;
		abort();
	}
	system("cls");
	cout << "        ****************通讯录修改信息*******************     \n";
	cout << "******************通讯录中所有人的姓名信息**********************\n";
	int i;
	for (i = 0; i < count; i++)
	{
		cout << "\t" << people[i].name;
		if ((i + 1) % 5 == 0 || i == count - 1) //5人信息一行进行罗烈
			cout << "\n";
	}
	cout << "**************************************************************\n";
	cout << "请选择输入上面通讯录中的一个姓名来修改信息：\n";
	cin >> a; //输入上面通讯录中的一个姓名来修改信息
	system("cls");
	for (i = 0; i < count; i++)
	{
		if (!(strcmp(a, people[i].name))) //判断输入的姓名是否在通讯录中
		{
			cout << "   *************" << people[i].name << "的所有信息如下**************\n";
			cout << "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n";
			cout << "\t手机号\t家庭电话\t办公电话\t电子邮件\t分组\n";
			cout << "\t" << people[i].phone << "\t" << people[i].homephone << "\t" << people[i].officephone;
			cout << "\t" << people[i].email << "\t" << people[i].group << endl;
			cout << "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n";
			cout << "1.姓名 2.手机号3.家庭电话 4.办公电话5.电子邮件 6.分组\n";
			cout << "请输入1~6进行数据选择性修改\n";
			int b;
			cin >> b;
			char c[20];
			switch (b) //switch函数进行客户选择修改的内容
			{
			case 1:
				cout << "请输入新的姓名：";
				cin >> c;
				strcpy(people[i].name, c);
				break;
			case 2:
				cout << "请输入新的手机号：";
				cin >> c;
				strcpy(people[i].phone, c);
				break;
			case 3:
				cout << "请输入新的家庭电话：";
				cin >> c;
				strcpy(people[i].homephone, c);
				break;
			case 4:
				cout << "请输入新的办公电话：";
				cin >> c;
				strcpy(people[i].officephone, c);
				break;
			case 5:
				cout << "请输入新的电子邮件：";
				cin >> c;
				strcpy(people[i].email, c);
				break;
			case 6:
				cout << "请输入新的分组：";
				cin >> c;
				strcpy(people[i].group, c);
				break;
			default:
				cout << "你输入的操作选项错误！\n";
			}
		}
	}
	for (int j = 0; j < count; j++)
	{
		outfile.write((char *)&people[j], sizeof(people[j]));
		outfile.write((char *)&count, sizeof(count));
	}
	cout << "数据修改保存成功！";
	outfile.close();
	infile.close();
}
void addresslist::deletepeople() //删除信息函数的定义
{

	char a[20]; //临时变量定义，同上
	ifstream infile("addresslist.txt", ios::binary);
	if (!infile)
	{
		cerr << "打开错误！" << endl;
		abort();
	}
	for (int i = 0; i <= count; i++)
	{
		infile.read((char *)&people[i], sizeof(people[i]));
		infile.read((char *)&count, sizeof(count));
	}
	ofstream outfile("addresslist.txt", ios::binary);
	if (!outfile)
	{
		cerr << "打开错误！" << endl;
		abort();
	}
	system("cls");
	cout << "            ****************通讯录删除信息*******************     \n";
	cout << "******************通讯录中所有人的姓名信息(五人一行罗列)****************\n";
	for (int i = 0; i < count; i++)
	{
		cout << "\t" << people[i].name;
		if ((i + 1) % 5 == 0 || i == count - 1) //五人一行进行罗列
			cout << "\n";
	}
	cout << "**************************************************************\n";
	cout << "请选择输入上面通讯录中的一个姓名进行删除信息：\n";
	cin >> a;
	system("cls");
	int j;
	for (int i = 0; i < count; i++)
	{
		if (!(strcmp(a, people[i].name))) //判断哪个符合进行删除
		{
			for (j = i + 1; j < count; j++)
			{
				strcpy(people[j - 1].name, people[j].name);
				strcpy(people[j - 1].phone, people[j].phone);
				strcpy(people[j - 1].homephone, people[j].homephone);
				strcpy(people[j - 1].officephone, people[j].officephone);
				strcpy(people[j - 1].email, people[j].email);
				strcpy(people[j - 1].group, people[j].group);
			}
			count--; //删除信息时，要把个数-1；进行存档
			break;
		}
	}
	for (int k = 0; k < count; k++)
	{
		outfile.write((char *)&people[k], sizeof(people[k]));
		outfile.write((char *)&count, sizeof(count));
	}
	cout << "\t\t数据删除成功！\n";
	outfile.close();
	infile.close();
}
void addresslist::search() //查找信息函数的定义
{
	char a[3];
	ifstream infile("addresslist.txt", ios::binary);
	if (!infile)
	{
		cerr << "打开错误！" << endl;
		abort();
	}
	for (int i = 0; i <= count; i++)
	{
		infile.read((char *)&people[i], sizeof(people[i]));
		infile.read((char *)&count, sizeof(count));
	}
	system("cls");
	cout << "********************查找通讯录信息*******************\n";
	cout << "\t 1.模糊查找    2.手机号码\n\t 请输入数字（1或2）进行查找信息：\n";
	int b;
	cin >> b;
	if (b == 1) //实现客户模糊查找（1.姓2.手机号第一位）
	{
		cout << "请输入你要查找信息的姓（1个汉字）：\n";
		cin >> a;
		cout << "*****************所有符合此（姓）的信息*******************\n";
		cout << "\t姓名\t手机号码\t家庭电话\t办公电话\t电子邮件\t分组\n";
		for (int i = 0; i < count; i++) //模糊查找相同的全部列出来
		{
			char c[3]; //定义一个临时字符数组，且初始化如下
			c[0] = people[i].name[0];
			c[1] = people[i].name[1];
			c[2] = '\0';			 //这样就可以模糊查找
			if (!(strcmp(&a[0], c))) //模糊查找中
			{
				cout << "\t" << people[i].name << "\t" << people[i].phone;
				cout << "\t" << people[i].homephone << "\t" << people[i].officephone;
				cout << "\t" << people[i].email << "\t" << people[i].group << endl;
			}
		}
		cout << "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n";
		cout << "所有符合的信息查找完毕！";
	}
	else //数字模糊查找，类似于上面
	{
		cout << "请输入你要模糊查找信息手机号码（1个数字）：\n";
		cin >> a;
		cout << "*****************所有符合手机号的信息*******************\n";
		cout << "\t姓名\t手机号码\t家庭电话\t办公电话\t电子邮件\t分组\n";
		for (int i = 0; i < count; i++)
		{
			char d[2];
			d[0] = people[i].phone[0];
			d[1] = '\0';
			if (!(strcmp(&a[0], d)))
			{
				cout << "\t" << people[i].name << "\t" << people[i].phone;
				cout << "\t" << people[i].homephone << "\t" << people[i].officephone;
				cout << "\t" << people[i].email << "\t" << people[i].group << endl;
			}
		}
		cout << "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n";
		cout << "所有符合的信息查找完毕！";
	}
	infile.close();
}
void addresslist::help() //帮助界面函数的定义
{
	system("cls");
	cout << "\n";
	cout << "          ********************帮助********************        \n";
	cout << "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n";
	cout << "%%                                                                         %%\n";
	cout << "%%                    								                   %%\n";
	cout << "%%            								                          %%\n";
	cout << "%%           help:                                                        %%\n";
	cout << "%%                在该简易系统中，客户可以实现对手机电话簿的管理操作      %%\n";
	cout << "%%               1.新信息的输入，及添加功能                               %%\n";
	cout << "%%               2.浏览查看功能（可以显示所有人的姓名，电话）             %%\n";
	cout << "%%               3.拨号功能（在这里采用了500毫秒的暂停以实现拨号界面）     %%\n";
	cout << "%%               4.在模糊查找信息时，客户需要注意只能输入一个汉字或一个数字%%\n";
	cout << "%%               5.在修改信息时，应注意可以返回再进行修改其他的信息       %%\n";
	cout << "%%               6.本程序均采用二进制存档,存档文件为addresslist.txt        %%\n";
	cout << "%%                  							                             %%\n";
	cout << "%%                                                                        %%\n";
	cout << "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n";
}
//////////////////////////////函数定义至此结束//////////////////////////////////////////////////

///////////////////////////主函数开始///////////////////////////////////
int main()
{
	addresslist addr;
	addr.welcome_display(); //欢迎界面
	char f;
	while (1) //构造假死循环
	{
		addr.menu_display(); //主菜单界面
		int ch;
		cin >> ch;
		switch (ch)
		{
		case 1:
			addr.add();
			break; //调用添加新信息函数
		case 2:
			addr.skim();
			break; //调用浏览函数
		case 3:
			addr.dial();
			break; //调用打电话函数
		case 4:
			addr.modify();
			break; //调用修改信息函数
		case 5:
			addr.deletepeople();
			break; //调用删除信息函数
		case 6:
			addr.search();
			break; //调用查找信息函数
		case 7:
			addr.help();
			break; //调用帮助函数
		case 0:
			cout << "已安全退出该系统！\n";
			break;
		default:
			cout << "没有此操作选项" << endl;
			break;
		}
		cout << "请问您是否需要返回主菜单中继续操作？（y:是;n:否）";
		cin >> f;
		system("cls"); //调用清屏函数，以便再次输入
		if (f == 'n' || f == 'N')
			break; //判断是否结束
	}
	return 0;
}
//////////////////////////////////////////////////////////主函数结束/////////////////////////////////////////
