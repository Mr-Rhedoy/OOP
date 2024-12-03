class MyThread1 extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread 1: Hi");
            try {
                Thread.sleep(500); // Pause for 500 milliseconds
            } catch (InterruptedException e) {
                System.out.println("Thread 1 interrupted");
            }
        }
    }
}

class MyThread2 extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread 2: Hello");
            try {
                Thread.sleep(500); // Pause for 500 milliseconds
            } catch (InterruptedException e) {
                System.out.println("Thread 2 interrupted");
            }
        }
    }
}

public class WithThread {
    public static void main(String[] args) {
        MyThread1 thread1 = new MyThread1(); // Create the first thread
        MyThread2 thread2 = new MyThread2(); // Create the second thread

        thread1.start(); // Start thread 1
        thread2.start(); // Start thread 2

        try {
            thread1.join(); // Wait for thread 1 to finish
            thread2.join(); // Wait for thread 2 to finish
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }

        System.out.println("Both threads are done!");
    }
}
