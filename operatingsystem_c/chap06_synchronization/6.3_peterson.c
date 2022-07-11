#include <stdio.h>
#include <pthread.h>

#define true 1
#define false 0

void* producer(void* param);
void* consumer(void* param);

int sum = 0;
int turn;
int flag[2];

int main()
{
    pthread_t tid1, tid2;
    pthread_create(&tid1, NULL, producer, NULL);
    pthread_create(&tid2, NULL, consumer, NULL);

    pthread_join(tid1, NULL);
    pthread_join(tid2, NULL);
    
    printf("sum = %d\n",sum);
}

void* producer(void* param)
{
    for(int k = 0; k < 10000; k++)
    {
        /* entry section */
        flag[0] = true; // producer 일 시작
        turn = 1;

        // consumer가 사용중
        while(flag[1] == true && turn == 1)
        {

        }

        /* critical section */
        sum++;

        /* exit section */
        flag[0] = false;    // producer 일 종료

        /* remainder section */

    }
    pthread_exit(0);
}

void* consumer(void* param)
{
    for(int k = 0; k < 10000; k++)
    {
        /* entry section */
        flag[1] = true;
        turn = 0;

        while(flag[0]==true && turn == 0)
        {

        }

        /* critical section */
        sum--;

        /* exit section */
        flag[1] = false;       
    }
    pthread_exit(0);
}