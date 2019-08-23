import java.util.concurrent.Callable;

class MyThreadRunable implements Runnable {
    public void run() {
        System.out.println("现在调用的线程为：MyThreadRunable");
    }
}

class MyThreadCallable implements Callable<Integer> {
    public Integer call() {
        System.out.println("现在调用的线程为：MyThreadCallable");
        return 777;
    }
}

class MyThreadExtendThread extends Thread {
    @Override
    public void run() {
        System.out.println("现在调用的线程为：MyThreadExtendThread");
    }
}