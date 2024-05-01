#include <stdio.h>
#include <sys/ipc.h>
int main(){
    printf("IPC_CREAT : 10진수 %d\n", IPC_CREAT);
    printf("IPC_CREAT : 8진수 %o\n", IPC_CREAT);
    printf("IPC_CREAT | 0666 : %o\n", IPC_CREAT | 0666);
    return 0;
}
