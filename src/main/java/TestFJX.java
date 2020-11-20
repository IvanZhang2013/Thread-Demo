public class TestFJX {
    static Object lock = new Object();
    static int i = 0;

    /**
     * 当前代码会造成死锁
     *
     *
     * */
    public static void main(String[] args) {
        Thread ts1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock) {
                        i = i + 1;
                        System.out.println(Thread.currentThread() + "---i:=" + i);
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
                            ts1.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "Thread-2");

        ts1.start();
        ts2.start();
    }
}
