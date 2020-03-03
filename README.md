# COSC 315 - Mini Project 2 - Group 6

## Group Members:

- **Levi Magnus (38012150)**
- **Dylan Baiko (50750158)**
- **Jordan Emslie (20600152)**

## Description (Levi)

**TODO** Simple Overall Description

## Java-Implementation (Dylan)

**TODO** Simple Java Description (Monitor Usage, Synchronous methods)

## C-Implementation (Jordan)

Although many areas of the solution are similar to Java, I am specifying the differences. When starting the C-implementation we first realized that there are not any synchronized methods in C. Reading the project description we knew that we will need to implement a solution using semaphores. So we created the `empty` and `full` semaphores so that we can know when the `BounedBuffer` is empty or full. But, we also needed to create a `mutex`, considering we are using pthreads we are using the pthreads_mutex. This will create a critical section between threads when completing the get and put methods on the `BoundedBuffer`, as the pthreads_mutex may only be unlocked by the thread that has locked it. So tying these three together whenever we `get` from the bounded buffer we will subtract 1 slot from `full` or wait if 0, then create the critical section to complete the action. Once finished we signal `empty` to open 1 free slot. For `put` it's very similar except we subtract 1 slot from `empty` critical section action, then signal `full` to open 1 free slot. For the producer and consumer, it is quite similar to the Java solution. But, instead of extending thread. We have the pthreads lib to create each thread on the producer and consumer methods. Then we used pthread_exit to wait for all threads to complete before finishing the process (Never will finish in our case). Finally, considering generating a random integer isn't as easy in C we also had to create a randombyRange method which generates a random number based on the CPU clock.

## Design Considerations (Jordan)

We first began working on the Java-Implementation of the project as we knew we can take advantage of Monitors. We ended up implementing a solution taking concepts from [Java Implementation Help - Dartmouth](https://www.cs.dartmouth.edu/~scot/cs10/lectures/27/27.html). Using the BoundedBuffer's get and put methods as our synchronized methods. Just for fun we added a process timer to make sure that the jobs were executing in the time that we specified. When moving over to the C-Implementation we were attempting first to use all three semaphores from semaphore.h, but we realized that we should instead be using the mutex from pthreads, as this can only be unlocked by the thread that locked it. This concept was found from [C Implementation Help - Stackoverflow](https://stackoverflow.com/a/19847934). Implementing the process timer in C wasn't as easy. We couldn't use clock() as it uses CPU Clock ticks, and when you have multiple threads it doesn't work right. So, we used another method found here: [C Process Timer Help](https://stackoverflow.com/a/41959179).

## Group Contributions (All)

**TODO** Group Contributions
Java-Implementation:

- Jordan: ApacheProducerConsumer.java + App.java.

C-Implementation:

- ALL: Pair Programming

Design Document:

- Jordan: C-Implementation + Design Considerations

Overall:
We all worked very closely on this project and the majority of it was developed together in a pair-programming setting.

## References (All)

[Java Implementation Help - Dartmouth](https://www.cs.dartmouth.edu/~scot/cs10/lectures/27/27.html)

[C Implementation Help - Stackoverflow](https://stackoverflow.com/a/19847934)

[C Process Timer Help](https://stackoverflow.com/a/41959179)
