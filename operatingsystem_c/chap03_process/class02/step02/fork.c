#include <stdio.h>
#include <unistd.h>

int main()
{
    pid_t pid;

    pid = fork();
    printf("Hello, Process!\n");
    wait(NULL);
    return 0;
}
