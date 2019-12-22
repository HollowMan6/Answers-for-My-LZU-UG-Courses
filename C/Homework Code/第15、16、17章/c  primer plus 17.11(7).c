#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>

typedef struct 
{
	char word[30];
	int count;
} Item;

typedef struct node {
	Item item;
	struct node * left;
	struct node * right;
} Node;

typedef Node * Tree;

Tree * InitializeTree(Tree * ptree) {
	*ptree = NULL;
	return ptree;
}

bool TreeIsEmpty(const Tree * ptree) {
	return (*ptree == NULL);
}

bool TreeIsFull(const Tree * ptree) {
	Node * pnode = (Node *) malloc(sizeof(Node));
	if (pnode == NULL) {
		return true;
	}
	else {
		free(pnode);
		return false;
	}
}

bool AddWord(const char * word, Tree * ptree) {
	if (TreeIsFull(ptree)) {
		return false;
	}

	int cmp;
	while (*ptree != NULL) {
		int cmp = strcmp(word, (*ptree)->item.word);
		if (cmp == 0) {
			(*ptree)->item.count++;
			return true;
		}
		else if (cmp < 0) {
			ptree = &((*ptree)->left);
		}
		else {
			ptree = &((*ptree)->right);
		}
	}

	Item item;
	strncpy(item.word, word, 30);
	item.count = 1;

	Node * pnode = (Node *) malloc(sizeof(Node));
	if (pnode == NULL) {
		fprintf(stderr, "内存不足！\n");
		return false;
	}

	pnode->item = item;
	pnode->left = NULL;
	pnode->right = NULL;

	*ptree = pnode;
	return true;
}

bool DeleteWord(const char * word, Tree * ptree) {
	while (*ptree != NULL) {
		int cmp = strcmp(word, (*ptree)->item.word);
		if (cmp == 0) {
			Node * right = (*ptree)->right;
			Node * tmp = (*ptree);
			*ptree = (*ptree)->left;
			while ((*ptree)->right != NULL) {
				ptree = &((*ptree)->right);
			}
			(*ptree)->right = right;

			free(tmp);
			return true;
		}
		else if (cmp < 0) {
			ptree = &((*ptree)->left);
		}
		else {
			ptree = &((*ptree)->right);
		}
	}
	return false;

}

bool InTree(const char * word, const Tree * ptree) {
	Node * pnode = *ptree;
	while (pnode != NULL) {
		int cmp = strcmp(word, pnode->item.word);
		if (cmp == 0) {
			return true;
		}
		else if (cmp < 0) {
			pnode = pnode->left;
		}
		else {
			pnode = pnode->right;
		}
	}
	return false;
}

void ApplyToNode(const char * word, const Tree * ptree, void (*fn)(Item)) {
	Node * pnode = *ptree;
	while (pnode != NULL) {
		int cmp = strcmp(word, pnode->item.word);
		if (cmp == 0) {
			(*fn)(pnode->item);
			return;
		}
		else if (cmp < 0) {
			pnode = pnode->left;
		}
		else {
			pnode = pnode->right;
		}
	}
	return;
}

void ApplyToAll(const Tree * ptree, void (*fn)(Item)) {
	Node * pnode = *ptree;
	if (pnode == NULL) {
		return;
	}
	ApplyToAll(&(pnode->left), fn);
	(*fn)(pnode->item);
	ApplyToAll(&(pnode->right), fn);
}

void DeleteAll(Tree * ptree) {
	Node * pnode = *ptree;
	DeleteAll(&(pnode->left));
	DeleteAll(&(pnode->right));
	free(pnode);
}

void printMenu(void) {
	puts("           选项:\n");
	puts("a.列出所有的单词及出现的次数");
	puts("b.寻找一个单词在文中出现的次数");
	puts("q.退出\n");
	printf("请选择: ");
}

int getOption(void) {
	printMenu();
	int opt;
	while ((opt = getchar()) != 'a' && opt != 'b' && opt != 'q') {
		printf("无效选择，请重试: ");
		while (getchar() != '\n')
			continue;
	}
	while (getchar() != '\n')
		continue;
	return opt;
}

void printWordCount(Item item) {
	printf("%-15s出现%4d次\n", item.word, item.count);
}

char * toLowercase(char * string) {
	char * retval = string;
	for (; *string != '\0'; string++) {
		*string = tolower(*string);
	}
	return retval;
}

char * get(char * string, int n) {

	char * retval = fgets(string, n, stdin);

	for (; *string != '\0'; string++) {
		if (*string == '\n') {
			*string = '\0';
			break;
		}
	}
	return retval;
}

int main(int argc, char *argv[]) 
{
	
	if (argc != 2) {
		fprintf(stderr, "用法: %s 文件名\n", argv[0]);
		exit(1);
	}

	FILE * fp = fopen(argv[1], "r");
	if (fp == NULL) {
		fprintf(stderr, "无法打开文件 '%s'.\n", argv[1]);
		exit(1);
	}

	char tmp[30];
	Tree words;
	Tree * ptree = InitializeTree(&words);
	while (fscanf(fp, "%s", tmp) == 1) {
		toLowercase(tmp);
		for(int i=0;i<30;i++)
			if(tmp[i]>'z'||tmp[i]<'a')
			{
				tmp[i]='\0';
				break;
			}
		if (!AddWord(tmp, ptree)) {
			fprintf(stderr, "未能将 %s 添加到查找范围中.\n",tmp);
			exit(1);
		}
	}

	int ch;
	char word[30];
	while ((ch = getOption()) != 'q') {
		if (ch == 'a') {
			ApplyToAll(ptree, printWordCount);
		} else if (ch == 'b') {
			printf("输入要搜索的单词: ");
			get(word, 30);
			ApplyToNode(word, ptree, printWordCount);
		}
		puts("");
	}
}