//
// Created by qkdlf on 2022-07-11 011.
//
//
// Created by qkdlf on 2022-07-11 011.
//

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>

#define true 1
#define NUM_PHILS 5 // 철학자들의 인원수

// THINKING : 생각중인  상태
// HUNGRY   : 배고픈    상태
// EATING   : 밥을 먹는 상태
enum {THINKING, HUNGRY, EATING} state[NUM_PHILS];

pthread_mutex_t mutex_lock;
pthread_cond_t cond_vars[NUM_PHILS];

void init();
int leftOf(int i);
int rightOf(int i);
void* philosopher(void* param);
void think(int id);
void eat(int id);
void pickup(int id);
void putdown(int id);
void test(int i);

int main(){

    pthread_t tid;
    init();
    for(int i = 0; i < NUM_PHILS; i++){
        pthread_create(&tid, NULL, philosopher, (void*) &i);
    }
    for(int i = 0; i < NUM_PHILS; i++){
        pthread_join(tid, NULL);
    }
    return 0;
}


void init(){
    for(int i = 0; i < NUM_PHILS; i++){
        state[i] = THINKING;
        pthread_cond_init(&cond_vars[i], NULL);
    }
    pthread_mutex_init(&mutex_lock, NULL);
    srand(time(0));
}

int leftOf(int i){
    return (i + NUM_PHILS - 1) % NUM_PHILS;
}

int rightOf(int i){
    return (i + 1) % NUM_PHILS;
}

void* philosopher(void* param){
    int id = *((int *) param);
    while(true){
        think(id);
        pickup(id);
        eat(id);
        putdown(id);
    }
}

void think(int id){
    printf("%d: Now, I'm thinking...\n", id);
    usleep((1 + rand() % 50) * 10000);
}

void eat(int id){
    printf("%d: Now, I'm eating...\n", id);
    usleep((1 + rand() % 50) * 10000);
}

void pickup(int id){
    pthread_mutex_lock(&mutex_lock);

    state[id] = HUNGRY;
    test(id);
    while(state[id] != EATING){
        pthread_cond_wait(&cond_vars[id], &mutex_lock);
    }

    pthread_mutex_unlock(&mutex_lock);
}

void putdown(int id){
    pthread_mutex_lock(&mutex_lock);

    state[id] = THINKING;
    test(leftOf(id));
    test(rightOf(id));

    pthread_mutex_unlock(&mutex_lock);
}

void test(int i){
    // 만약 철학자 i가 배고픈 상태이고 이웃하는 젓가락이 eating 상태가 아니라면
    // 밥을 먹습니다.
    if(state[i] == HUNGRY &&
       state[leftOf(i)] != EATING &&
       state[rightOf(i)] != EATING){
        state[i] = EATING;
        pthread_cond_signal(&cond_vars[i]);
    }
}