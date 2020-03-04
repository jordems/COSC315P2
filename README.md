# COSC 315 - Mini Project 2 - Group 6

## Group Members:

- **Levi Magnus (38012150)**
- **Dylan Baiko (50750158)**
- **Jordan Emslie (20600152)**

## Run Instructions

Java: Run `App.java`. You will be prompted to enter a number for Consumers and Max Duration. The program will continue producing/consuming until you terminate it.

C: Compile using `gcc ProducersConsumers.c -o app -lpthread` and then run using  `./app`. You will be prompted to enter a number for Consumers and Max Duration. The program will continue producing/consuming until you terminate it.

## Description

For this project we solved the producer-consumer synchronization problem in Java using monitors and in C using the pthreads library and semaphores. Both versions of our solution allow the user to specify the number of consumer threads to create as well as the max duration, which is the longest duration (in seconds) that can be generated when creating the random duration for each job. Our solutions continue to produce and consume until the user terminates the program, and the threads all print useful messages that indicate their actions. Overall, we found solving the producer-consumer problem in Java was easier and took less effort as the use of monitors simplified synchronization and it is a programming language that we are more familiar with.

## Java Implementation

The Java implementation of the producer-consumers model makes use of multiple Java objects to split up and assign logic and tasks in an appropriate way. The producer and consumers were separate classes that extended the thread class (making their operation occur in a different thread than the master), and these threads are created and managed in the main `ApacheProducerConsumer` class. Both of these classes are given the same instance of the `BoundedBuffer` class, which they will both interact with to communicate job objects. The BoundedBuffer acts as the monitor in this case, managing the get and put actions on the buffer array in a synchronized way as to prevent data races on the buffer. In the runtime of the program, the number of desired consumers and the maximum job length are retrieved from the user, and the BoundedBuffer, producer thread, and all the necessary consumer threads are created. Then the threads are started, which run forever producing and consuming jobs. 

In the `get` syncronized method (called by the consumers), the BoundedBuffer first waits for there to actually be elements in the buffer and puts all the threads waiting for data to sleep with the `wait()` method. When there is data, exactly 1 thread will be able to retrieve an element from the buffer, removing the item from the array in the process. A `getIndex` and a `count` is maintained to ensure no `ArrayOutOfBoundsException`'s occur. Then the thread will notify the other threads that they can now interact with the buffer.

In the `put` syncronized method (called by the producers), the BounderBuffer first waits for there to be at least 1 space available on the buffer and puts all the threads waiting to put data to sleep with the `wait()` method. When there is space, exactly 1 thread will be able to add an element to the buffer. A `putIndex` and a count is maintained to ensure no `ArrayOutofBoundsExceptions`'s occur. Then the thread will notify the other threads that they can now interact with the buffer.

## C Implementation

Many areas of the solution are similar to Java, but we are going to specify the differences. When starting the C implementation we realized that we could not easily create synchronized methods in C like we could in Java. Reading the project description we knew that we needed to implement a solution using semaphores. We created the `empty` and `full` semaphores so that we could know when the `BounedBuffer` is empty or full. We also needed to create a `mutex`, and considering we are using pthreads we used the pthreads_mutex. This will create a critical section between threads when completing the get and put methods on the `BoundedBuffer`, as the pthreads_mutex may only be unlocked by the thread that has locked it. So, tying these three together whenever we `get` from the bounded buffer we will subtract 1 slot from `full` or wait if 0, then create the critical section to complete the action. Once finished we signal `empty` to open 1 free slot. For `put` it's very similar except we subtract 1 slot from `empty` critical section action, then signal `full` to open 1 free slot. For the producer and consumer, it is quite similar to the Java solution, but instead of extending thread we have the pthreads library to create each thread on the producer and consumer methods. We used pthread_exit to wait for all threads to complete before finishing the process (never will finish in our case as the program continues to produce/consume indefinitely). Finally, considering generating a random integer isn't as easy in C, we also had to create a randombyRange method which generates a random number based on the CPU clock.

## Design Considerations

We began working on the Java implementation first as we knew we could take advantage of monitors. We ended up implementing a solution taking concepts from [Java Implementation Help - Dartmouth](https://www.cs.dartmouth.edu/~scot/cs10/lectures/27/27.html), using the BoundedBuffer's get and put methods as our synchronized methods. Just for fun we added a process timer to make sure that the jobs were executing in the time that we specified. When moving over to the C implementation we were originally attempting to use all three semaphores from semaphore.h, but we realized that we should instead be using the mutex from pthreads, as this can only be unlocked by the thread that locked it. This concept was found from [C Implementation Help - Stackoverflow](https://stackoverflow.com/a/19847934). Implementing the process timer in C wasn't as easy. We couldn't use clock() as it uses CPU Clock ticks, and when you have multiple threads it doesn't work right. So, we used another method found here: [C Process Timer Help](https://stackoverflow.com/a/41959179).

## Group Contributions

Java Implementation:

- Jordan: ApacheProducerConsumer.java + App.java.
- Levi: Producer.java + Consumer.java
- Dylan: BoundedBuffer.java + Job.java

C Implementation:

- ALL: Pair Programming

Design Document:

- Jordan: C Implementation + Design Considerations
- Dylan: Java Implementation
- Levi: Description + Revision

Overall:
We all worked very closely on this project and the majority of it was developed together in a pair-programming setting.

## References

[Java Implementation Help - Dartmouth](https://www.cs.dartmouth.edu/~scot/cs10/lectures/27/27.html)

[C Implementation Help - Stackoverflow](https://stackoverflow.com/a/19847934)

[C Process Timer Help](https://stackoverflow.com/a/41959179)
