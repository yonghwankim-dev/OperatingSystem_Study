#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>

#define BUFFER_SIZE 25
#define READ_END 0
#define WRITE_END 1

int main()
{
    char write_msg[BUFFER_SIZE] = "Greetings";
    char read_msg[BUFFER_SIZE];
    int fd[2];
    pid_t pid;

    // pipe 생성
    pipe(fd);

    pid = fork();   // 새로운 프로세스 생성

    if(pid>0)   // 부모 프로세스(parent process)
    {
        close(fd[READ_END]);    // parent는 read할 필요없기 때문에 해제

        // pipe에 메시지 작성
        write(fd[WRITE_END], write_msg, strlen(write_msg)+1);
        close(fd[WRITE_END]);
    }
    else if(pid==0) // 자식 프로세스(child process)
    {
        close(fd[WRITE_END]);
        
        // pipe 메시지 읽기
        read(fd[READ_END], read_msg, BUFFER_SIZE);
        printf("read %s\n",read_msg);
        close(fd[READ_END]);
    }

    return 0;
}