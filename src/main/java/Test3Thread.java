public class Test3Thread {

    static volatile int flag = 1;
    static Object lock = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock) {
                        if(flag==1){
                            System.out.println(Thread.currentThread()+":-> 1");
                            flag =2;
                            try {
                                lock.notifyAll();
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }, "Thread-1");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock) {
                        if(flag==2){
                            System.out.println(Thread.currentThread()+":-> 2");
                            flag =3;
                            try {
                                lock.notifyAll();
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }, "Thread-2");
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock) {
                        if(flag==3){
                            System.out.println(Thread.currentThread()+":-> 3");
                            flag =1;
                            try {
                                lock.notifyAll();
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }, "Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();

    }
}
