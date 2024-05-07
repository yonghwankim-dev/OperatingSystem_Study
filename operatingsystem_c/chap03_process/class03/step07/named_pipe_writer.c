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

    // create named pipe
    mkfifo(FIFO_NAME, 0666);

    fifo_fd = open(FIFO_NAME, O_RDWR);
    if(fifo_fd == -1){
        perror("open");
        exit(1);
    }

    printf("open the pipe. input the message (if you want exit, input 'exit'): \n");

    // read the user input and then write to the pipe
    while(fgets(buf, sizeof(buf), stdin) != NULL){
        // 'exit' is the exit command
        if(strcmp(buf, "exit\n") == 0){
            break;
        }

        // write to the pipe
        num_bytes = write(fifo_fd, buf, sizeof(buf));
        if(num_bytes == -1){
            perror("write");
            exit(1);
        }
    }

    // close the pipe
    if(close(fifo_fd) == -1){
        perror("close");
        exit(1);
    }

    // remove the named pipe
    if(unlink(FIFO_NAME) == -1){
        perror("unlink");
        exit(1);
    }

    printf("finish the program.\n");
    return 0;
}
