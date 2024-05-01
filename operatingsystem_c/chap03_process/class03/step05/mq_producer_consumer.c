#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <mqueue.h>
#include <string.h>

#define QUEUE_NAME "/my_queue"
#define MAX_MESSAGES 10
#define MAX_MSG_SIZE 256

void producer(mqd_t mq);
void consumer(mqd_t mq);

int main(){
    mqd_t mq;
    pid_t pid;

    // create the message queue
    struct mq_attr attr;
    attr.mq_flags = 0;
    attr.mq_maxmsg = MAX_MESSAGES;
    attr.mq_msgsize = MAX_MSG_SIZE;
    attr.mq_curmsgs = 0;

    mq = mq_open(QUEUE_NAME, O_CREAT | O_RDWR, 0666, &attr);
    if (mq == -1){
        perror("mq_open");
        return 1;
    }

    // create process
    pid = fork();
    if (pid == -1){
        perror("fork");
        exit(EXIT_FAILURE);
    } else if(pid == 0){
        // start the consumer process (child process)
        consumer(mq);
        exit(EXIT_SUCCESS);
    } else{
        // start the producer process (parent process)
        producer(mq);
    }

    wait(NULL);

    // close the message queue
    if(mq_close(mq) == -1){
        perror("mq_close");
        exit(EXIT_FAILURE);
    }

    // unlink the message queue
    if(mq_unlink(QUEUE_NAME) == -1){
        perror("mq_unlink");
        exit(EXIT_FAILURE);
    }

    return 0;
}

void producer(mqd_t mq){
    int i;
    for(i = 0; i < MAX_MESSAGES; i++){
        char msg[MAX_MSG_SIZE];
        sprintf(msg, "Message %d", i);
        printf("Sending message: %s\n", msg);

        // send the message
        if(mq_send(mq, msg, strlen(msg), 0) == -1){
            perror("mq_send");
            exit(EXIT_FAILURE);
        }

        printf("Produced: %s\n", msg);
        sleep(1);
    }
}


void consumer(mqd_t mq){
    int i;
    for(i = 0; i < MAX_MESSAGES; i++){
        char buffer[MAX_MSG_SIZE];
        // init buffer array before receving the message
        memset(buffer, 0, MAX_MSG_SIZE);

        // receive the message
        if(mq_receive(mq, buffer, MAX_MSG_SIZE, NULL) == -1){
            perror("mq_receive");
            exit(EXIT_FAILURE);
        }
        printf("Received: %s\n", buffer);
        sleep(2);
    }
}
