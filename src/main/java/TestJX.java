
public class TestJX {

    static Object lock = new Object();
    static int i = 0;
    static boolean flag = true;

    public static void main(String[] args) {

        Thread ts1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock) {
                        if (flag) {
                            try {
                                flag = false;
                                i = i + 1;
                                System.out.println(Thread.currentThread() + "---i:=" + i);
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }, "Thread-1");
        ts1.start();
        Thread ts2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock) {
                        if (!flag) {
                            i = i + 1;
                            System.out.println(Thread.currentThread() + "---i:=" + i);
                            lock.notify();
                            flag = true;
                        }
                    }
                }
            }
        }, "Thread-2");
        ts2.start();
    }
}
