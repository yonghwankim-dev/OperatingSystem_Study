#include <pthread.h>
#include <stdio.h>
#define NUM_THREADS 5

int sum = 0;

void* runner(void* param);

int main(int argc, char* argv[])
{
    int i, scope;
    pthread_t tid[NUM_THREADS];
    pthread_attr_t attr;

    // 기본 속성 초기화
    pthread_attr_init(&attr);

    // 경재 범위 정책 확인
    if(pthread_attr_getscope(&attr, &scope) != 0)
    {
        fprintf(stderr, "Unable to get scheduling scope\n");
    }
    else
    {
        if(scope==PTHREAD_SCOPE_PROCESS)
        {
            printf("PTHREAD SCOPE PROCESS\n");
        }
        else if(scope==PTHREAD_SCOPE_SYSTEM)
        {
            printf("PTHREAD_SCOPE_SYSTEM\n");
        }
        else
        {
            fprintf(stderr, "Illegal scope value.\n");
        }
    }
    // PCS(프로세스 경쟁 범위) or SCS(시스템 경쟁 범위) 스케줄링 알고리즘 설정
    pthread_attr_setscope(&attr, PTHREAD_SCOPE_SYSTEM);

    // 쓰레드 생성
    for(i=0; i<NUM_THREADS; i++)
    {
        pthread_create(&tid[i], &attr, runner, NULL);
    }

    // 각각의 쓰레드 대기
    for(i=0; i<NUM_THREADS; i++)
    {
        pthread_join(tid[i],NULL);
    }
    printf("sum = %d\n",sum);
}

// each thread will begin control in the function
void* runner(void* param)
{
    sum += 5;

    pthread_exit(0);
}
