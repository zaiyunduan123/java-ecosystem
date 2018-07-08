# Java生态学习汇总

## 锁汇总

### **乐观锁**

正如其名，**在要提交更新时才关心是否发生冲突**，实现：给数据增加一个version字段，为每一条数据加上版本。每次更新的时候version+1，并且更新时候带上版本号，当提交前版本号等于更新前版本号，说明此时没有被其他线程影响到，正常更新，如果冲突了则不会进行提交更新。众所周知的CAS就是乐观锁的一种实现。

代码示例:[乐观锁](https://github.com/zaiyunduan123/jesper_seckill/blob/master/src/main/java/com/jesper/seckill/mapper/GoodsMapper.java)


###  **悲观锁**

在整个数据处理过程中持保守态度，将数据处于锁定状态，悲观锁大多数情况下依靠**数据库的锁机制实现，以保证操作最大程度的独占性**。在Java中，synchronized的思想也是悲观锁。如果加锁的时间过长，其他用户长时间无法访问，影响程序的并发访问性，同时这样对数据库性能开销影响也很大，特别是长事务而言，这样的开销往往无法承受。


### **分布式锁**

分布式场景中的数据一致性问题一直是一个比较重要的话题。我们为了保证数据的最终一致性，需要很多的技术方案来支持保证一个方法在同一时间内只能被同一个线程执行，比如分布式事务、分布式锁等。目前比较常用的有以下几种方案：

1. 基于数据库实现分布式锁 
2. 基于缓存（redis，memcached，tair）实现分布式锁 
3. 基于Zookeeper实现分布式锁


代码示例:[基于缓存实现的分布式锁](https://github.com/zaiyunduan123/redis-tool)


### **可重入锁**

ReentrantLock重入锁，是实现Lock接口的一个类，支持重入性，表示能够对共享资源能够重复加锁，即当前线程获取该锁再次获取不会被阻塞，在java关键字synchronized也是重入锁，隐式支持重入性，synchronized通过获取自增，释放自减的方式实现重入。

代码示例:[可重入锁](https://github.com/zaiyunduan123/Java_ecosystem/tree/master/src/lock/reentrant)

### **自旋锁**

自旋锁与互斥锁有点类似，只是自旋锁不会引起调用者睡眠，如果自旋锁已经被别的执行单元保持，调用者就一直循环在那里看是否该自旋锁的保持者已经释放了锁，"自旋"一词就是因此而得名。

由于自旋锁使用者一般保持锁时间非常短，因此选择自旋而不是睡眠是非常必要的，自旋锁的效率远高于互斥锁。

代码示例:[自旋锁](https://github.com/zaiyunduan123/Java_ecosystem/tree/master/src/lock/spin)

### **读写锁**

ReadWriteLock管理一组锁，一个是只读的锁，一个是写锁。读锁可以在没有写锁的时候被多个线程同时持有，写锁是独占的。 

代码示例:[读写锁](https://github.com/zaiyunduan123/Java_ecosystem/tree/master/src/lock/readwrite)

### **信号量**

Semaphore是用来保护一个或者多个共享资源的访问，Semaphore内部维护了一个计数器，其值为可以访问的共享资源的个数。一个线程要访问共享资源，先获得信号量，如果信号量的计数器值大于1，意味着有共享资源可以访问，则使其计数器值减去1，再访问共享资源。

如果计数器值为0,线程进入休眠。当某个线程使用完共享资源后，释放信号量，并将信号量内部的计数器加1，之前进入休眠的线程将被唤醒并再次试图获得信号量。

代码示例:[信号量](https://github.com/zaiyunduan123/Java_ecosystem/tree/master/src/lock/semaphore)

### **条件变量**

Condition的作用是对锁进行更精确的控制。Condition中的await()方法相当于Object的wait()方法，Condition中的signal()方法相当于Object的notify()方法，Condition中的signalAll()相当于Object的notifyAll()方法。不同的是，Object中的wait(),notify(),notifyAll()方法是和"同步锁"(synchronized关键字)捆绑使用的；而Condition是需要与"互斥锁"/"共享锁"捆绑使用的。
代码示例:[条件变量](https://github.com/zaiyunduan123/Java_ecosystem/tree/master/src/lock/condition/ConditionDemo.java)



## 重复造轮子系列

### **自己实现一个线程池**

线程池构造的核心几个点：

1. 线程池里的核心线程数与最大线程数
2. 线程池里真正工作的线程worker
3. 线程池里用来存取任务的队列
4. 线程中的任务task

代码示例:[自定义线程池](https://github.com/zaiyunduan123/Java_ecosystem/tree/master/src/wheel/threadpool)

### **自己实现独占锁**

内部通过继承队列同步器AQS来实现自定义同步器，自定义锁将操作代理给Sync上。同步器只是定义了若干同步状态获取和释放的方法来供自定义同步组件使用

代码示例:[自定义锁](https://github.com/zaiyunduan123/Java_ecosystem/tree/master/src/lock/custom/Mutex.java)


### **自己实现LRU Cache**

LRU是Least Recently Used 的缩写，翻译过来就是“最近最少使用”，LRU缓存就是使用这种原理实现，简单的说就是缓存一定量的数据，当超过设定的阈值时就把一些过期的数据删除掉，Java里面实现LRU缓存通常有两种选择，一种是使用LinkedHashMap，一种是自己设计数据结构，使用链表+HashMap

代码示例:[自定义LRU缓存](https://github.com/zaiyunduan123/Java_ecosystem/tree/master/src/wheel/lru)


## JVM

### 栈溢出
