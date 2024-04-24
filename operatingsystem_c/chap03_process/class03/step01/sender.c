#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <mqueue.h>

#define QUEUE_NAME "/my_message_queue"
#define MAX_MSG_SIZE 256
#define MSG_STOP "exit"

int main(){
    mqd_t mq;
    struct mq_attr attr;
    char buffer[MAX_MSG_SIZE];

    // set up the attributes of  the message queue
    attr.mq_flags = 0;
    attr.mq_maxmsg = 10;
    attr.mq_msgsize = MAX_MSG_SIZE;
    attr.mq_curmsgs = 0;

    // create the message queue
    mq = mq_open(QUEUE_NAME, O_CREAT | O_WRONLY, 0644, &attr);
    if(mq == (mqd_t)-1){
        perror("mq_open");
        exit(1);
    }

    // send message until "exit" is entered
    printf("Enter message to send (or 'exit' to quit): \n");
    while(1){
        printf("> ");
        fgets(buffer, MAX_MSG_SIZE, stdin);

        // check for exit command
        if(strncmp(buffer, MSG_STOP, strlen(MSG_STOP)) == 0){
            if(mq_send(mq, buffer, strlen(buffer), 0) == -1){
                perror("mq_send");
                exit(1);
            }
            break;
        }

        // send the message
        if(mq_send(mq, buffer, strlen(buffer), 0) == -1){
            perror("mq_send");
            exit(1);
        }
        printf("Message sent: %s", buffer);
    }

    printf("Exiting...\n");
    sleep(1);
    // close the message queue
    mq_close(mq);
    mq_unlink(QUEUE_NAME); // remove the message queue from the system
    return 0;
}
