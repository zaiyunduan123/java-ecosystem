## 造轮子系列

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