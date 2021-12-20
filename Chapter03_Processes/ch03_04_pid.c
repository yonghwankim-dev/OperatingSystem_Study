#include <stdio.h>
#include <unistd.h>

int main()
{
    pid_t pid;

    pid = fork();
    printf("Hello, Process! %d\n",pid);
    return 0;
}