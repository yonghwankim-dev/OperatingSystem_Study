#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/shm.h>
#include <sys/stat.h>
#include <sys/mman.h>


int main()
{
    const int SIZE = 4096;                      // shared memory의 크기
    const char* name = "OS";                    // shared memory의 이름
    const char* message_0 = "Hello, ";
    const char* message_1 = "Shared Memory!\n";
    
    int shm_fd; // shared memory의 file 설명자
    char* ptr;  // shared memory의 pointer

    // shared memory 객체 생성
    shm_fd = shm_open(name, O_CREAT | O_RDWR, 0666);
    
    // shared memory의 크기 설정
    ftruncate(shm_fd, SIZE);

    // shared memory 객체에 매핑
    ptr = (char*) mmap(0, SIZE, PROT_READ | PROT_WRITE, MAP_SHARED, shm_fd, 0);

    // shared memory에 메시지 작성
    sprintf(ptr, "%s", message_0);
    ptr += strlen(message_0);
    sprintf(ptr, "%s", message_1);
    ptr += strlen(message_1);

    return 0;
}