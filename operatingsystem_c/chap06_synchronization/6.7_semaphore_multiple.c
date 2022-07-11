#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>

int sum = 0;    // a shared variable

sem_t sem;

void* counter(void* param)
{
    for(int k=0; k<10000; k++)
    {
        /* entry section */
        sem_wait(&sem);

        /* critical section */
        sum++;

        /* exit section */
        sem_post(&sem);

        /* remainder section */
    }
    pthread_exit(0);
}

int main()
{
    pthread_t tid[5];
    sem_init(&sem, 0, 5);
    
    for(int i=0; i<5; i++)
    {
        pthread_create(&tid[i], NULL, counter, NULL);
    }

    for(int i=0; i<5; i++)
    {
        pthread_join(tid[i], NULL);
    }

    printf("sum = %d\n", sum);  // not 50000, synchronization error
}