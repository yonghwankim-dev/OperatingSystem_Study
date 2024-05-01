#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <semaphore.h>
#include <fcntl.h>

#define BUFFER_SIZE 5 // shared memory buffer size
#define SHM_KEY 12345 // shared memory key

typedef struct{
    int buffer[BUFFER_SIZE];
    int in; // index to write the data
    int out; // index to read the data
    sem_t mutex; // semaphore to use when accessing the buffer
    sem_t full; // semaphore to use when fulling the buffer
    sem_t empty; // semaphore to use when empty the buffer
} SharedData;


/*
title : semaphore 및 shared_memory 방식의 생산자-소비자 문제 구현
*/
int main(){
    int shm_id;
    SharedData* shared_data;

    shm_id = shmget(SHM_KEY, sizeof(shared_data), IPC_CREAT | 0666); //create shared memory
    if(shm_id < 0){
        perror("shmget");
        exit(1);
    }

    // connect the shared memory
    shared_data = (SharedData*) shmat(shm_id, NULL, 0);
    if(shared_data == (SharedData*)(-1)){
        perror("shmat");
        exit(1);
    }

    // init semaphore
    sem_init(&shared_data->mutex, 1, 1);
    sem_init(&shared_data->full, 1, 0);
    sem_init(&shared_data->empty, 1, BUFFER_SIZE);

    // create producer process
    if(fork() == 0){
        int i;

        for (i = 0; i < 10; i++){
            sem_wait(&shared_data->empty);
            sem_wait(&shared_data->mutex);

            shared_data->buffer[shared_data->in] = i;
            printf("Produced: %d\n", i);
            shared_data->in = (shared_data->in + 1) % BUFFER_SIZE;

            sem_post(&shared_data->mutex);
            sem_post(&shared_data->full);
        }
        exit(0);
    }

    // create consumer process
    if(fork() == 0){
        int i;
        for (i = 0; i < 10; i++){
            sem_wait(&shared_data->full);
            sem_wait(&shared_data->mutex);

            int item = shared_data->buffer[shared_data->out];
            printf("Consumed: %d\n", item);
            shared_data->out = (shared_data->out + 1) % BUFFER_SIZE;

            sem_post(&shared_data->mutex);
            sem_post(&shared_data->empty);

            sleep(1);
        }
        exit(0);
    }

    // wait the parent process until finish the child process;
    wait(NULL);

    shmctl(shm_id, IPC_RMID, NULL);
    sem_destroy(&shared_data->mutex);
    sem_destroy(&shared_data->full);
    sem_destroy(&shared_data->empty);

    return 0;
}

