#include <time.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
//旅客结构体
typedef struct customer
{
    char name[6]; //姓名
    int age;      //年龄
    int sex;      //性别
    int level;    //房间等级
    int bed_num;  //房间号
    char date[100];
    struct customer *next;
} customer;
//旅店等级结构体
typedef struct hotel_level
{
    int lv_num;               //等级数目
    struct hotel_level *next; //该结构体指针 指向下一个等级
    struct room *next_r;      //定义房间指针 指向等级划分下的房间链表指针
} hotel_lv;
typedef struct room //房间结构体
{
    int r_num;                 //房间号
    int max;                   //最大值
    int member_num;            //成员数
    struct room *next;         //定义房间指针 指向下一个房间（房间相指）
    struct customer *next_cus; //房间里的顾客指针 房间指向顾客
} room;
// 初始化函数
void Init(hotel_lv *L)
{
    int i, j, k, l;
    hotel_lv *p, *q;
    room *p1, *p2, *p3;
    printf("\n请输入房间分为几个等级 : ");
    scanf("%d", &j);
    //等级设置了头结点
    q = L;
    //创建等级 -房间 -床位数
    for (i = 1; i <= j; i++)
    {
        // 开辟等级结点
        p = (hotel_lv *)malloc(sizeof(hotel_lv));
        q->next = p;
        //给房间等级赋值
        p->lv_num = i;
        //输入该等级的房间数目
        printf("\n请输入 %d 等级的房间个数 : ", p->lv_num);
        scanf("%d", &l);
        if (l == 0)
            continue;
        else
        {
            p2 = (room *)malloc(sizeof(room));
            p2->r_num = 1;
            printf(" 请输入 %d 等级的房间号为 1 的房间床位个数的最大值 :", p->lv_num, k);
            //输入房间 1 的床位数目
            scanf("%d", &(p2->max));
            //该房间内当前人数赋为零
            p2->member_num = 0;
            p->next_r = p2;
            p3 = p2;
            for (k = 2; k <= l; k++)
            {
                p1 = (room *)malloc(sizeof(room));
                p3->next = p1;
                p1->r_num = k;
                printf(" 请输入 %d 等级的房间号为 %d 的房间床位个数的最大值 :", p->lv_num, k);
                //输入房间 k 的床位数目
                scanf("%d", &(p1->max));
                //该房间内当前人数赋为零
                p1->member_num = 0;
                p3 = p1;
            }
            //该等级房间床位设置结束，房间链表尾指针赋为空
            p3->next = NULL;
        }
        //等级指针指向下一个等级
        q = q->next;
    }
    //所有等级设置结束，等级链表尾指针域赋为空
    q->next = NULL;
}
//是否客满函数
int full(hotel_lv *L)
{
    hotel_lv *l;
    l = L->next;
    room *r;
    while (l != NULL)
    {
        r = l->next_r;
        while (r != NULL)
        {
            //如果有房间的当前人数不等于房间的最大人数，则返回 0
            if (r->member_num != r->max)
                return 0;
            else
                //否则指向下一个房间
                r = r->next;
        }
        //否则指向下一个等级
        l = l->next;
    }
    if (!l)
        return 1;
}
void press(customer *p, room *q)
{
    if (p->sex = 1)
    {
        printf("___________________________________________________________\n");
        printf("| 姓 名 | 性别 | 年龄 | 等级号 | 房间号 | 床位号 | 入 住 时 间| \n");
        printf("|----------------------------------------------------------|\n");
        printf("| %6s | 男 | %2d | %2d | %2d | %2d | %s", p->name, p->age, p->level, q->r_num, p->bed_num, p->date);
        printf("|__________________________________________________________|\n");
        printf("\n");
    }
    else if (p->sex = 0)
    {
        printf("___________________________________________________________\n");
        printf("| 姓 名 | 性别 | 年龄 | 等级号 | 房间号 | 床位号 | 入 住 时 间|\n");
        printf("|----------------------------------------------------------|\n");
        printf("| %6s | 女 | %2d | %2d | %2d | %2d | %s", p->name, p->age, p->level, q->r_num, p->bed_num, p->date);
        printf("|__________________________________________________________|\n");
        printf("\n");
    }
}
//旅客入住函数
void input(hotel_lv *L)
{
    time_t rawtime;
    struct tm *timeinfo;
    char c;
    customer *s, *p2, *p3;
    hotel_lv *l;
    l = L->next;
    room *p1;
    int i, lv;
    s = (customer *)malloc(sizeof(customer));
    printf("\n----------------------------------------\n");
    printf("\n 旅客信息 : ");
    printf("\n 请输入旅客姓名 :");
    scanf("%s", s->name);
    printf(" 请输入旅客年龄 :");
    scanf("%d", &(s->age));
    printf(" 请输入旅客性别 (男 1 女 0):");
    scanf("%d", &(s->sex));
    time(&rawtime);
    timeinfo = localtime(&rawtime);
    strcpy(s->date, asctime(timeinfo));
    if (full(L))
    {
        printf("\n 旅店客满 ，不能入住。 \n");
    }
    else
    {
        //输入旅客所需的等级
        printf(" 请输入旅客所需的房间等级 :");
        scanf("%d", &lv);
        //等级不为空时
        while (l != NULL)
        {
            if (l->lv_num != lv)
                l = l->next;
            //符合旅客需求的等级时
            else
            {
                p1 = l->next_r;
                //当房间不为空时
                while (p1 != NULL)
                {
                    //房间满时访问下一个房间
                    if (p1->member_num == p1->max)
                    {
                        p1 = p1->next;
                        if (p1 == NULL)
                        {
                            getchar();
                            printf(" 此等级为 %d 的所有房间均无空位 \n请更换等级！ [y/n]", lv);
                            //做出选择
                            scanf("%c", &c);
                            if (c == 'y')
                                input(L);
                            else
                                return;
                        }
                    }
                    //进行旅客登记
                    else
                    { //因为床位没有设置头结点， 所以对于第一个结点要特别判断
                        if (p1->member_num == 0)
                        {
                            s->level = lv;
                            p1->next_cus = s;
                            p1->member_num++; //房间当前人数加 1
                            s->bed_num = 1;   // 顾客床位号
                            s->next = NULL;   //尾指针置为空
                            press(s, p1);     //打印房间分配表
                            break;
                        }
                        else
                        {
                            p2 = p1->next_cus;
                            p3 = p2;
                            i = 1;
                            while (i <= p1->member_num)
                            {
                                if (i == p3->bed_num)
                                {
                                    p3 = p3->next;
                                    i++;
                                }
                                else
                                {
                                    i++;
                                    break;
                                }
                            }
                            // 如果 p3 是最后一个结点 （ NULL 的）时，输入旅客信息
                            if (p3 == NULL)
                            {
                                s->level = lv;
                                while (p2->next != p3)
                                    p2 = p2->next;
                                if (s->sex == p2->sex)
                                {
                                    p2->next = s;
                                    p1->member_num++;
                                    s->bed_num = i;
                                    s->next = NULL;
                                    press(s, p1);
                                    break;
                                }
                                else
                                    p1 = p1->next;
                            }
                            else
                            {
                                while (p2->bed_num == i)
                                    p2 = p2->next;
                                if (s->sex == p2->sex)
                                {
                                    p2->next = s;
                                    p1->member_num++;
                                    s->bed_num = i;
                                    s->next = NULL;
                                    press(s, p1);
                                    break;
                                }
                                else
                                    p1 = p1->next;
                            }
                        }
                    }
                }
                break;
            }
        }
    }
}
//旅客退房函数
customer *Delete(hotel_lv *L)
{
    int lv, hotel_num, bed_num;
    hotel_lv *l;
    l = L->next;
    room *r;
    customer *p, *q;
    char c;
    printf(" 请输入该退房人房间的等级号码： \n");
    //输入退房人的房间等级
    scanf("%d", &lv);
    getchar();
    while (l != NULL)
    {
        if (l->lv_num != lv)
            l = l->next;
        //找到该等级
        else
        {
            r = l->next_r;
            printf(" 请输入该退房人房间的房间号码： \n");
            //输入退房人的房间号码
            scanf("%d", &hotel_num);
            getchar();
            while (r != NULL)
            {
                if (r->r_num != hotel_num)
                    r = r->next;
                //找到该房间
                else
                {
                    p = r->next_cus;
                    q = r->next_cus;
                    printf(" 请输入该退房人的床位号码： \n");
                    //输入退房人的床位号码
                    scanf("%d", &bed_num);
                    getchar();
                    while (q != NULL)
                    {
                        if (q->bed_num != bed_num)
                            q = q->next;
                        //查找该床位
                        else
                        {
                            //因为床位没有设置头结点，所以对于第一个结点要特别判断
                            if (bed_num == 1)
                            { //直接打印旅客信息
                                printf("\n");
                                printf(" 此等级、此房间、此床位旅客的信息为：\n");
                                printf(" 该名顾客的信息 :\n");
                                printf(" 姓名\t 性别\t 年龄 \t 入住时间 \n");
                                printf("%s\t%d\t%d\t%s\n", q->name, q->sex, q->age, q->date);
                                printf(" 确认是否与要退房人信息相符？[y/n]\n");
                                scanf("%c", &c);
                                //做出是否退房的选择
                                if (c == 'y')
                                {
                                    r->member_num--;
                                    printf(" 退房成功！ \n");
                                    return (q);
                                }
                                else
                                {
                                    printf(" 退房失败！ \n");
                                    return NULL;
                                }      
                            }
                            //如果床位不为 1
                            else
                            {
                                //指向下一床位通过循环找到该床位
                                while (p->next != q)
                                    //打印旅客信息
                                    p = p->next;
                                printf("\n");
                                printf(" 此等级、此房间、此床位旅客的信息为：\n");
                                printf(" 该名顾客的信息 :\n");
                                printf(" 姓名\t 性别\t 年龄 \t 入住时间 \n");
                                printf("%s\t%d\t%d\t%s\n", q->name, q->sex, q->age, q->date);
                                printf(" 确认是否与要退房人信息相符？[y/n]\n");
                                scanf("%c", &c);
                                //做出是否退房的选择
                                if (c == 'y')
                                {
                                    p->next = q->next;
                                    r->member_num--;
                                    printf("\n");
                                    printf(" 退房成功！ \n");
                                    return (q);
                                }
                                else
                                {
                                    printf("\n");
                                    printf(" 退房失败！ \n");
                                    return NULL;
                                }
                            }
                        }
                    }
                    if (q == NULL) //如果该房间内床位为空
                        printf("\n 未找到该床位号，请核实信息后重新输入。 \n");
                }
            }
            if (r == NULL) // 如果该等级的房间为空
                printf("\n 未找到该房间号，请核实信息后重新输入。 \n");
        }
    }
    if (l == NULL) // 如果等级链表为空
        printf("\n 未找到该房间等级，请核实信息后重新输入。 \n");
}
//查询旅客信息函数
void cx_customer(hotel_lv *L)
{
    char s[15];
    hotel_lv *l;
    room *r;
    customer *t;
    int flag = 0; //设置标志量
    printf("\n 请输入要查询的旅客姓名： \n");
    scanf("%s", s); //输入要查找旅客的姓名
    getchar();
    l = L->next;
    while (l != NULL) //等级不为空时，走向房间
    {
        r = l->next_r;
        while (r != NULL) //房间不为空时，走向床位
        {
            if (r->member_num == 0) //若此房间当前人数为零，走向下一房间
                r = r->next;
            else
            {
                t = r->next_cus;
                while (t != NULL)
                {
                    if (!strcmp(t->name, s)) //如果找到该旅客
                    {
                        press(t, r); //打印信息
                        t = t->next; //指向下一人，继续查找
                        flag++;      //标志量加 1
                    }
                    else
                        t = t->next;
                }
                r = r->next;
            }
        }
        l = l->next;
    }
    if (!flag) //标志量为零， 则未查找到
        printf("\n 未找到该旅客，请核实后再输入。 \n");
}
//统计函数与查询旅客信息函数基本类似
void Tongji_cus(hotel_lv *L) //统计旅店当前住宿人数函数
{
    hotel_lv *l;
    room *r;
    customer *t;
    int i = 0, j = 0;
    int flag;
    l = L->next;
    while (l != NULL)
    {
        flag = 0;
        i++;
        r = l->next_r;
        while (r != NULL)
        {
            if (r->member_num == 0)
                r = r->next;
            else
            {
                t = r->next_cus;
                while (t != NULL)
                {
                    flag++;
                    j = j + flag;
                    t = t->next;
                }
                r = r->next;
            }
        }
        printf("\n 等级为 %d 的房间入住人数为 %d\n", i, flag);
        l = l->next;
    }
    if (!j)
        printf("\n 此时无人住宿。 \n");
}
int main()
{
    hotel_lv *L;
    customer *p;
    int c;
    L = (hotel_lv *)malloc(sizeof(hotel_lv));
    printf("\n 初始化旅店信息： \n");
    Init(L);
    while (1)
    { // 进入菜单选项
        printf("\n");
        printf(" 1. 录入旅客信息 \n");
        printf(" 2. 旅客退房 \n");
        printf(" 3. 查询旅客信息 \n");
        printf(" 4. 统计旅客信息 \n");
        printf(" 5. 退出本程序 \n");
        printf(" 请选择 1-5： \n");
        scanf("%d", &c);
        //做选择
        getchar();
        switch (c)
        {
        case 1:
            input(L);
            break;
        case 2:
            p = Delete(L);
            free(p);
            break;
        case 3:
            cx_customer(L);
            break;
        case 4:
            Tongji_cus(L);
            break;
        case 5:
            return 0;
        }
    }
    system("pause");
}