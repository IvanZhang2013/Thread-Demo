public class Test2JX {
    static Object lock = new Object();
    static int i = 0;
    public static void main(String[] args) {
        Thread ts1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock) {
                        i = i + 1;
                        System.out.println(Thread.currentThread() + "---i:=" + i);
                        try {
                            lock.wait(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "Thread-1");


        Thread ts2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock) {
                        i = i + 1;
                        System.out.println(Thread.currentThread() + "---i:=" + i);
                        try {
                            lock.wait(1);
                            ts1.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "Thread-2");

        Thread ts3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock) {
                        i = i + 1;
                        System.out.println(Thread.currentThread() + "---i:=" + i);
                        try {
                            lock.wait(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "Thread-3");
        ts1.start();
        ts2.start();
        ts3.start();
    }
}
