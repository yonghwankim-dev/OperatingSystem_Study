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
    pthread_t tid1, tid2;
    sem_init(&sem, 0, 1);
    pthread_create(&tid1, NULL, counter, NULL);
    pthread_create(&tid2, NULL, counter, NULL);
    pthread_join(tid1, NULL);
    pthread_join(tid2, NULL);
    printf("sum = %d\n", sum);  // 20000
}