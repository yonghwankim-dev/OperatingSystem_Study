#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mqueue.h>

#define QUEUE_NAME "/my_message_queue"
#define MAX_MSG_SIZE 256
#define MSG_STOP "exit"

int main(){
    mqd_t mq;
    struct mq_attr attr;
    char buffer[MAX_MSG_SIZE];
    ssize_t bytes_read;

    // set up the attributes of message queue
    attr.mq_flags = 0;
    attr.mq_maxmsg = 10;
    attr.mq_msgsize = MAX_MSG_SIZE;
    attr.mq_curmsgs = 0;

    // open the message queue for reading
    mq = mq_open(QUEUE_NAME, O_RDONLY, 0644, &attr);
    if (mq == (mqd_t)-1) {
        perror("mq_open");
        exit(1);
    }

    // read messages from the queue
    printf("Waiting for messages from sender (enter 'exit' to quit):\n");
    while(1){
        bytes_read = mq_receive(mq, buffer, MAX_MSG_SIZE, NULL);
        if (bytes_read == -1) {
            perror("mq_receive");
            exit(1);
        }

        // Null-terminate the received message
        buffer[bytes_read] = '\0';

        // Check if the received message is MSG_STOP
        if (strncmp(buffer, MSG_STOP, strlen(MSG_STOP)) == 0) {
            printf("Received stop message, exiting...\n");
            break;
        }

        // Print the received message
        printf("Received message: %s\n", buffer);
    }
    printf("Exiting...\n");
}
