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
    
    int shm_fd; // shared memory의 file 설명자
    char* ptr;  // shared memory의 pointer

    // shared memory 객체 생성
    shm_fd = shm_open(name, O_RDONLY, 0666);
    
    // shared memory 객체에 매핑
    ptr = (char*) mmap(0, SIZE, PROT_READ, MAP_SHARED, shm_fd, 0);

    // shared memory 객체 읽기
    printf("%s", (char*)ptr);

    // shared memory 제거
    shm_unlink(name);

    return 0;
}