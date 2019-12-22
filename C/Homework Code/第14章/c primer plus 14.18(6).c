//第14章 第6题
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
struct Player
{
	char first[12];
	char last[12];
	unsigned int atbats, hits, walks, rbis;
	float battingaverage;
};

void getbattingaverage(struct Player * player)
{
	player->battingaverage = player->hits / (float) player->atbats;
}

void showteamdata(const struct Player * player, int size)
{
	unsigned int atbats = 0, hits = 0, walks = 0, rbis = 0;

	printf("队伍信息 (编号, 名, 姓, 上场次数, 击中数, 走垒数,打点, 安打率):\n");
	for (int i = 0; i < size; i++, player++)
	{
		printf("%2d ", i);
		printf("%*s %*s %u %u %u %u %.3f\n", 12, player->first, 12,
		       player->last, player->atbats, player->hits, player->walks,
		       player->rbis, player->battingaverage);

		atbats += player->atbats;
		hits += player->hits;
		walks += player->walks,
		rbis += player->rbis;
	}
	printf("累计总和: %u %u %u %u %.3f\n", atbats, hits, walks, rbis, hits / (float) atbats);
}

int main(void)
{
	struct Player players[19];
	for (int i = 0; i < 19; i++)
		players[i] = (struct Player) {"", "", 0, 0, 0, 0, 0};

	FILE *fp;
	int number;
	char first[12];
	char last[12];
	unsigned int atbats;
	unsigned int hits;
	unsigned int walks;
	unsigned int rbis;

	if ((fp = fopen("roster.txt", "r")) == NULL)
	{
		fprintf(stderr, "无法打开文件'roster.txt'.\n");
		exit(EXIT_FAILURE);
	}

	while (fscanf(fp, "%d %s %s %u %u %u %u", &number, first, last, &atbats,
		          &hits, &walks, &rbis) == 7)
	{
		if (players[number].first[0] == '\0')
		{
			strncpy(players[number].first, first, 12);
			strncpy(players[number].last, last, 12);
		}
		players[number].atbats += atbats;
		players[number].hits += hits;
		players[number].walks += walks;
		players[number].rbis += rbis;
	}

	for (int i = 0; i < 19; i++)
		getbattingaverage(&players[i]);

	showteamdata(players, 19);

	if (fclose(fp) != 0)
		fprintf(stderr,"打开文件失败，正在关闭文件······\n");

	return 0;
}