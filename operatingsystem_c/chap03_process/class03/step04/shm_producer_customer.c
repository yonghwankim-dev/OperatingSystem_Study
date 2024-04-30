#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>

#define BUFFER_SIZE 5 // shared memory buffer size
#define SHM_KEY 12345 // shared memory key
#define MAX_ITEMS 10

typedef struct {
    int buffer[BUFFER_SIZE]; // the buffer to store the data
    int in; // index to write the data
    int out; // index to read the data
} SharedData;

int main() {
    int shm_id;
    SharedData* shared_data;

    // create the shared memory
    shm_id = shmget(SHM_KEY, sizeof(SharedData), IPC_CREAT | 0666);
    if (shm_id < 0) {
        perror("shmget");
        exit(1);
    }

    // connect the shared memory
    shared_data = (SharedData *)shmat(shm_id, NULL, 0);
    if (shared_data == (SharedData *)(-1)) {
        perror("shmat");
        exit(1);
    }

    shared_data->in = 0;
    shared_data->out = 0;

    // create the producer process
    if (fork() == 0) {
        int next_produced = 0;
        int i;
        for(i = 0; i < MAX_ITEMS; i++) {
            // create the data
            next_produced++;

            while (((shared_data->in + 1) % BUFFER_SIZE) == shared_data->out) {
                // wait until fulling the buffer
            }

            // write the data in the buffer
            shared_data->buffer[shared_data->in] = next_produced;
            shared_data->in = (shared_data->in + 1) % BUFFER_SIZE;

            printf("Produced: %d\n", next_produced);

            sleep(1);
        }
        exit(0);
    }

    // create the customer process
    if (fork() == 0) {
        int i;
        for (i = 0; i < MAX_ITEMS; i++) {
            while (shared_data->in == shared_data->out) {
                // wait until empty the buffer
            }

            // read the data in buffer
            int item = shared_data->buffer[shared_data->out];
            shared_data->out = (shared_data->out + 1) % BUFFER_SIZE;

            printf("Consumed: %d\n", item);

            sleep(2);
        }
        exit(0);
    }

    // wait for Child Process to end
    wait(NULL);

    // destroy shared memory
    shmctl(shm_id, IPC_RMID, NULL);

    return 0;
}
