#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>

#define FIFO_NAME "/tmp/my_fifo"

int main(){
    int fifo_fd;
    char buf[256];
    size_t num_bytes;

    // open the pipe (readonly mode)
    fifo_fd = open(FIFO_NAME, O_RDONLY);
    if(fifo_fd == -1){
        perror("open");
        exit(1);
    }

    printf("open the pipe. reading the data...\n");

    // read the data from pipe
    while((num_bytes = read(fifo_fd, buf, sizeof(buf))) > 0){
        buf[num_bytes] = '\0';
        printf("Received: %s\n", buf);
    }

    if(num_bytes == -1){
        perror("read");
        exit(1);
    }

    // close the pipe
    if(close(fifo_fd) == -1){
        perror("close");
        exit(1);
    }

    printf("finish the program\n");
    return 0;
}
