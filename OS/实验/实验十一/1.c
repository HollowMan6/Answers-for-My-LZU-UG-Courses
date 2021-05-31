#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <dirent.h>
#include <string.h>
char paths[1000], patht[1000], temp_paths[1000], temp_patht[1000];
void Copy(char *spathname, char *tpathname)
{
    int sfd, tfd;
    struct stat s, t;
    char c;
    sfd = open(spathname, O_RDONLY);
    tfd = open(tpathname, O_RDWR | O_CREAT);
    while (read(sfd, &c, 1) > 0)
        write(tfd, &c, 1);
    fstat(sfd, &s);
    chown(tpathname, s.st_uid, s.st_gid);
    chmod(tpathname, s.st_mode);
    close(sfd);
    close(tfd);
}
void d_copy(char *spathname, char *tpathname)
{
    struct stat s, t, temp_s, temp_t;
    struct dirent *s_p;
    DIR *dirs, *dirt;
    stat(spathname, &s);
    mkdir(tpathname, s.st_mode);
    chown(tpathname, s.st_uid, s.st_gid);
    dirt = opendir(tpathname);
    dirs = opendir(spathname);
    strcpy(temp_paths, spathname);
    strcpy(temp_patht, tpathname);
    while ((s_p = readdir(dirs)) != NULL)
    {
        if (strcmp(s_p->d_name, ".") != 0 && strcmp(s_p->d_name, "..") != 0)
        {
            strcat(temp_paths, "/");
            strcat(temp_paths, s_p->d_name);
            strcat(temp_patht, "/");
            strcat(temp_patht, s_p->d_name);
            lstat(temp_paths, &s);
            temp_s.st_mode = s.st_mode;
            if (S_ISLNK(temp_s.st_mode))
            {
                printf("%s is a symbol link file\n", temp_paths);
            }
            else if (S_ISREG(temp_s.st_mode))
            {
                printf("Copy file %s ......\n", temp_paths);
                Copy(temp_paths, temp_patht);
                strcpy(temp_paths, spathname);
                strcpy(temp_patht, tpathname);
            }
            else if (S_ISDIR(temp_s.st_mode))
            {
                printf("Copy directory %s ......\n", temp_paths);
                d_copy(temp_paths, temp_patht);
                strcpy(temp_paths, spathname);
                strcpy(temp_patht, tpathname);
            }
        }
    }
}
int main()
{
    struct dirent *sp, *tp;
    char spath[1000], tpath[1000], temp_spath[1000], temp_tpath[1000];
    struct stat sbuf, tbuf, temp_sbuf, temp_tbuf;
    char sdirect[1000], tdirect[1000], judge;
    DIR *dir_s, *dir_t;
    printf("Please input the sourse direct path and name :");
    scanf("%s", sdirect);
    dir_s = opendir(sdirect);
    if (dir_s == NULL)
    {
        printf("This directory don't exist !\n");
        return 0;
    }
    if (stat(sdirect, &sbuf) != 0)
    {
        printf("Get status error !\n");
        return 0;
    }
    printf("Please input the target direct path and name :");
    scanf("%s", tdirect);
    dir_t = opendir(tdirect);
    if (dir_t == NULL)
    {
        mkdir(tdirect, sbuf.st_mode);
        chown(tdirect, sbuf.st_uid, sbuf.st_gid);
        dir_t = opendir(tdirect);
    }
    else
    {
        chmod(tdirect, sbuf.st_mode);
        chown(tdirect, sbuf.st_uid, sbuf.st_gid);
    }
    strcpy(spath, sdirect);
    strcpy(tpath, tdirect);
    strcpy(temp_spath, sdirect);
    strcpy(temp_tpath, tdirect);
    printf("Begin copy ........\n");
    while ((sp = readdir(dir_s)) != NULL)
    {
        if (strcmp(sp->d_name, ".") != 0 && strcmp(sp->d_name, "..") != 0)
        {
            strcat(temp_spath, "/");
            strcat(temp_spath, sp->d_name);
            strcat(temp_tpath, "/");
            strcat(temp_tpath, sp->d_name);
            lstat(temp_spath, &sbuf);
            temp_sbuf.st_mode = sbuf.st_mode;
            if (S_ISLNK(temp_sbuf.st_mode))
            {
                printf("%s is a symbolic link file\n", temp_spath);
            }
            else if ((S_IFMT & temp_sbuf.st_mode) == S_IFREG)
            {
                printf("Copy file %s ......\n", temp_spath);
                Copy(temp_spath, temp_tpath);
                strcpy(temp_tpath, tpath);
                strcpy(temp_spath, spath);
            }
            else if ((S_IFMT & temp_sbuf.st_mode) == S_IFDIR)
            {
                printf("Copy directory %s ......\n", temp_spath);
                d_copy(temp_spath, temp_tpath);
                strcpy(temp_tpath, tpath);
                strcpy(temp_spath, spath);
            }
        }
    }
    printf("Copy end !\n");
    closedir(dir_t);
    closedir(dir_s);
    return 1;
}
