#include<iostream>
using namespace std;
//定义链表节点类型
struct node
{
    int elem;
    node *next;
} *head,*tail; //定义链表类型

int main(void)
{
    int k;
    while(cin>>k)
    {
        if(k==0)
            break;
        // 建立一个新的节点
        node * pNode = new node;
        if (pNode == NULL) //判断分配是否成功
        {
            cout << "Memory insufficient!";
            break;
        }
        else
        {
            pNode->elem = k; //节点赋存放值
            pNode->next = NULL; //节点赋值为NULL
            //将上面建立的新节点加到链表中的过程为：
            if(head == NULL)//如果是第一个节点，链表为空
                head = tail = pNode; //第一个节点既是头也是尾。
            else
            {
                tail->next = pNode; //尾节点的指针指向新节点，即将新节点加到链表中
                tail = pNode; //新节点加入到链表尾后，让尾指针指向该节点。
            }
        }
    }
        node *bNode = head; //让一个指针pNode指向链表头
        cout<<"Link elements:";
        while (bNode!= NULL)
        {
            cout<<bNode->elem <<ends;// 输出节点存储的数据，也可以是访问pNode 所指节点
            //内容的其他语句
            bNode = bNode->next;//让bNode 指向下一个节点。
        }
}
/*
[思考问题]
在Node节点中添加*foward指针,记录上一个节点的值。
*/