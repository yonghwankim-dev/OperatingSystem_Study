#include <stdio.h>
#include <stdlib.h>

int x;
int y = 15;

int main(int argc, char *argv[])
{
    int *values;
    int i;

    values = (int *) malloc(sizeof(int)*5);

    for(i=0; i<5; i++)
    {
        values[i] = i;
    }

    for(i=0; i<5; i++)
    {
        printf("%d ", values[i]);
    }
    printf("\n");
    return 0;
}
