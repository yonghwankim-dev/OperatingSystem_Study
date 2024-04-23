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
    const int SIZE = 4096;                      // shared memory size
    const char* name = "OS";                    // shared memory name
    const char* message_0 = "Hello, ";
    const char* message_1 = "Shared Memory!\n";
    
    int shm_fd; // shared memory file description
    char* ptr;  // shared memory pointer

    // create shared memory
    shm_fd = shm_open(name, O_CREAT | O_RDWR, 0666);
    
    // set shared memory size
    ftruncate(shm_fd, SIZE);

    // mapping shared memory
    ptr = (char*) mmap(0, SIZE, PROT_READ | PROT_WRITE, MAP_SHARED, shm_fd, 0);

    // write message to shared memory
    sprintf(ptr, "%s", message_0);
    ptr += strlen(message_0);
    sprintf(ptr, "%s", message_1);
    ptr += strlen(message_1);

    return 0;
}
