# Thread-Demo

线程相关的代码

ThreadJX  两个线程交叉打印数据 

对象调用 wait()放弃锁  线程进入等待池 此时其它线程可以获取锁
当唤醒wait()的线程的时候，线程进入同步队列等待其他对象释放锁，并再次获取锁继续执行
