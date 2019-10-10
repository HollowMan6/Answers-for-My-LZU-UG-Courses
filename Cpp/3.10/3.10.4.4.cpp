#include <iostream>
#include <string>
#include <fstream> //定义文件输入输出流
#define num 8
using namespace std;
//学生基本信息的结构体定义
typedef struct student
{
	string xuehao; // 学号
	string name;   //姓名
	string gender; // 性别
	string sushe;  //宿舍号
	string tel;	// 电话
} student;
//学生成绩信息的结构体定义
typedef struct grade
{
	string xuehao;	 // 学号
	string courseno;   //课程号
	string coursename; //课程名称
	int xuefen;		   //学分
	int pingshi;	   //平时成绩
	int shiyan;		   //实验成绩
	int juanmian;	  // 卷面成绩
	float zonghe;	  // 综合成绩
	float shide;	   // 实得学分
	grade *next;
} grade;
void luru(grade *gra);
void menu(student *stu, grade *head, grade *gra);
void lookfor(student *p, grade *head);
void sort(grade *head);
void out(grade *head);
void sort1(grade *head);
void del(student *stu, grade *head);
void show(student *stu);
void save1(student *stud);
void save2(grade *head);
void leave();
//数据录入
void luru(grade *gra)
{
	string str;
	cout << " 请输入学生的学号， " << endl;
	cin >> str;
	gra->xuehao = str;
	cout << " 请录入该生课程编号 " << endl;
	cin >> gra->courseno;
	cout << " 请录入该生课程名称 " << endl;
	cin >> gra->coursename;
	cout << " 请录入该生学分 " << endl;
	cin >> gra->xuefen;
	cout << " 请录入该生平时成绩 " << endl;
	cin >> gra->pingshi;
	cout << " 请录入该生实验成绩 " << endl;
	cin >> gra->shiyan;
	cout << " 请录入该生卷面成绩 " << endl;
	cin >> gra->juanmian;
	//综合成绩计算
	if (gra->shiyan == -1)
		gra->zonghe = gra->pingshi * 0.3 + gra->juanmian * 0.7;
	else
		gra->zonghe = gra->pingshi * 0.15 + gra->juanmian * 0.7 + gra->shiyan * 0.15;
	//实得学分的计算
	if (gra->zonghe >= 90 && gra->zonghe <= 100)
		gra->shide = gra->xuefen;
	else if (gra->zonghe >= 80)
		gra->shide = gra->xuefen * 0.8;
	else if (gra->zonghe >= 70)
		gra->shide = gra->xuefen * 0.75;
	else if (gra->zonghe >= 60)
		gra->shide = gra->xuefen * 0.60;
	else if (gra->zonghe < 60)
		gra->shide = 0;
	else
		cout << " 您输入有误 ,请按提示操作！ " << endl;
}
//查询功能
void lookfor(student *p, grade *head)
{
	char i, k;
	string str;
	int j, x = 0;
	float sum = 0;
	cout << "-------------------------------------------------" << endl;
	cout << "-- A: 学生基本情况查询 B:成绩查询 --" << endl;
	cout << "-------------------------------------------------" << endl;
	cin >> i;
	switch (i)
	{ // 学生基本情况查询
	case 'A':
		cout << "-------------------------------------------------" << endl;
		cout << "--1. 输入一个学号或姓名 2.输入一个宿舍号码 --" << endl;
		cout << "-------------------------------------------------" << endl;
		cin >> j;
		//A1---- 输入一个学号或姓名（可实现选择） ，查出此生的基本信息并显示输出。
		if (j == 1)
		{
			cout << "*****************************************************" << endl;
			cout << "************a 按学号查找 b 按姓名查找 ************" << endl;
			cout << "*****************************************************" << endl;
			cin >> k;
			if (k == 'a')
			{
				cout << " 请输入学号： " << endl;
				cin >> str;
				for (j = 1; j < 8; j++)
				{
					if (p[j].xuehao == str)
						cout << p[j].xuehao << " " << p[j].name << " " << p[j].gender << " " << p[j].sushe << " "<<p[j].tel;
							cout << endl;
				}
			}
			else if (k == 'b')
			{
				cout << " 请输入姓名： " << endl;
				cin >> str;
				for (j = 1; j < 8; j++)
				{
					if (p[j].name == str)
						cout << p[j].xuehao << " " << p[j].name << " " << p[j].gender << " " << p[j].sushe << " "<<p[j].tel;
							cout << endl;
				}
			}
		}
		//A2--- 输入一个宿舍号码，可查询出本室所有的学生的基本信息并显示输出。
		else if (j == 2)
		{
			cout << " 请输入宿舍号： " << endl;
			cin >> str;
			for (j = 1; j < 8; j++)
			{
				if (p[j].sushe == str)
					cout << p[j].xuehao << " " << p[j].name << " " << p[j].gender << " " << p[j].sushe << " "<<p[j].tel;
						cout << endl;
			}
		}
		else
			cout << " 您输入有误 ,请按提示操作！ " << endl;
		break;
	//成绩查询
	case 'B':
		cout << " 请输入要查询学生的学号： " << endl;
		cin >> str;
		for (j = 1; j < 8; j++)
		{
			if (p[j].xuehao == str)
				cout << " 学号： " << str << "\t"
					 << " 姓名： " << p[j].name << endl;
		}
		head = head->next; //指向首元结点
		while (head != NULL)
		{
			if (head->xuehao == str)
			{
				cout << " 课程编号： " << head->courseno << "\t"
					 << " 课程名称： " << head->coursename << "\t"
					 << " 综合成绩： " << head->zonghe << "\t"
					 << " 实得学分： " << head->shide << endl;
				x++;
				sum = sum + head->shide;
			}
			head = head->next; // 指针后指
		}
		cout << " 共修： " << x << " 科，实得总学分为： " << sum << endl;
		break;
	default:
		cout << " 您输入有误 ,请按提示操作！ " << endl;
	}
}
//删除功能
void del(student *stu, grade *head)
{
	string str;
	grade *p = head->next;
	grade *q;
	cout << " 请输入要删除学生的学号： " << endl;
	cin >> str;
	//在学生基本信息中删除
	for (int i = 1; stu[i].xuehao != "0"; i++)
	{
		if (stu[i].xuehao == str)
		{
			int j;
			for (j = i; stu[j].xuehao != "0"; j++)
			{
				stu[j].gender = stu[j + 1].gender;
				stu[j].name = stu[j + 1].name;
				stu[j].sushe = stu[j + 1].sushe;
				stu[j].tel = stu[j + 1].tel;
				stu[j].xuehao = stu[j + 1].xuehao;
			}
			stu[j].gender = "0";
			stu[j].name = "0";
			stu[j].sushe = "0";
			stu[j].tel = "0";
			stu[j].xuehao = "0";
		}
	}
	//在学生成绩基本信息中删除
	while (p)
	{
		if (p->xuehao == str)
		{
			head->next = p->next;
			q = p;
			p = p->next;
			delete q;
		}
		else
		{
			p = p->next;
			head = head->next;
		}
	}
	cout << " 删除成功！ " << endl;
}
	//排序功能
	//按综合成绩升序排序
	void
	sort(grade *head)
{
	grade *temp = new grade;
	grade *h = head;
	for (head = head->next; head->next; head = head->next)
	{
		for (grade *p = head->next; p; p = p->next)
		{
			if (head->zonghe > p->zonghe)
			{
				temp->xuehao = head->xuehao;
				head->xuehao = p->xuehao;
				p->xuehao = temp->xuehao;
				temp->courseno = head->courseno;
				head->courseno = p->courseno;
				p->courseno = temp->courseno;
				temp->coursename = head->coursename;
				head->coursename = p->coursename;
				p->coursename = temp->coursename;
				temp->xuefen = head->xuefen;
				head->xuefen = p->xuefen;
				p->xuefen = temp->xuefen;
				temp->pingshi = head->pingshi;
				head->pingshi = p->pingshi;
				p->pingshi = temp->pingshi;
				temp->shiyan = head->shiyan;
				head->shiyan = p->shiyan;
				p->shiyan = temp->shiyan;
				temp->juanmian = head->juanmian;
				head->juanmian = p->juanmian;
				p->juanmian = temp->juanmian;
				temp->zonghe = head->zonghe;
				head->zonghe = p->zonghe;
				p->zonghe = temp->zonghe;
				temp->shide = head->shide;
				head->shide = p->shide;
				p->shide = temp->shide; // 数据交换
			}
		}
	}
	cout << " 按综合成绩升序排序为： " << endl;
	out(h);
}
//按实得学分降序排序
void sort1(grade *head)
{
	grade *temp = new grade;
	grade *h = head;
	for (head = head->next; head->next; head = head->next)
	{
		for (grade *p = head->next; p; p = p->next)
		{
			if (head->shide < p->shide)
			{
				temp->xuehao = head->xuehao;
				head->xuehao = p->xuehao;
				p->xuehao = temp->xuehao;
				temp->courseno = head->courseno;
				head->courseno = p->courseno;
				p->courseno = temp->courseno;
				temp->coursename = head->coursename;
				head->coursename = p->coursename;
				p->coursename = temp->coursename;
				temp->xuefen = head->xuefen;
				head->xuefen = p->xuefen;
				p->xuefen = temp->xuefen;
				temp->pingshi = head->pingshi;
				head->pingshi = p->pingshi;
				p->pingshi = temp->pingshi;
				temp->shiyan = head->shiyan;
				head->shiyan = p->shiyan;
				p->shiyan = temp->shiyan;
				temp->juanmian = head->juanmian;
				head->juanmian = p->juanmian;
				p->juanmian = temp->juanmian;
				temp->zonghe = head->zonghe;
				head->zonghe = p->zonghe;
				p->zonghe = temp->zonghe;
				temp->shide = head->shide;
				head->shide = p->shide;
				p->shide = temp->shide;
			}
		}
	}
	cout << " 按实得学分降序排序为： " << endl;
	out(h);
}
//输出学生成绩信息
void out(grade *head)
{
	head = head->next;
	cout << " 学号 "
		 << " "
		 << " 课程编号 "
		 << " "
		 << " 课程名称 \t"
		 << " "
		 << " 学分 "
		 << " "
		 << " 平时成绩 "<<" "
		 << " 实验成绩 "
		 << " "
		 << " 卷面成绩 "
		 << " "
		 << " 综合成绩 "
		 << " "
		 << " 实得学分 " << endl;
	while (head)
	{
		cout << head->xuehao << "\t" << head->courseno << "\t" << head->coursename << "\t" << head->xuefen
			 << "\t" << head->pingshi << "\t" << head->shiyan << "\t" << head->juanmian << "\t" << head->zonghe << "\t "<<head->shide<<endl;
			head = head->next;
	}
}
//显示所有学生信息
void show(student *stud)
{
	for (int i = 1; stud[i].xuehao != "0"; i++)
	{
		cout << stud[i].xuehao << " " << stud[i].name << " " << stud[i].gender << " " << stud[i].sushe << " "<<stud[i].tel;
			cout << endl;
	}
}
//将学生信息保存到 A.txt
void save1(student *stud)
{
	ofstream outfile("A.txt", ios::out); //打开文件
	if (!outfile)
	{
		cerr << " 打开文件失败，程序中止 !" << endl;
		return ; //打开文件失败，终止程序
	}
	for (int i = 0; stud[i].xuehao != "0"; i++)
	{
		outfile << stud[i].xuehao << " " << stud[i].name << " " << stud[i].gender << " " << stud[i].sushe << " "<<stud[i].tel<<endl;
	}
	outfile.close(); //关闭文件
}
//将学生成绩信息保存到 B.txt
void save2(grade *head)
{
	ofstream outfile("B.txt", ios::out); //打开文件
	if (!outfile)
	{
		cerr << " 打开文件失败，程序中止 !" << endl;
		return ; //打开文件失败，终止程序
	}
	head = head->next;
	outfile << " 学号 "
			<< " "
			<< " 课程编号 "
			<< " "
			<< " 课程名称 \t"
			<< " "
			<< " 学分 "
			<< " "
			<< " 平时成绩 "<<" "
			<< " 实验成绩 "
			<< " "
			<< " 卷面成绩 "
			<< " "
			<< " 综合成绩 "
			<< " "
			<< " 实得学分 " << endl;
	while (head)
	{
		outfile << head->xuehao << "\t" << head->courseno << "\t" << head->coursename << "\t" << head->xuefen << "\t"
				<< head->pingshi << "\t" << head->shiyan << "\t" << head->juanmian << "\t" << head->zonghe << "\t" << head->shide << endl;
		head = head->next;
	}
	outfile.close(); //关闭文件
}
void leave()
{
	cout << " 谢谢使用！ " << endl;
	exit(0);
}
//菜单
void menu(student *stu, grade *head, grade *gra)
{
	int i;
	grade *p;
	cout << " 欢 迎 使 用 学 生 管 理 系 统 ， 请 按 提 示 操 作 ！"<<endl;
	cout << "************************************************************************* *******"<<endl;
	cout << "*1 数据录入功能 2 查询功能 3 删除功能 4 排序功能 5 显示所有学生信息 6保存 0 退出 *"<<endl;
	cout << "************************************************************************* *******"<<endl;
	cin >> i;
	switch (i)
	{
	case 1:
		p = new grade;
		p->next = NULL;
		gra->next = p;
		gra = p;
		luru(gra);
		system("pause");
		system("cls");
		menu(stu, head, gra);
		break;
	case 2:
		lookfor(stu, head);
		system("pause");
		system("cls");
		menu(stu, head, gra);
		break;
	case 3:
		del(stu, head);
		system("pause");
		system("cls");
		menu(stu, head, gra);
		break;
	case 4:
		cout << "-------------------------------------------------" << endl;
		cout << "--1. 按综合成绩升序 2.按实得学分降序 --" << endl;
		cout << "-------------------------------------------------" << endl;
		cin >> i;
		if (i == 1)
			sort(head);
		else if (i == 2)
			sort1(head);
		else
		{
			cout << " 您输入有误 ,请重新操作！ " << endl;
			system("pause");
			system("cls");
			menu(stu, head, gra);
		}
		system("pause");
		system("cls");
		menu(stu, head, gra);
		break;
	case 5:
		show(stu);
		system("pause");
		system("cls");
		menu(stu, head, gra);
		break;
	case 6:
		cout << "-------------------------------------------------" << endl;
		cout << "--1. 学生基本信息保存 2.学生成绩保存 --" << endl;
		cout << "-------------------------------------------------" << endl;
		cin >> i;
		if (i == 1)
			save1(stu);
		else if (i == 2)
			save2(head);
		else
		{
			cout << " 您输入有误 ,请重新操作！ " << endl;
			system("pause");
			system("cls");
			menu(stu, head, gra);
		}
		system("pause");
		system("cls");
		menu(stu, head, gra);
		break;
	case 0:
		leave();
	default:
		cout << " 您输入有误 ,请按提示操作！ " << endl;
		system("pause");
		system("cls");
		menu(stu, head, gra);
		break;
	}
}
int main()
{
	student stud[num];
	grade *gra1, *r;
	gra1 = new grade;
	gra1->next = NULL;
	r = gra1;
	for (int i = 0; i < num; i++)
	{
		stud[i].xuehao = "0";
		stud[i].name = "0";
		stud[i].gender = "0";
		stud[i].sushe = "0";
		stud[i].tel = "0";
	}
	ifstream infile("A.txt", ios::in);
	if (!infile)
	{
		cerr << " 不存在A.txt文件，程序中止 !" << endl;
		system("pause");
		exit(1); //打开文件失败，终止程序
	}
	for (int i = 0; i < num; i++)
	{
		infile >> stud[i].xuehao 
			   >> stud[i].name 
			   >> stud[i].gender 
			   >> stud[i].sushe 
			   >> stud[i].tel;
	}
	infile.close(); //关闭文件
	menu(stud, gra1, r);
	return 0;
}