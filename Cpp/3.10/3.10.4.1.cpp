#include <iostream>
using namespace std;
enum Error_code
{
	underflow,
	overflow,
	success
};
const int maxstack = 10; // 小值用于测试
template <class Stack_entry>
class MyStack
{
public:
	MyStack();
	bool empty() const;
	Error_code pop();
	Error_code top(Stack_entry &item) const;
	Error_code push(const Stack_entry &item);

private:
	int count;
	Stack_entry entry[maxstack];
};
template <class Stack_entry>
Error_code MyStack<Stack_entry>::push(const Stack_entry &item)
//如果堆栈未满，则将项添加到顶部堆栈的如果堆栈已满，返回溢出的错误代码，堆栈保持不变。
{
	Error_code outcome = success;
	if (count >= maxstack)
		outcome = overflow;
	else
		entry[count++] = item;
	return outcome;
}
template <class Stack_entry>
Error_code MyStack<Stack_entry>::pop()
//如果堆栈不是空的，则堆栈被删除。如果堆栈为空，返回下溢错误代码。
{
	Error_code outcome = success;
	if (count == 0)
		outcome = underflow;
	else
		--count;
	return outcome;
}
template <class Stack_entry>
Error_code MyStack<Stack_entry>::top(Stack_entry &item) const
//如果堆栈不是空的，则堆栈在项中返回。如果堆栈为空，返回下溢错误代码。
{
	Error_code outcome = success;
	if (count == 0)
		outcome = underflow;
	else
		item = entry[count - 1];
	return outcome;
}
template <class Stack_entry>
bool MyStack<Stack_entry>::empty() const // 不能修改类的数据，不能在没有常量的情况下调用fuc
// 如果堆栈为空，则返回true，否则返回false。
{
	bool outcome = true;
	if (count > 0)
		outcome = false;
	return outcome;
}
template <class Stack_entry>
MyStack<Stack_entry>::MyStack()
// 堆栈初始化为空
{
	count = 0;
}
int main()
{
	MyStack<char> openings;
	char symbol;
	bool is_matched = true;
	cout << "括号匹配程序" << endl
		 << "请输入一行数学表达式:" << endl;
	while (is_matched && (symbol = cin.get()) != '\n')
	{
		if (symbol == '{' || symbol == '(' || symbol == '[')
			openings.push(symbol);
		if (symbol == '}' || symbol == ')' || symbol == ']')
		{
			if (openings.empty())
			{
				cout << 0 << endl;
				is_matched = false;
			}
			else
			{
				char match;
				openings.top(match);
				openings.pop();
				is_matched = (symbol == '}' && match == '{') || (symbol == ')' && match == '(') || (symbol == ']' && match == '[');
				if (!is_matched)
					cout << 0 << endl;
			}
		}
	}
	if (!openings.empty())
		cout << 0 << endl;
	else if (is_matched)
		cout << 1 << endl;
}