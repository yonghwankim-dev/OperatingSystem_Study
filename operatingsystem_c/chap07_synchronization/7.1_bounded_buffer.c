#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <semaphore.h>

#define true 1
#define BUFFER_SIZE 1

int buffer[BUFFER_SIZE];

pthread_mutex_t mutex;
sem_t empty, full;

int in  = 0;
int out = 0;

void insert_item(int item){
    sem_wait(&empty);
    pthread_mutex_lock(&mutex);

    buffer[in] = item;
    in = (in + 1) & BUFFER_SIZE;
    printf("Producer : inserted $%d\n", item);

    pthread_mutex_unlock(&mutex);
    sem_post(&full);
}

void remove_item(int* item){
    sem_wait(&full);
    pthread_mutex_lock(&mutex);

    *item = buffer[out];
    out = (out + 1) % BUFFER_SIZE;
    printf("Consumer : removed $%d\n", *item);

    pthread_mutex_unlock(&mutex);
    sem_post(&empty);
}

void* producer(void* param){
    int item;
    while(true){
        usleep((1 + rand() % 5) * 100000); // sleep 0.1s ~ 0.5s
        item = 1000 + rand() % 1000;       // create 1000 ~ 2000 random number
        insert_item(item);                 // critical section
    }
}

void* consumer(void* param){
    int item;
    while(true){
        usleep((1 + rand() % 5) * 100000); // sleep 0.1s ~ 0.5s
        remove_item(&item);           // critical section
    }
}



int main(){
    int i = 0;
    int numOfProducers = 1;
    int numOfConsumers = 1;
    pthread_t tid;

    pthread_mutex_init(&mutex, NULL);
    sem_init(&empty, 0, BUFFER_SIZE);
    sem_init(&full,  0, 0);

    srand(time(0));

    // create the producers
    for(i = 0; i < numOfProducers; i++){
        pthread_create(&tid, NULL, producer, NULL);
    }

    // create the consumers
    for(i = 0; i < numOfConsumers; i++){
        pthread_create(&tid, NULL, consumer, NULL);
    }

    sleep(10);
    return 0;
}