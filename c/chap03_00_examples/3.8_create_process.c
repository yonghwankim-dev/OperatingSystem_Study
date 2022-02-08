#include <stdio.h>
#include <unistd.h>
#include <wait.h>

int main()
{
    pid_t pid;

    // 자식 프로세스 생성
    pid = fork();
    if(pid<0)   // 에러 발생
    {
        fprintf(stderr, "Fork Failed");
        return 1;
    }
    else if(pid==0) // 자식 프로세스인 경우
    {
        execlp("/bin/ls","ls",NULL);
    }
    else    // 부모 프로세스인 경우
    {
        wait(NULL); // 자식 프로세스가 종료될때까지 대기
        printf("Child Complete\n");
    }

    return 0;
}