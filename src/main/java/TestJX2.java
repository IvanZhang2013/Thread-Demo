public class TestJX2 {

    static Object lock = new Object();
    static int i = 0;
    static boolean flag = true;

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
        ts1.start();
        Thread ts2 = new Thread(new Runnable() {
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
        }, "Thread-2");
        ts2.start();
    }
}
