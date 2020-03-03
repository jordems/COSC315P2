#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>
#include <stdbool.h>
#include <time.h>

#define BUFFER_SIZE 500

void *producer();
void *consumer();
int bufferGet();
void bufferPut(int job);
int randombyRange(int min, int max);

pthread_mutex_t mutex;
sem_t full;
sem_t empty;

int buffer[BUFFER_SIZE];
int getIndex = 0;
int putIndex = 0;
int count = 0;
int maxDuration;

/**
 * 	Main
 */
int main()
{
	sem_init(&full, 0, 0);
	sem_init(&empty, 0, BUFFER_SIZE);
	pthread_mutex_init(&mutex, NULL);

	pthread_t producerThread;

	int numConsumers;

	printf("# Consumers: ");
	fflush(stdout);
	scanf("%d", &numConsumers);

	printf("\nMax Duration: ");
	fflush(stdout);
	scanf("\n%d", &maxDuration);

	pthread_create(&producerThread, NULL, producer, NULL);

	for (int i = 0; i < numConsumers; i++)
	{
		pthread_t consumerThread;
		pthread_create(&consumerThread, NULL, consumer, NULL);
	}

	pthread_exit(NULL);
}

/**
 * 	Producer Thread
 */
void *producer()
{
	printf("\nI am a producer, My id is: %d", pthread_self());
	fflush(stdout);

	while (true)
	{
		int randSleep = randombyRange(1, maxDuration);
		printf("\nProducer sleeping for %d seconds.", randSleep);
		fflush(stdout);

		sleep(randSleep);

		int randDuration = randombyRange(1, maxDuration);
		printf("\nProduced Job of length: %d", randDuration);

		bufferPut(randDuration);

		count++;
	}

	return NULL;
}

/**
 * 	Consumer Thread
 */
void *consumer()
{
	printf("\nI am a consumer, My id is: %d", pthread_self());
	fflush(stdout);

	while (true)
	{
		int job = bufferGet();
		clock_t startTime = clock();
		printf("\nConsumer: %d, Consuming Job of duration: %d seconds", pthread_self(), job);
		fflush(stdout);
		sleep(job);
		clock_t endTime = clock();
		double total_t = (double)(endTime - startTime) / CLOCKS_PER_SEC;
		printf("\nConsumer completed job in time: %d seconds.", total_t);
	}

	return NULL;
}

/**
 * Gets a job out of the Bounded Buffer with a manually made `synchronous` method
 */
int bufferGet()
{
	sem_wait(&full);
	pthread_mutex_lock(&mutex);

	int job = buffer[getIndex];
	getIndex++;
	if (getIndex > BUFFER_SIZE - 1)
	{
		getIndex = 0;
	}
	count--;
	pthread_mutex_unlock(&mutex);
	sem_post(&empty);
	return job;
}

/**
 * Places job into Bounded Buffer within a manually made `synchronous` method
 */
void bufferPut(int job)
{
	sem_wait(&empty);
	pthread_mutex_lock(&mutex);

	buffer[putIndex] = job;
	putIndex++;
	if (putIndex > BUFFER_SIZE - 1)
	{
		putIndex = 0;
	}
	count++;

	pthread_mutex_unlock(&mutex);
	sem_post(&full);
}

/** 
 * Generates random from min to max with seed of CPU clock
 */
int randombyRange(int min, int max)
{
	srand(clock());
	return (int)(((rand() / (double)RAND_MAX) * (max - min + 1)) + min);
}
